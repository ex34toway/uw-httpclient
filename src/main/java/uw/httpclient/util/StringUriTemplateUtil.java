package uw.httpclient.util;

import com.google.common.base.Joiner;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Http URL 参数展开工具
 * @author liliang
 * @since 2017/9/20
 */
public class StringUriTemplateUtil {

    private final static Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");

    private static final StrSubstitutor strSubstitutor = new StrSubstitutor(null,"{","}");

    private static class VaargsLookUp extends StrLookup {

        private final Iterator<Object> valueIterator;

        public VaargsLookUp(Object... uriVariables) {
            this.valueIterator = Arrays.asList(uriVariables).iterator();
        }

        @Override
        public String lookup(String name) {
            if (!valueIterator.hasNext()) {
                throw new IllegalArgumentException("Not enough variable values available to expand '" + name + "'");
            }
            try {
                return URLEncoder.encode(String.valueOf(valueIterator.next()), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * LookUp
     * @param uriVariables
     * @return
     */
    private static StrLookup vaargsLookUp(Object... uriVariables) {
        return new VaargsLookUp(uriVariables);
    }

    /**
     * expand - 注意,此expand没有考虑编码问题
     * @param url
     * @param uriVariables
     * @return
     */
    public static String expand(String url, Map<String, String> uriVariables) {
        uriVariables.forEach((k, v) -> {
            try {
                uriVariables.put(k, URLEncoder.encode(v, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        if(uriVariables != null && !uriVariables.isEmpty()){
            url += "?";
            url += mapJoiner.join(uriVariables);
        }
        return url;
    }

    /**
     * expand - 注意,此expand没有考虑编码问题
     * @param url
     * @param uriVariables
     * @return
     */
    public static String expand(String url, Object... uriVariables) {
        strSubstitutor.setVariableResolver(vaargsLookUp(uriVariables));
        return strSubstitutor.replace(url);
    }
}
