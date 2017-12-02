package pl.edu.agh.to2.parser;

public class ParseException extends Exception{

    public ParseException(String message){
        super(message);
    }

    public ParseException(String message, Throwable cause){
        super(message, cause);
    }
}
