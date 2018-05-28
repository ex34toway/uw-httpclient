package uw.httpclient.util;

import com.google.common.base.Joiner;
import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Http URL 参数展开工具
 *
 * @author liliang
 * @since 2017/9/20
 */
public class StringUriTemplateUtil {

    private final static Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");

    private static final StrSubstitutor strSubstitutor = new StrSubstitutor(null, "{", "}");

    /**
     * LookUp
     *
     * @param uriVariables
     * @return
     */
    private static StrLookup vaargsLookUp(Object... uriVariables) {
        return new VaargsLookUp(uriVariables);
    }

    /**
     * expand - 注意,此expand没有考虑编码问题
     *
     * @param url
     * @param uriVariables
     * @return
     */
    public static String expand(String url, Map<String, String> uriVariables) {
        if (uriVariables != null && !uriVariables.isEmpty()) {
            url += "?";
            url += mapJoiner.join(uriVariables);
        }
        return url;
    }

    /**
     * expand - 注意,此expand没有考虑编码问题
     *
     * @param url
     * @param uriVariables
     * @return
     */
    public static String expand(String url, Object... uriVariables) {
        strSubstitutor.setVariableResolver(vaargsLookUp(uriVariables));
        return strSubstitutor.replace(url);
    }

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

            return String.valueOf(valueIterator.next());

        }
    }
}
