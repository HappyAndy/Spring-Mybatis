package rml.exception;

/**
 * Created by Administrator on 2016/9/25.
 */
public class UserPwdCanNotBeNullException extends Exception {
    public UserPwdCanNotBeNullException(String s) {
        super(s);
    }

    public UserPwdCanNotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
