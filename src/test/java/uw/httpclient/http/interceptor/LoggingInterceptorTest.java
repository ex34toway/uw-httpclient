package uw.httpclient.http.interceptor;

import org.junit.Test;
import uw.httpclient.http.HttpConfig;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.httpclient.util.HttpLoggingInterceptor;

/**
 * TestLoggingInterceptor
 * @author liliang
 * @since 2018-03-26
 */
public class LoggingInterceptorTest {

    /**
     * 日志器底层均使用了Buffer.clone()方法,对性能会有影响。正常条件下,不建议在生产环境开启
     * @see okio.Buffer
     */
    private static final HttpLoggingInterceptor applicationInterceptor =
            new HttpLoggingInterceptor(new LoggerUtil("ApplicationInterceptor"));

    private static final HttpLoggingInterceptor networkInterceptor =
            new HttpLoggingInterceptor(new LoggerUtil("NetworkInterceptor"));

    private static final HttpInterface testHttpInterface =
            new JsonInterfaceHelper(new HttpConfig.Builder()
                    .applicationInterceptor(applicationInterceptor)
                    .networkInterceptor(networkInterceptor).build());

    @Test
    public void testLoggingBasic() throws Exception {
        applicationInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        networkInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        String resp = testHttpInterface.getForObject("http://www.zowoyoo.com",String.class);
    }

    @Test
    public void testLoggingHeader() throws Exception {
        applicationInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        networkInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        String resp = testHttpInterface.getForObject("http://www.zowoyoo.com",String.class);
    }

    @Test
    public void testLoggingBody() throws Exception {
        applicationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        networkInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        String resp = testHttpInterface.getForObject("http://www.zowoyoo.com",String.class);
    }
}
