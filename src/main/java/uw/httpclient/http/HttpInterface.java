package uw.httpclient.http;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import uw.task.exception.TaskPartnerException;

import java.util.Map;

/**
 * Http请求接口
 * @author liliang
 * @since 2017/9/20
 */
public interface HttpInterface {

    /**
     * 值映射接口
     * @return
     */
    ObjectMapper objectMapper();

    /**
     * 请求对象
     * @return
     */
    HttpHelper httpHelper();

    /**
     * HTTP callBack
     * @return
     */
    HttpMessageCallBack callBack();

    /**
     * 请求对象
     * @param httpConfig
     * @return
     */
    HttpHelper httpHelper(HttpConfig httpConfig);

    /**
     * 自定义请求
     * @param request
     * @param responseType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> requestForEntity(Request request, Class<T> responseType) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> requestForEntity(Request request, TypeReference<T> typeRef) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param responseType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T requestForObject(Request request, Class<T> responseType) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T requestForObject(Request request, TypeReference<T> typeRef) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param responseType
     * @param httpConfig
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> requestEntity(Request request, Class<T> responseType,HttpConfig httpConfig) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param httpConfig
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> requestEntity(Request request, TypeReference<T> typeRef,HttpConfig httpConfig) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param responseType
     * @param httpConfig
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T requestForObject(Request request, Class<T> responseType,HttpConfig httpConfig) throws TaskPartnerException;

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param httpConfig
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T requestForObject(Request request, TypeReference<T> typeRef,HttpConfig httpConfig) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Class<T> responseType,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, TypeReference<T> typeRef,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, HttpConfig httpConfig,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Class<T> responseType, HttpConfig httpConfig,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType,
                                        Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, TypeReference<T> typeRef,
                                        Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    <T> T getForObject(String url, Map<String, String> headers, Class<T> responseType,
                                        Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     */
    <T> T getForObject(String url, Map<String, String> headers, TypeReference<T> typeRef,
                       Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType,HttpConfig httpConfig,
                       Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                                        Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Map<String, String> headers, Class<T> responseType,HttpConfig httpConfig,
                                        Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Map<String, String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                       Object... uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef, Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, TypeReference<T> typeRef, Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, HttpConfig httpConfig,Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Class<T> responseType, HttpConfig httpConfig,Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers,Class<T> responseType,
                                        Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, TypeReference<T> typeRef,
                                        Map<String, ?> uriVariables) throws TaskPartnerException;
    /**
     * GET
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Map<String, String> headers,Class<T> responseType,
                                        Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Map<String, String> headers,TypeReference<T> typeRef,
                       Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers,Class<T> responseType,HttpConfig httpConfig,
                                        Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers,TypeReference<T> typeRef,HttpConfig httpConfig,
                                        Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param responseType
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Map<String, String> headers,Class<T> responseType,HttpConfig httpConfig,
                                        Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T getForObject(String url, Map<String, String> headers,TypeReference<T> typeRef,HttpConfig httpConfig,
                       Map<String, ?> uriVariables) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Class<T> responseType, RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param formParam
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, Map<String,String> formParam) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param formParam
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Class<T> responseType, Map<String,String> formParam) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, TypeReference<T> typeRef, RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param formParam
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, Map<String,String> formParam) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param formParam
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, TypeReference<T> typeRef, Map<String,String> formParam) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, HttpConfig httpConfig,RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Class<T> responseType, HttpConfig httpConfig,RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, TypeReference<T> typeRef, HttpConfig httpConfig,RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers,Class<T> responseType,
                                         RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers,TypeReference<T> typeRef,
                                         RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers,Class<T> responseType,
                                         RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers,TypeReference<T> typeRef,
                        RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers,Class<T> responseType, HttpConfig httpConfig,
                                         RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers,TypeReference<T> typeRef, HttpConfig httpConfig,
                                         RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers,Class<T> responseType, HttpConfig httpConfig,
                                         RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers,TypeReference<T> typeRef, HttpConfig httpConfig,
                        RequestBody requestBody) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType,
                        String content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef,
                                         String content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Class<T> responseType,
                                                String content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, TypeReference<T> typeRef,
                        String content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType,
                                                Object content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef,
                                         Object content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Class<T> responseType,
                        Object content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, TypeReference<T> typeRef,
                        Object content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
                                                String content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,
                                         String content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, Class<T> responseType,
                                                String content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,
                        String content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param responseType
     * @param httpConfig
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
                                         Object content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,
                                         Object content, MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, Class<T> responseType,
                        Object content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, Class<T> responseType,
                        Object content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,
                        Object content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, Class<T> responseType,HttpConfig httpConfig,
                                         String content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                                         String content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, Class<T> responseType,HttpConfig httpConfig,
                                         String content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                        String content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, Class<T> responseType,HttpConfig httpConfig,
                                         Object content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                                         Object content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, Class<T> responseType,HttpConfig httpConfig,
                        Object content,MediaType mediaType) throws TaskPartnerException;

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                        Object content,MediaType mediaType) throws TaskPartnerException;
}
