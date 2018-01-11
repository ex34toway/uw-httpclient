package uw.httpclient.http.xml;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.common.collect.Maps;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.http.ObjectMapper;
import uw.httpclient.http.ResponseWrapper;
import uw.httpclient.http.xml.vo.LvmamaPriceInfoVoErrorVo;
import uw.httpclient.http.xml.vo.SessionVo;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.task.exception.MapperException;
import uw.task.exception.TaskPartnerException;

import java.io.IOException;
import java.util.Map;

/**
 * 解析Xml中遇到的问题
 * @author liliang
 * @since 2017/12/13
 */
public class JacksonXmlFailOnUnknownPropertiesUsageTest {

    /**
     * 接口请求失败的响应
     */
    private static final String errorResp = "<response>\n" +
            "    <state>\n" +
            "        <code>33</code>\n" +
            "        <message>非法的参数</message>\n" +
            "        <solution>请查看根据服务接口对参数格式的要求</solution>\n" +
            "        <subErrors>\n" +
            "            <subError>\n" +
            "                <code>isv.invalid-paramete:goodsIds</code>\n" +
            "                <message><![CDATA[参数goodsIds无效，格式不对、非法值、越界等]]></message>\n" +
            "            </subError>\n" +
            "        </subErrors>\n" +
            "    </state>\n" +
            "</response>";

    /**
     * 这是因为Vo的参数没有Mapping到相关的节点就会抛这样的异常
     *
     * @throws IOException
     * @see com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException:
     * Unrecognized field "subErrors" (class uw.httpclient.http.xml.vo.LvmamaPriceInfoVo$State),
     * not marked as ignorable (3 known properties: "solution", "code", "message"])
     */
    @Test(expected = UnrecognizedPropertyException.class)
    public void testThrowJsonMappingException() throws IOException {
        com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();
        LvmamaPriceInfoVoErrorVo response = xmlMapper.readValue(errorResp, LvmamaPriceInfoVoErrorVo.class);
    }

    /**
     * 关闭 - 遇到一个未知节点解析抛JsonMappingException [默认是开启的]
     *
     * @throws IOException
     */
    @Test
    public void testParseXml() throws IOException {
        com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        LvmamaPriceInfoVoErrorVo response = xmlMapper.readValue(errorResp, LvmamaPriceInfoVoErrorVo.class);
        assertEquals("33", response.getState().getCode());
    }

    /**
     * 解析带命名空间的Xml
     *
     * @throws MapperException
     */
    @Test
    public void testParseNamespace() throws MapperException {
        String content = "<?xml version=\"1.0\"?>\n" +
                "<Response xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <SessionId>636510883907692029</SessionId>\n" +
                "    <Code>2</Code>\n" +
                "    <Result i:nil=\"false\" />\n" +
                "    <Error>DateVisit parameter is invalid.</Error>\n" +
                "</Response>";
        SessionVo sessionVo = ObjectMapper.DEFAULT_XML_MAPPER.parse(content, SessionVo.class);
    }

    @Test
    public void testGet() throws TaskPartnerException {
        HttpInterface httpInterface = new JsonInterfaceHelper();
        String resp =
                httpInterface.getForObject("http://www.zowoyoo.com",String.class);
        System.out.println(resp);
    }

    @Test
    public void testPostForm() throws TaskPartnerException {
        HttpInterface httpInterface = new JsonInterfaceHelper();
        Map<String,String> loginParam = Maps.newHashMap();
        loginParam.put("username","test");
        loginParam.put("password","test");
        ResponseWrapper<String> responseWrapper =
                httpInterface.postForEntity("http://localhost:8087/cookie/test",String.class,loginParam);
        System.out.println(responseWrapper.getResponseBody());
    }
}
