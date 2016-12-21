package rml.exception;

/**
 * Created by Administrator on 2016/9/25.
 */
public class UserAireadyExistException extends Exception {
    public UserAireadyExistException(String s) {
        super(s);
    }

    public UserAireadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
