package uw.httpclient.http.json;

import org.junit.Test;
import uw.httpclient.http.ObjectMapper;
import uw.httpclient.http.json.vo.TestVo;

/**
 * Json解析测试
 * @author liliang
 * @since 2018-03-01
 */
public class JacksonJsonFailOnUnknownPropertiesUsageTest {

    @Test
    public void testUnknownJsonParse() throws Exception
    {
        String json = "{\"name\":\"xiaoming\",\"age\":100,\"address\":\"Beijing NO.101\",\"type\": \"1\"}";
        TestVo xiaoming = ObjectMapper.DEFAULT_JSON_MAPPER.parse(json,TestVo.class);
        xiaoming.getAddress();
    }
}
