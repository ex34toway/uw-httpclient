package uw.httpclient.http.interceptor;

import uw.httpclient.util.HttpLoggingInterceptor;

/**
 * LoggerUtil
 * @author liliang
 * @since 2018-03-26
 */
public final class LoggerUtil implements HttpLoggingInterceptor.Logger {

    private String loggerName;

    public LoggerUtil(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public void log(String message) {
        System.out.println(loggerName +" -- "+message);
    }
}
