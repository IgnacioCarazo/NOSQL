package main;

import Listas.Lista;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class ServidorGUI extends JFrame {

    private JTextArea jTextArea; //Interfaz

    private ServerSocket serverSocket; //ServidorGUI
    private final int puerto=20000; //ServidorGUI
    private Queue cola = new Queue();
    private Paquete paquete;

    public static Lista<Esquema> esquemasCreados = new Lista<>(); //Lista con los esquemas


    public static void main(String[] args) {
        new ServidorGUI();

    }

    public ServidorGUI(){ //Constructor
        iniciarInterfazServidor();
        paquete = BD.extraerDatos();
        esquemasCreados = paquete.listaEsquemas;
        desplegarEnIrtefaz("INICIANDO SERVIDOR... "+"\t[OK], \nEsperando Clientes");
        System.out.print(" INICIANDO SERVIDOR... ");

        try{
            serverSocket = new ServerSocket(puerto);
            System.out.println("\t[OK], Esperando Clientes");
            int contador = 0;
            String stringRecibida;
            while(true){
                Socket socketCliente = serverSocket.accept();
                System.out.println("Entrada al servidor de un cliente");

                ObjectInputStream canalEntradaServidor = new ObjectInputStream(socketCliente.getInputStream());
                stringRecibida = (String) canalEntradaServidor.readObject();
                //Este paquete solo trae el nombre del cliente
                Paquete paquete = JSON.deserealizar(stringRecibida);
                paquete.listaEsquemas = this.paquete.listaEsquemas;

                desplegarEnIrtefaz(paquete.nombre + " Conectado con la direccion: " + socketCliente.getInetAddress().getHostAddress()); //Interfaz

                HiloServidor hiloServidor = new HiloServidor(socketCliente,paquete, this);
                System.out.println("Hilo del servidor: "  + ++contador + " creado");
                hiloServidor.start();

            }
        } catch (IOException e){
            e.printStackTrace();}
        catch (ClassNotFoundException e) {
            System.out.println("CONEXION DEL SERVIDOR FALLIDA 2" + e.getMessage());
        }
    }


    public void desplegarEnIrtefaz(String msg){ //Relacionado con interfaz del servidor
        this.jTextArea.append(msg + "\n");

    }

    public String serealizar(Paquete paquete) throws IOException {
        cola.insert(paquete);
        Paquete paquete2 = (Paquete) cola.delete();
        return JSON.serealizar(paquete2);
    }

    private void iniciarInterfazServidor(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("BASE DE DATOS");
        setMaximumSize(new Dimension(500,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE); //HIDE_ON_CLOSE
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.DARK_GRAY);
        this.getContentPane().add(jPanel); //Ponerlo sobre la mesa
        jPanel.setLayout(null); //Desactivar el diseño por defecto
        JLabel jLabel = new JLabel(); //De texto o imagenes.
        jLabel.setText("Estado del servidor");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER); //Alinea texto en el centro del label
        jLabel.setBounds(0,0,600,20);
        jLabel.setForeground(Color.WHITE); //Color de la letra
        jLabel.setFont(new Font("Arial", Font.ITALIC ,15));
        jPanel.add(jLabel); //Agregamos etiqueta al jPanel
        jTextArea = new JTextArea();
        jTextArea.setBounds(10,20,570,500);
        jPanel.add(jTextArea);


    }
}

