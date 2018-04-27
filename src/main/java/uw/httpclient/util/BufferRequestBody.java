package uw.httpclient.util;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import java.io.IOException;

/**
 * 以一个流作为源写入Socket
 *
 * @author liliang
 * @since 2018-04-27
 */
public final class BufferRequestBody {

    /**
     * Returns a new request body that transmits the content of {@code buffer}.
     *
     * @param contentType
     * @param buffer
     * @return
     */
    public static RequestBody create(final MediaType contentType, final Buffer buffer) {
        if (buffer == null) throw new NullPointerException("buffer == null");
        return new RequestBody() {
            @Override public MediaType contentType() {
                return contentType;
            }
            @Override public long contentLength() {
                return -1;
            }
            @Override public void writeTo(BufferedSink sink) throws IOException {
                // zero-copy impl,NB
                sink.write(buffer, buffer.size());
            }
        };
    }
}
