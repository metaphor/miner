package metaphor.miner.match;

import org.hamcrest.Matcher;

public class FluentPatternMatcher {

    private PatternMatcher patternMatcher;

    private FluentPatternMatcher() {
        patternMatcher = new PatternMatcher();
    }

    public static FluentPatternMatcher matcher() {
        return new FluentPatternMatcher();
    }

    public <T> FluentPatternMatcher when(Matcher matcher, CaseHandler<T> caseHandler) {
        patternMatcher.when(matcher, caseHandler);
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