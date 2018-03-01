package uw.httpclient.http;

import com.fasterxml.jackson.core.type.TypeReference;
import uw.httpclient.json.JSONObjectMapperImpl;
import uw.httpclient.xml.XMLObjectMapperImpl;
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

    ObjectMapper DEFAULT_JSON_MAPPER = new JSONObjectMapperImpl();

    ObjectMapper DEFAULT_XML_MAPPER = new XMLObjectMapperImpl();
}
