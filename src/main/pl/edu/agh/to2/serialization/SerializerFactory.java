package pl.edu.agh.to2.serialization;

public class SerializerFactory {

    public static Serializer createJsonSerializer(){
        return new JsonSerializer();
    }
}
