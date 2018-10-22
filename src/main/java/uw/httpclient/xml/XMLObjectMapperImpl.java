package uw.httpclient.xml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uw.httpclient.http.ObjectMapper;
import uw.task.exception.MapperException;

import java.io.OutputStream;

/**
 * 基于Jackson Xml 的ObjectMapper
 * @author liliang
 * @since 2018-03-01
 */
public class XMLObjectMapperImpl implements ObjectMapper {

    private final static com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = xmlMapperInit();

    /**
     * 初始化xmlMapper
     *
     * @return
     */
    private static com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapperInit() {
        com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper =
                new com.fasterxml.jackson.dataformat.xml.XmlMapper();
        // Java时间模块
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return xmlMapper;
    }

    /**
     * Java 泛型绑定
     * @param parametrized
     * @param parameterClasses
     * @return
     */
    @Override
    public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return xmlMapper.getTypeFactory().constructParametricType(parametrized,parameterClasses);
    }

    @Override
    public <T> T parse(String content, Class<T> classType) throws MapperException {
        try {
            return (T) xmlMapper.readValue(content, classType);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parse(String content, TypeReference<T> typeRef) throws MapperException {
        try {
            return (T) xmlMapper.readValue(content, typeRef);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content, e);
        }
    }

    @Override
    public <T> T parse(String content,JavaType type) throws MapperException {
        try {
            return xmlMapper.readValue(content, type);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content, e);
        }
    }

    @Override
    public void write(OutputStream out, Object value) throws MapperException {
        try {
            xmlMapper.writeValue(out, value);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + value, e);
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
