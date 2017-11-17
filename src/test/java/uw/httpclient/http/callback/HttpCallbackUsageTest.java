package uw.httpclient.http.callback;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uw.httpclient.http.*;
import uw.httpclient.http.exception.InterfaceException;
import uw.httpclient.http.exception.MapperException;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.httpclient.util.NoReturnClass;

/**
 * 回调的使用的示例
 * @author liliang
 * @since 2017/11/17
 */
public class HttpCallbackUsageTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpCallbackUsageTest.class);

    /**
     * 正常返回Vo
     */
    public class ResponseResult {
        /**
         * biz id
         */
        private long orderId;

        /**
         * biz name
         */
        private String userName;

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    /**
     * 错误返回Vo
     */
    public static class ErrorVo {

        /**
         * 状态码
         */
        private int status;

        /**
         * 错误消息
         */
        private String msg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 错误处理回调
     */
    public class ErrorCallBack implements HttpMessageCallBack {

        /**
         * 外部接口错误判定码
         */
        private int errorStatus;

        /**
         * 响应内容
         */
        private String response;

        /**
         * 响应Vo
         */
        private ErrorVo errorVo;

        public int getErrorStatus() {
            return errorStatus;
        }

        public void setErrorStatus(int errorStatus) {
            this.errorStatus = errorStatus;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public ErrorVo getErrorVo() {
            return errorVo;
        }

        public void setErrorVo(ErrorVo errorVo) {
            this.errorVo = errorVo;
        }

        @Override
        public String doConvert(String content) {
            try {
                // 进行业务处理
                // biz logic

                // 判定成功,写入返回
                errorStatus = 1;
                response = content;
                this.errorVo = ObjectMapper.DEFAULT_JSON_MAPPER.parse(content,ErrorVo.class);
            } catch (MapperException e) {
                // 判定失败
                logger.error(e.getMessage(),e);
                return content;
            }

            // 已经判定有错误,make uw-httpclient happy
            return "";
        }
    }

    /**
     * 一个下单的接口调用
     * 发生错误与下单正常返回不同的Vo的处理方法
     * NoReturnClass.class 用来标识 uw-httpclient是否还需要parse
     * @throws InterfaceException
     */
    @Test
    public void testCallback() throws InterfaceException {
        ErrorCallBack errorCallBack = new ErrorCallBack();
        HttpInterface httpInterface = new JsonInterfaceHelper(errorCallBack);
        TypeReference<ResponseResult> typeRef = new TypeReference<ResponseResult>(){};
        httpInterface.postForEntity("http://localhost:8080/test/addOrder",
                NoReturnClass.class, "{\"orderId\": 1,\"userName\": \"test\"}", HttpHelper.JSON_UTF8);
        // 业务Vo
        int errorStatus = errorCallBack.getErrorStatus();
        ErrorVo errorVo = errorCallBack.getErrorVo();
    }
}
