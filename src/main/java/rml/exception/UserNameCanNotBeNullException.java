package rml.exception;

/**
 * Created by Administrator on 2016/9/25.
 */
public class UserNameCanNotBeNullException extends Exception {
    public UserNameCanNotBeNullException(String s) {
        super(s);
    }

    public UserNameCanNotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
