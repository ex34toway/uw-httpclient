package uw.httpclient.util;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.Credentials;

import java.io.IOException;
import java.nio.charset.Charset;

import static okhttp3.internal.Util.ISO_8859_1;

/**
 * Http Basic 验证器
 *
 * @author liliang
 * @since 2018-04-28
 */
public class HttpBasicAuthenticator implements Authenticator {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 字符编码
     */
    private Charset charset;

    public HttpBasicAuthenticator(String username,String password,Charset charset) {
        this.username = username;
        this.password = password;
        this.charset = charset;
    }

    public HttpBasicAuthenticator(String username,String password) {
        this(username,password,ISO_8859_1);
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String credential = Credentials.basic(username, password,charset);
        return response.request().newBuilder().header("Authorization", credential).build();
    }
}
