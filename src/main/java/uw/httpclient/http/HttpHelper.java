package uw.httpclient.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http请求工具
 * 
 * @author liliang
 * @since 2017/9/19
 */
public class HttpHelper {

	public final static MediaType FORM_UTF8 = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
	public final static MediaType JSON_UTF8 = MediaType.parse("application/json; charset=utf-8");
	public final static MediaType FORM_GBK = MediaType.parse("application/x-www-form-urlencoded; charset=gbk");
	public final static MediaType JSON_GBK = MediaType.parse("application/json; charset=gbk");
	public final static MediaType XML_UTF8 = MediaType.parse("application/xml; charset=utf-8");

	private static final OkHttpClient globalOkHttpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(false).build();

	private final OkHttpClient okHttpClient;

	public HttpHelper() {
		okHttpClient = globalOkHttpClient;
	}

	/**
	 * 自定义OkHttpClient配置
	 * 
	 * @return
	 */
	public HttpHelper(final HttpConfig httpConfig) {
		OkHttpClient.Builder okHttpClientBuilder = globalOkHttpClient.newBuilder()
				.connectTimeout(httpConfig.connectTimeout(), TimeUnit.MILLISECONDS)
				.readTimeout(httpConfig.readTimeout(), TimeUnit.MILLISECONDS)
				.writeTimeout(httpConfig.writeTimeout(), TimeUnit.MILLISECONDS);
		if(httpConfig.authenticator() != null)
		    okHttpClientBuilder.authenticator(httpConfig.authenticator());
		if(httpConfig.applicationInterceptor() != null)
            okHttpClientBuilder.addInterceptor(httpConfig.applicationInterceptor());
        if(httpConfig.networkInterceptor() != null)
            okHttpClientBuilder.addNetworkInterceptor(httpConfig.networkInterceptor());
        if(httpConfig.onRetryOnConnectionFailure())
            okHttpClientBuilder.retryOnConnectionFailure(httpConfig.onRetryOnConnectionFailure());
        this.okHttpClient = okHttpClientBuilder.build();
	}

	/**
	 * 执行请求
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public Response execute(Request request) throws IOException {
		return okHttpClient.newCall(request).execute();
	}
}
