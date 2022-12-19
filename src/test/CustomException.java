package test;

public class CustomException extends NullPointerException {
    public CustomException(String message){
        super(message);
    }
}
