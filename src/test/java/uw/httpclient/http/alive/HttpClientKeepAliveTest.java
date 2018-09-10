package uw.httpclient.http.alive;

import com.google.common.base.Stopwatch;
import okhttp3.ConnectionPool;
import org.junit.BeforeClass;
import org.junit.Test;
import uw.httpclient.http.HttpConfig;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.http.interceptor.LoggerUtil;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.httpclient.util.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * 关于单独设定KeepAlive配置
 *
 * @author liliang
 * @since 2018-09-10
 */
public class HttpClientKeepAliveTest {

    /**
     * 日志器底层均使用了Buffer.clone()方法,对性能会有影响。正常条件下,不建议在生产环境开启
     * @see okio.Buffer
     */
    private static final HttpLoggingInterceptor applicationInterceptor =
            new HttpLoggingInterceptor(new LoggerUtil("ApplicationInterceptor"));

    private static final HttpLoggingInterceptor networkInterceptor =
            new HttpLoggingInterceptor(new LoggerUtil("NetworkInterceptor"));

    /**
     * 默认的HttpInterface
     */
    private static final HttpInterface DEFAULT_CONFIG_HTTPINTERFACE = new JsonInterfaceHelper(new HttpConfig.Builder()
            .applicationInterceptor(applicationInterceptor)
            .networkInterceptor(networkInterceptor)
            .build());

    /**
     * 自定义配置的HttpInterface
     */
    private static final HttpInterface CUSTOM_HTTPINTERFACE = new JsonInterfaceHelper(new HttpConfig.Builder()
            .connectTimeout(5_000L)
            .readTimeout(5_000L)
            .writeTimeout(5_000L)
            .connectionPool(new ConnectionPool(0,1L, TimeUnit.MILLISECONDS))
            .applicationInterceptor(applicationInterceptor)
            .networkInterceptor(networkInterceptor)
            .build()
    );

    @BeforeClass
    public static void setUp() {
//        applicationInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//        networkInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    @Test
    public void testWithKeepAlive() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 100; i++) {
            String resp = DEFAULT_CONFIG_HTTPINTERFACE.getForObject("https://www.baidu.com/",String.class);
        }
        System.out.println(String.format("DEFAULT_CONFIG_HTTPINTERFACE: %s ms",stopwatch.elapsed(TimeUnit.MILLISECONDS)));
        stopwatch.reset();
        stopwatch.start();
        for (int i = 0; i < 100; i++) {
            String resp = CUSTOM_HTTPINTERFACE.getForObject("https://www.baidu.com/",String.class);
        }
        System.out.println(String.format("CUSTOM_HTTPINTERFACE: %s ms",stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }
}
