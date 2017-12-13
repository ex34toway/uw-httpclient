package uw.httpclient.http.xml;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import uw.httpclient.http.xml.vo.LvmamaPriceInfoVoErrorVo;

import java.io.IOException;

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
     * @see com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException:
     * Unrecognized field "subErrors" (class uw.httpclient.http.xml.vo.LvmamaPriceInfoVo$State),
     * not marked as ignorable (3 known properties: "solution", "code", "message"])
     * @throws IOException
     */
    @Test(expected = UnrecognizedPropertyException.class)
    public void testThrowJsonMappingException() throws IOException {
        com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();
        LvmamaPriceInfoVoErrorVo response = xmlMapper.readValue(errorResp,LvmamaPriceInfoVoErrorVo.class);
    }

    /**
     * 关闭 - 遇到一个未知节点解析抛JsonMappingException [默认是开启的]
     * @throws IOException
     */
    @Test
    public void testParseXml() throws IOException {
        com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        LvmamaPriceInfoVoErrorVo response = xmlMapper.readValue(errorResp,LvmamaPriceInfoVoErrorVo.class);
        assertEquals("33",response.getState().getCode());
    }
}
