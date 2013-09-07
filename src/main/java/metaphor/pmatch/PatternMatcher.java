package metaphor.pmatch;

import java.util.HashMap;
import java.util.Map;

public class PatternMatcher {

    private Map<String, CaseHandler> cases = new HashMap<String, CaseHandler>();
    private OtherwiseHandler otherwiseHandler;

    public PatternMatcher(OtherwiseHandler otherwiseHandler) {
        this.otherwiseHandler = otherwiseHandler;
    }

    public <T> void onCase(Class<T> clazz, CaseHandler<T> caseHandler) {
        cases.put(className(clazz), caseHandler);
    }

    public void onOtherwise(OtherwiseHandler otherwiseHandler) {
        this.otherwiseHandler = otherwiseHandler;
    }

    public void match(Object target) {
        CaseHandler caseHandler = cases.get(className(target.getClass()));

        if (caseHandler != null) { caseHandler.apply(target); return; }

        otherwiseHandler.apply();
    }

    private String className(Class<?> clazz) {
        return clazz.getName();
    }

}
