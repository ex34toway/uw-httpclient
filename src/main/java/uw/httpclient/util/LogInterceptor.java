package uw.httpclient.util;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @Description:
 * @author: Terry
 * @create 2018-03-02 17:43
 */
public class LogInterceptor implements Interceptor {

    private static final Logger log = Logger.getLogger("The HttpClient test");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        log.info(String.format("发送请求 : %s ,headers : %s",
                request.url(), request.headers()));
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.peekBody(1024*1024);
        log.info(String.format("接收响应: %s %n实际返回json : %s",
                response.request().url(),
                responseBody.string()));
        return response;
    }
}
