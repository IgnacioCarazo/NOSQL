package main;


import java.io.*;
import java.net.Socket;
import java.util.Vector;


public class HiloServidor extends Thread {
     //Atributo de clase
    public static Vector<HiloServidor> usuarioActivo = new Vector<>(); //Atributo que almacena objetos de tipo hilo

    //------Atributos de objetos Hilo servidor-----//
    private ServidorGUI servidor;
    private Paquete paquete1;
    private String nombre;

    private Socket socketCliente;
    private ObjectInputStream canalEntradaServidor;
    private ObjectOutputStream canalSalidaServidor;
    //----------------------------------------------------//

    public HiloServidor(Socket socketCliente, Paquete paquete, ServidorGUI servidor) throws IOException {
        this.socketCliente = socketCliente;
        this.servidor = servidor;
        this.paquete1 = paquete;
        this.nombre = paquete.nombre;
        usuarioActivo.add(this); //Agrega a una lista estatica todos los clientes unidos.

        for (int i = 0; i < usuarioActivo.size() ; i++) { //Envia a todos los clientes quienes estan conectados conforme se unen al servidor

            usuarioActivo.get(i).actualizarClientes();
        }

    }

    public void run(){
        String stringRecibida;
        //------------Ciclo de recepcion de solicitudes por cliente--------//
        while(true){
            try{
                canalEntradaServidor = new ObjectInputStream(socketCliente.getInputStream());
                stringRecibida = (String) canalEntradaServidor.readObject(); //Objeto serealizado
                this.paquete1 = JSON.deserealizar(stringRecibida);
                accion();

                BD.guardarDatos(paquete1);

                //-----Bucle para actualizar clientes.--/////
                for (int i = 0; i < usuarioActivo.size() ; i++) {
                    usuarioActivo.get(i).actualizarClientes();
                    servidor.desplegarEnIrtefaz("Mensaje Enviado");
                }

            } catch (IOException e) {
                servidor.desplegarEnIrtefaz("El usuario: " + this.nombre + " se ha desconectado");
                usuarioActivo.removeElement(this);
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------//
        try{
            socketCliente.close();
        } catch (IOException e) {
            System.out.println("Socket del cliente cerrado. " + e.getMessage());
        }
    }

    private void actualizarClientes() throws IOException {
        canalSalidaServidor = new ObjectOutputStream(socketCliente.getOutputStream());
        String stringEnviada = servidor.serealizar(paquete1);
        canalSalidaServidor.writeObject(stringEnviada); //Salida de la ultima version del paquete1 serealizada
    }
    //--Metodo para realizar la accion proveniente desde el cliente en el paquete1--//

    public void accion(){

        if (paquete1.accion.equals("CREAR_ESQUEMA")){
            // Se crea un nuevo objeto esquema con su respectivo nombre\
            System.out.println("Antes de insercion");
            ServidorGUI.esquemasCreados.imprimirListaEnlazada();
            System.out.println();
            Esquema nuevoEsquema = new Esquema(paquete1.esquema);
            // Agrega el esquema creado a la base de datos en la que se encuentran todos los esquemas
            ServidorGUI.esquemasCreados.agregarInicio(nuevoEsquema);

            System.out.println("ListaEsquemasServidorGUI");
            ServidorGUI.esquemasCreados.imprimirListaEnlazada();
            paquete1.listaEsquemas = ServidorGUI.esquemasCreados;
            System.out.println("ListaEsquemas");
            paquete1.listaEsquemas.imprimirListaEnlazada();

        }



        if(paquete1.accion.equals("MODIFICAR_ESQUEMA")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);
            //Cambia el nombre del esquema al nombre que se quiere cambiar
            esquema.nombreEsquema = paquete1.esquemaNuevoNombre;
        }
        if(paquete1.accion.equals("ELIMINAR_ESQUEMA")){
            //Busca en la lista de esquemasCreados, el esquema por medio de su atributo nombreEsquema
            // Al encontrarlo, elimina ese nodo de la lista enlazada
            ServidorGUI.esquemasCreados.eliminarEsquema(paquete1.esquemaPorBorrar);
        }

        if(paquete1.accion.equals("CREAR_ATRIBUTO")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);
            //Crea un atributo de "x" esquema, con su key: String y su value: Atributo
            esquema.Columnas.put(paquete1.nombreColumna, paquete1.columnaPorCrear);
        }
        if(paquete1.accion.equals("MODIFICAR_ATRIBUTO")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);
            // Mete a las columnas un nuevo key/value. Este tiene un nuevo nombre pero el mismo valor
            // que el key que se quiere modificar
            esquema.Columnas.put(paquete1.columnaNuevoNombre, esquema.Columnas.get(paquete1.nombreColumna));
            // Se elimina el key/value que se queria modificar.
            esquema.Columnas.remove(paquete1.nombreColumna);
        }
        if(paquete1.accion.equals("ELIMINAR_ATRIBUTO")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);
            esquema.Columnas.remove(paquete1.columnaPorBorrar);
            esquema.Datos.eliminarColumna(paquete1.columnaPorBorrar);
        }

        if(paquete1.accion.equals("CREAR_INSTANCIAS")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);

            esquema.Datos.agregarInicio(paquete1.hashMapInstancias);


        }
        if(paquete1.accion.equals("MODIFICAR_INSTANCIAS")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);
        }
        if(paquete1.accion.equals("ELIMINAR_INSTANCIAS")){
            // Obtiene el esquema, conforme al valor entrante de "paquete1.esquema"
            // Asi sabe sobre que esquema debe trabajar
            Esquema esquema = ServidorGUI.esquemasCreados.obtenerEsquema(paquete1.esquema);



        }
    }
}