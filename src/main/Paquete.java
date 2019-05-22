package main;

import Listas.Lista;

import java.io.Serializable;
import java.util.HashMap;

public class Paquete implements Serializable {

    public String nombre;
    public String mensaje;
    public String accion;

    public String esquema;
    public String esquemaNuevoNombre;
    public  String esquemaPorBorrar;
    public String nombreID;
    public Atributo columnaID;
    public Lista<Esquema> listaEsquemas;

    public String nombreColumna;
    public Atributo columnaPorCrear;
    public String columnaPorBorrar;
    public String columnaNuevoNombre;

    public HashMap<String,Atributo>  hashMapInstancias;
    public Integer valorID;

    public Paquete() {
    }
}