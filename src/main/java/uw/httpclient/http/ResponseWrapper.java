package uw.httpclient.http;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求响应Wrapper
 * @author liliang
 * @since 2017/9/20
 */
public class ResponseWrapper<T> {

    /**
     * 请求响应
     */
    private final Response response;

    /**
     * 请求内容。
     */
    private final Request request;

    /**
     * 响应内容
     */
    private final String responseBody;

    /**
     * 持有对象
     */
    private T value;

    public ResponseWrapper(final Request request,final Response response,final T value,final String responseBody) {
        this.request = request;
        this.response = response;
        this.value = value;
        this.responseBody = responseBody;
    }

    public Response getResponse(){
        return response;
    }

    public Request getRequest() {
        return request;
    }

    public T getValue(){
        return value;
    }

    public String getResponseBody(){
        return responseBody;
    }
}
