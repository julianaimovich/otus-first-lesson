package exceptions;

public class PathEmptyException extends Exception{

    public PathEmptyException() {
        super("Annotation Path not presented on page class");
    }
}