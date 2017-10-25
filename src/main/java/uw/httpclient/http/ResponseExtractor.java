package uw.httpclient.http;

import okhttp3.Request;
import okhttp3.Response;
import uw.httpclient.http.exception.MapperException;

import java.io.IOException;

/**
 * 抓取结果数据
 * @author liliang
 * @since 2017/9/25
 */
public interface ResponseExtractor<T> {

    /**
     * Extract data from the given {@code okhttp3.Response} and return it.
     * @param response the HTTP response
     * @return the extracted data
     * @throws IOException in case of I/O errors
     */
    T extractData(Request request,Response response,HttpMessageCallBack callBack) throws IOException, MapperException;
}
