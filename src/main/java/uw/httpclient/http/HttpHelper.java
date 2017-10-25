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

	public final static MediaType FORM_UTF8 = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
	public final static MediaType JSON_UTF8 = MediaType.parse("application/json;charset=utf-8");
	public final static MediaType FORM_GBK = MediaType.parse("application/x-www-form-urlencoded;charset=gbk");
	public final static MediaType JSON_GBK = MediaType.parse("application/json;charset=gbk");

	private static final OkHttpClient uwOkHttpClient = new OkHttpClient.Builder().build();

	private final OkHttpClient okHttpClient;

	private final boolean defaultClient;

	public HttpHelper() {
		okHttpClient = uwOkHttpClient;
		this.defaultClient = true;
	}

	public final OkHttpClient okHttpClient() {
		return uwOkHttpClient;
	}

	/**
	 * 自定义OkHttpClient配置
	 * 
	 * @return
	 */
	public HttpHelper(HttpConfig httpConfig) {
		this.okHttpClient = uwOkHttpClient.newBuilder()
				.connectTimeout(httpConfig.connectTimeout(), TimeUnit.MILLISECONDS)
				.readTimeout(httpConfig.readTimeout(), TimeUnit.MILLISECONDS)
				.writeTimeout(httpConfig.writeTimeout(), TimeUnit.MILLISECONDS).build();
		this.defaultClient = false;
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
