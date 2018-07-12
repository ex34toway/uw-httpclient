package uw.httpclient.http.get;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.http.ResponseWrapper;
import uw.httpclient.json.JsonInterfaceHelper;

import java.util.List;
import java.util.Map;

/**
 * @author liliang
 * @since 2018-07-12
 */
public class GetMethodTest {

    private static final HttpInterface httpInterface = new JsonInterfaceHelper();

    @Test
    public void testGetObject() throws Exception {
        String resp = httpInterface.getForObject("http://www.baidu.com/s?wd=小说",String.class);
        System.out.println(resp);
        List<Map<String,Object>> services =
                httpInterface.getForObject("http://192.168.88.88:8500/v1/internal/ui/services?dc=dc1&token=",
                        new TypeReference<List<Map<String,Object>>>() {
        });
        System.out.println(services.size());
    }

    @Test
    public void tesGetEntity() throws Exception {
        ResponseWrapper<String> responseWrapper =
                httpInterface.getForEntity("http://www.baidu.com/s?wd=小说",String.class);
        System.out.println(responseWrapper.getValue());
        ResponseWrapper<List<Map<String,Object>>> serviceResponseWrapper =
                httpInterface.getForEntity("http://192.168.88.88:8500/v1/internal/ui/services?dc=dc1&token=",
                        new TypeReference<List<Map<String,Object>>>() {
                        });
        System.out.println(serviceResponseWrapper.getValue().size());
    }

    /**
     * 关于uriVariables的使用@Terry
     *
     * @throws Exception
     */
    @Test
    public void testGetVariablesInUri() throws Exception {
        String resp = httpInterface.getForObject("http://www.baidu.com/s?wd={searchWord}",String.class,
                "小说");
        System.out.println(resp);
    }
}
