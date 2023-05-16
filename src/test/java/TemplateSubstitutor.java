import java.util.Map;

public class TemplateSubstitutor {
    private static final String DEFAULT_START = "#{";
    private static final String DEFAULT_END = "}";

    public static String replace(String source, Map<String, String> values) {
        if (source == null || source.isEmpty()) {
            return "";
        }
        String result = source;
        for (String key : values.keySet()) {
            final String sub = DEFAULT_START + key + DEFAULT_END;
            if (!result.contains(sub)) {
                throw new IllegalArgumentException(String.format("source does not contain '%s'", key));
            }
            result = result.replace(
                    DEFAULT_START + key + DEFAULT_END, values.get(key));
        }
        return result;
    }
}
