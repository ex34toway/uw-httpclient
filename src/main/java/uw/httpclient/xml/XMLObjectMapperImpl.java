package uw.httpclient.xml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import uw.httpclient.http.ObjectMapper;
import uw.task.exception.MapperException;

/**
 * 基于Jackson Xml 的ObjectMapper
 * @author liliang
 * @since 2018-03-01
 */
public class XMLObjectMapperImpl implements ObjectMapper {

    private final static com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper =
            new com.fasterxml.jackson.dataformat.xml.XmlMapper();

    static {
        // 默认配置
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    @Override
    public <T> T parse(String content, Class<T> classType) throws MapperException {
        try {
            return (T)xmlMapper.readValue(content,classType);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content,e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parse(String content, TypeReference<T> typeRef) throws MapperException {
        try {
            return (T)xmlMapper.readValue(content,typeRef);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content,e);
        }
    }

    @Override
    public String toString(Object object) throws MapperException {
        try {
            return xmlMapper.writeValueAsString(object);
        } catch (Throwable t) {
            throw new MapperException(t);
        }
    }
}
