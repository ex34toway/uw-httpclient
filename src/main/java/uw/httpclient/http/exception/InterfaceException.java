package uw.httpclient.http.exception;

/**
 * 任务异常，用于抛出接口方错误导致的异常。
 *
 * @author axeon
 */
public class InterfaceException extends Exception {

    /**
     * <code>serialVersionUID</code> 的注释
     */
    private static final long serialVersionUID = 8713460933603499992L;

    public InterfaceException() {
        super();
    }

    public InterfaceException(String msg) {
        super(msg);
    }

    public InterfaceException(Throwable nestedThrowable) {
        super(nestedThrowable);
    }

    public InterfaceException(String msg, Throwable nestedThrowable) {
        super(msg, nestedThrowable);
    }

}