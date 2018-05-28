package uw.httpclient.http;

import uw.task.exception.MapperException;

/**
 * Http接口调用成功回调
 * @author liliang
 * @since 2017/10/10
 */
@FunctionalInterface
public interface HttpMessageCallBack {

    /**
     * 返回内容转换,比如解析,解密etc
     * @param content
     * @return
     */
    String doConvert(String content) throws MapperException ;
}
