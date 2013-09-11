package metaphor.pmatch;

public class FluentPatternMatcher {

    private PatternMatcher patternMatcher;

    private FluentPatternMatcher() {
        patternMatcher = new PatternMatcher();
    }

    public static FluentPatternMatcher matcher() {
        return new FluentPatternMatcher();
    }

    public <T> FluentPatternMatcher when(Class<T> type, CaseHandler<T> caseHandler) {
        patternMatcher.onCase(type, caseHandler);
        return this;
    }

    public FluentPatternMatcher otherwise(OtherwiseHandler otherwiseHandler) {
        patternMatcher.onOtherwise(otherwiseHandler);
        return this;
    }

    public void match(Object target) {
        patternMatcher.match(target);
    }
}