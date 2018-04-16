package uw.httpclient.http;

import okhttp3.Interceptor;

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
     * application interceptor
     */
    private final Interceptor applicationInterceptor;

    /**
     * network interceptor
     */
    private final Interceptor networkInterceptor;

    public HttpConfig(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.applicationInterceptor = builder.applicationInterceptor;
        this.networkInterceptor = builder.networkInterceptor;
    }

    public long connectTimeout() {
        return connectTimeout;
    }

    public long readTimeout() {
        return readTimeout;
    }

    public long writeTimeout() {
        return writeTimeout;
    }

    public Interceptor applicationInterceptor() {
        return applicationInterceptor;
    }

    public Interceptor networkInterceptor() {
        return networkInterceptor;
    }

    public static class Builder {
        /**
         * 连接超时时间 - 毫秒
         */
        private long connectTimeout;

        /**
         * 读超时时间 - 毫秒
         */
        private long readTimeout;

        /**
         * 写超时时间 - 毫秒
         */
        private long writeTimeout;

        /**
         * application interceptor
         */
        private Interceptor applicationInterceptor;

        /**
         * network interceptor
         */
        private Interceptor networkInterceptor;

        public Builder() {
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = connectTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder applicationInterceptor(Interceptor applicationInterceptor) {
            this.applicationInterceptor = applicationInterceptor;
            return this;
        }

        public Builder networkInterceptor(Interceptor networkInterceptor) {
            this.networkInterceptor = networkInterceptor;
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }
    }
}
