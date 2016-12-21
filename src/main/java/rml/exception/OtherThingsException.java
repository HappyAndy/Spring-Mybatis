package rml.exception;

/**
 * Created by Administrator on 2016/9/25.
 */
public class OtherThingsException extends Exception {
    public OtherThingsException(String message) {
        super(message);
    }
    public OtherThingsException(Exception e){
        this(e.getMessage());
    }

    public OtherThingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
