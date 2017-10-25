package uw.httpclient.http;

import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

/**
 * HttpConfig
 * @author liliang
 * @since 2017/9/21
 */
public class HttpConfig {

    /**
     * 连接超时时间 - 毫秒
     */
    private final long connectTimeout;

    /**
     * 读超时时间 - 毫秒
     */
    private final long readTimeout;

    /**
     * 写超时时间 - 毫秒
     */
    private final long writeTimeout;

    /**
     * 连接池配置
     */
    private final ConnectionPool connectionPool;

    public HttpConfig(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        connectionPool = new ConnectionPool(builder.maxIdleConnections,builder.keepAliveDuration, TimeUnit.MILLISECONDS);
    }

    public long connectTimeout(){
        return connectTimeout;
    }

    public long readTimeout(){
        return readTimeout;
    }

    public long writeTimeout(){
        return writeTimeout;
    }

    public ConnectionPool connectionPool(){
        return connectionPool;
    }

    public static class Builder {
        private long connectTimeout;
        private long readTimeout;
        private long writeTimeout;
        /**
         * 最大空闲连接数 - 默认5个
         */
        private int maxIdleConnections;

        /**
         * 存活时间 - 毫秒 - 默认5分钟
         */
        private long keepAliveDuration;

        public Builder(){
            connectTimeout = 10000;
            readTimeout = 10000;
            writeTimeout = 10000;
            maxIdleConnections = 5;
            keepAliveDuration = 300000;
        }

        public Builder connectTimeout(long connectTimeout){
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout){
            this.readTimeout = connectTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout){
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder maxIdleConnections(int maxIdleConnections){
            this.maxIdleConnections = maxIdleConnections;
            return this;
        }

        public Builder keepAliveDuration(int keepAliveDuration){
            this.keepAliveDuration = keepAliveDuration;
            return this;
        }

        public HttpConfig build(){
            return new HttpConfig(this);
        }
    }
}
