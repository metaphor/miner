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
        patternMatcher.when(type, caseHandler);
        return this;
    }

    public FluentPatternMatcher otherwise(OtherwiseHandler otherwiseHandler) {
        patternMatcher.otherwise(otherwiseHandler);
        return this;
    }

    public void match(Object target) {
        patternMatcher.match(target);
    }
}