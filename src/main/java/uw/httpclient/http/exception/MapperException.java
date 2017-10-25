package uw.httpclient.http.exception;

/**
 * 转换异常
 * @author liliang
 * @since 2017/9/22
 */
public class MapperException extends InterfaceException  {

    private static final long serialVersionUID = -4816326148147854194L;

    public MapperException() {
        super();
    }

    public MapperException(String msg) {
        super(msg);
    }

    public MapperException(Throwable nestedThrowable) {
        super(nestedThrowable);
    }

    public MapperException(String msg, Throwable nestedThrowable) {
        super(msg, nestedThrowable);
    }
}
