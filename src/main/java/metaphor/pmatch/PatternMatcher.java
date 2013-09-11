package metaphor.pmatch;

import java.util.HashMap;
import java.util.Map;

public class PatternMatcher {

    private Map<String, CaseHandler<?>> cases = new HashMap<String, CaseHandler<?>>();
    private OtherwiseHandler otherwiseHandler;

    public PatternMatcher() {
        this.otherwiseHandler = new DoNothingOtherwiseHandler();
    }

    public <T> void when(Class<T> clazz, CaseHandler<T> caseHandler) {
        cases.put(className(clazz), caseHandler);
    }

    public void otherwise(OtherwiseHandler otherwiseHandler) {
        this.otherwiseHandler = otherwiseHandler;
    }

    public void match(Object target) {
        CaseHandler caseHandler = cases.get(className(target));

        if (caseHandler != null) {
            caseHandler.onCase(target);
            return;
        }

        otherwiseHandler.onOtherwise();
    }

    private String className(Class<?> clazz) {
        return clazz.getName();
    }

    private String className(Object object) {
        return className(object.getClass());
    }

}
