package main;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSON {

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper(); //Objeto Jackson


    public static String serealizar(Paquete paquete) throws IOException {
        File rootFile = new File("src/main/servidor.json"); //Utilidad para encontrar el archivo donde se va a serealizar el objeto
        File finalFile = new File(rootFile.getAbsolutePath());
        JSON_MAPPER.writeValue(finalFile, paquete); //Serealizacion realizada
        FileReader reader = new FileReader(finalFile);
        BufferedReader br = new BufferedReader(reader);
        return (br.readLine());
    }

    public static Paquete deserealizar(String inputStream) throws IOException {
        Paquete paquete = JSON_MAPPER.readValue(inputStream, Paquete.class); //Objeto Deserealizado
        return paquete;
    }
}
