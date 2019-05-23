package main;

import java.util.Random;
import Arboles.ArbolAVL;
import Arboles.ArbolR_N;
import Arboles.BinaryTree;
import Listas.Lista;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Esquema{

    public String nombreEsquema;
    // Columnas del esquema (Atributo generales)
    public HashMap<String, Atributo> Columnas;
    // La lista de instancias del esquema
    public Lista<HashMap> Datos;
    public String ID;
    public Lista<String> ListaID;


    public Esquema(String nombreEsquema) {
        this.nombreEsquema = nombreEsquema;
        this.Columnas = new HashMap<>();
        this.Datos = new Lista<>();

    }

    public HashMap<String, Atributo> getColumnas() {
        return Columnas;
    }

    public Lista<HashMap> getDatos() {
        return Datos;
    }

    @Override
    public String toString() {
        return "Nombre del esquema: " + nombreEsquema ;
    }

    public Esquema(){

    }


//    public static void main(String[] args) {
//        Esquema nuevoEsquema = new Esquema("Persona","Cedula","ArbolAVL");
//        Integer porBuscar = 0;
//        //hashmap representa a una instancia
//        for (int i = 0; i < 30000; i++) {
//            HashMap<String,Atributo> instancia = new HashMap();
//            Random random = new Random();
//            Atributo atributo =  new Atributo();
//            atributo.valueInt = i;
//            instancia.put("Carnet", atributo);
//            nuevoEsquema.arbolAVL.insertar(instancia.get("Carnet").valueInt,instancia);
//            nuevoEsquema.arbolR_N.insert(instancia.get("Carnet").valueInt,instancia);
//            nuevoEsquema.Datos.agregarInicio(instancia);
//            if (i == (29999)) {
//                porBuscar = instancia.get("Carnet").valueInt;
//            }
//        }
//        long initial = System.nanoTime();
//        HashMap<String,Atributo> hashMapS = nuevoEsquema.Datos.obtenerHashmap(porBuscar,"Carnet");
//        System.out.println("Busqueda secuencial > " + (System.nanoTime() - initial));
//        System.out.println("Persona encontrada en la lista:  " + hashMapS.get("Carnet").valueInt);
//
//        initial = System.nanoTime();
//        HashMap<String, Atributo> buscado = nuevoEsquema.arbolAVL.buscarElemento(porBuscar);
//        System.out.println("Busqueda en indice > " + (System.nanoTime() - initial));
//        System.out.println("Persona busacada por medio del arbol: " + buscado.get("Carnet").valueInt);
//
//        initial = System.nanoTime();
//        buscado = nuevoEsquema.arbolR_N.search(porBuscar);
//        System.out.println("Busqueda en indice > " + (System.nanoTime() - initial));
//        System.out.println("Persona busacada por medio del arbol: " + buscado.get("Carnet").valueInt);
//
//    }
}
