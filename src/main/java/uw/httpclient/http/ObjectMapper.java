package uw.httpclient.http;

import com.fasterxml.jackson.core.type.TypeReference;
import uw.task.exception.MapperException;

/**
 * 对象Mapper
 * @author liliang
 * @since 2017/9/20
 */
public interface ObjectMapper {

    /**
     * 解析
     * @param content
     * @param cls
     * @param <T>
     * @return
     */
    <T> T parse(String content, Class<T> cls) throws MapperException;

    /**
     * 解析
     * @param content
     * @param typeRef
     * @param <T>
     * @return
     */
    <T> T parse(String content, TypeReference<T> typeRef) throws MapperException;

    /**
     * 转Json
     * @param object
     * @return
     */
    String toString(Object object) throws MapperException;

    ObjectMapper DEFAULT_JSON_MAPPER = new ObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        @Override
        public <T> T parse(String content, Class<T> classType) throws MapperException {
            try {
                return (T)objectMapper.readValue(content,classType);
            } catch (Exception e) {
                throw new MapperException(e.getMessage() + ",data: " + content,e);
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T parse(String content, TypeReference<T> typeRef) throws MapperException {
            try {
                return (T)objectMapper.readValue(content,typeRef);
            } catch (Exception e) {
                throw new MapperException(e.getMessage() + ",data: " + content,e);
            }
        }

        @Override
        public String toString(Object object) throws MapperException {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (Throwable t) {
                throw new MapperException(t);
            }
        }
    };

    ObjectMapper DEFAULT_XML_MAPPER = new ObjectMapper() {
        com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();
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
    };
}
