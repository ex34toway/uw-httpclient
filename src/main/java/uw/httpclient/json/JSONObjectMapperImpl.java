package uw.httpclient.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import uw.httpclient.http.ObjectMapper;
import uw.task.exception.MapperException;

/**
 * 基于Jackson2 的ObjectMapper
 * @author liliang
 * @since 2018-03-01
 */
public class JSONObjectMapperImpl implements ObjectMapper {

    private static final com.fasterxml.jackson.databind.ObjectMapper jsonMapper =
            new com.fasterxml.jackson.databind.ObjectMapper();

    static {
        // 默认配置
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    @Override
    public <T> T parse(String content, Class<T> classType) throws MapperException {
        try {
            return (T)jsonMapper.readValue(content,classType);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content,e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parse(String content, TypeReference<T> typeRef) throws MapperException {
        try {
            return (T)jsonMapper.readValue(content,typeRef);
        } catch (Exception e) {
            throw new MapperException(e.getMessage() + ",data: " + content,e);
        }
    }

    @Override
    public String toString(Object object) throws MapperException {
        try {
            return jsonMapper.writeValueAsString(object);
        } catch (Throwable t) {
            throw new MapperException(t);
        }
    }
}
