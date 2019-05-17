package main;


import java.io.File;
import java.io.IOException;

import static main.JSON.JSON_MAPPER;

public class BD {

    public static void guardarDatos(Paquete persona) throws IOException {
        File rootFile = new File("src/main/BD.json"); //Utilidad para encontrar el archivo donde se va a serealizar el objeto
        File finalFile = new File(rootFile.getAbsolutePath());
        JSON_MAPPER.writeValue(finalFile, persona); //Serealizacion realizada
    }


    public static Paquete extraerDatos()  {
        File rootFile = new File("src/main/BD.json"); //Utilidad para encontrar el archivo donde se va a serealizar el objeto
        File finalFile = new File(rootFile.getAbsolutePath());
        Paquete persona = null; //Objeto Deserealizado
        System.out.println(finalFile);
        try {
            persona = JSON_MAPPER.readValue(finalFile, Paquete.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

}
