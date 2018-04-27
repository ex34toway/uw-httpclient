package uw.httpclient.http.json;

import org.junit.Test;

import okhttp3.Request;
import okio.Buffer;
import org.apache.commons.lang.RandomStringUtils;

import uw.httpclient.http.HttpHelper;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.http.ObjectMapper;
import uw.httpclient.http.json.vo.LogInterface;
import uw.httpclient.http.json.vo.TestVo;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.httpclient.util.BufferRequestBody;

import java.util.Date;

/**
 * Json解析测试
 * @author liliang
 * @since 2018-03-01
 */
public class JacksonJsonFailOnUnknownPropertiesUsageTest {

    private static final HttpInterface httpInterface = new JsonInterfaceHelper();

    @Test
    public void testUnknownJsonParse() throws Exception
    {
        String json = "{\"name\":\"xiaoming\",\"age\":100,\"address\":\"Beijing NO.101\",\"type\": \"1\"}";
        TestVo xiaoming = ObjectMapper.DEFAULT_JSON_MAPPER.parse(json,TestVo.class);
        xiaoming.getAddress();
    }

    @Test
    public void testCreateRequestBodyFromStream() throws Exception
    {
        Buffer buffer = new Buffer();
        LogInterface logInterface2 = new LogInterface();
        logInterface2.setInterfaceType(1);
        logInterface2.setInterfaceConfigId(Long.parseLong(RandomStringUtils.randomNumeric(6)));
        logInterface2.setMchId(Long.parseLong(RandomStringUtils.randomNumeric(6)));
        logInterface2.setProductType(10);
        logInterface2.setProductId(Long.parseLong(RandomStringUtils.randomNumeric(6)));
        logInterface2.setInterfaceProductId(RandomStringUtils.randomNumeric(11));
        logInterface2.setInterfaceFunction("zwy.common.log.client.logInterface");
        logInterface2.setRequestDate(new Date());
        logInterface2.setRequestBody("你吃饭了吗?");
        logInterface2.setResponseDate(new Date());
        logInterface2.setResponseBody("吃了");

        ObjectMapper.DEFAULT_JSON_MAPPER.write(buffer.outputStream(),logInterface2);
        String url = "http://localhost:9200/zwy.common.log.client.vo.loginterface/logs";
        String resp = httpInterface.requestForObject(new Request.Builder().url(url)
                .post(BufferRequestBody.create(HttpHelper.JSON_UTF8, buffer))
                .build(), String.class);
        System.out.println(resp);
    }
}
