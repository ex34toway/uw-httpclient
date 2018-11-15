package uw.httpclient.http;

import okhttp3.Request;
import okhttp3.Response;
import uw.task.exception.MapperException;

import java.io.IOException;

/**
 * 抓取结果数据
 * @author liliang
 * @since 2017/9/25
 */
public interface ResponseExtractor<T> {

    /**
     * 直接取Response,主要针对响应文件流等HTTP请求
     */
    ResponseExtractor<Response> RESPONSE_CALLBACK = (r, resp, cb) -> resp;

    /**
     * Extract data from the given {@code okhttp3.Response} and return it.
     * @param response the HTTP response
     * @return the extracted data
     * @throws IOException in case of I/O errors
     */
    T extractData(Request request,Response response,HttpMessageCallBack callBack) throws IOException, MapperException;
}
