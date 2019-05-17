package main;

import Listas.Lista;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Esquema {

    public String nombreEsquema;
    // Columnas del esquema (Atributo generales)
    public HashMap<String, Atributo> Columnas;
    // La lista de instancias del esquema
    public Lista<HashMap> Datos;

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

    public Esquema() {
    }
}
