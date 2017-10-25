package uw.httpclient.json;

import uw.httpclient.http.*;

/**
 * Json接口助手
 * @author liliang
 * @since 2017/9/20
 */
public class JsonInterfaceHelper extends AbstractHttpInterface {

    private HttpHelper httpHelper;

    private ObjectMapper jsonConverter;

    private HttpMessageCallBack callBack;

    /**
     * 生成一个默认实例
     * @return
     */
    public static HttpInterface getInstance(){
        return new JsonInterfaceHelper();
    }

    public JsonInterfaceHelper(){
        this.httpHelper = new HttpHelper();
        this.jsonConverter = ObjectMapper.DEFAULT_JSON_MAPPER;
        this.callBack = null;
    }

    public JsonInterfaceHelper(final HttpMessageCallBack callBack){
        this(ObjectMapper.DEFAULT_JSON_MAPPER,callBack);
    }

    public JsonInterfaceHelper(final ObjectMapper jsonConverter){
        this(jsonConverter,null);
    }

    public JsonInterfaceHelper(final ObjectMapper jsonConverter,final HttpMessageCallBack callBack){
        this.httpHelper = new HttpHelper();
        this.jsonConverter = jsonConverter;
        this.callBack = callBack;
    }

    public JsonInterfaceHelper(final HttpConfig httpConfig){
        this(httpConfig,ObjectMapper.DEFAULT_JSON_MAPPER,null);
    }

    public JsonInterfaceHelper(final HttpConfig httpConfig,final HttpMessageCallBack callBack){
        this(httpConfig,ObjectMapper.DEFAULT_JSON_MAPPER,callBack);
    }

    public JsonInterfaceHelper(final HttpConfig httpConfig,final ObjectMapper jsonConverter,final HttpMessageCallBack callBack) {
        this.httpHelper = new HttpHelper(httpConfig);
        this.jsonConverter = jsonConverter;
        this.callBack = callBack;
    }

    /**
     * 值映射接口
     * @return
     */
    @Override
    public ObjectMapper objectMapper() {
        return jsonConverter;
    }

    /**
     * 请求对象
     * @return
     */
    @Override
    public HttpHelper httpHelper(){
        return httpHelper;
    }

    /**
     * callBack,主要在ObjectMapper反序列化之前把content做一些处理
     * @return
     */
    public HttpMessageCallBack callBack(){
        return callBack;
    }

    /**
     * 请求对象
     * @return
     */
    public HttpHelper httpHelper(HttpConfig httpConfig){
        httpHelper = new HttpHelper(httpConfig);
        return httpHelper;
    }
}
