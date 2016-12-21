package rml.exception;

/**
 * Created by Administrator on 2016/9/25.
 */
public class UserCanNotBeNullException extends Exception {
    public UserCanNotBeNullException(String message) {
        super(message);
    }

    public UserCanNotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
