package metaphor.pmatch;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.hamcrest.Matcher;

import java.util.List;

import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.collect.Lists.newArrayList;
import static metaphor.pmatch.MatchPattern.matchPattern;

public class PatternMatcher {

    private final List<MatchPattern> patterns;

    private OtherwiseHandler otherwiseHandler;

    public PatternMatcher() {
        this.otherwiseHandler = new DoNothingOtherwiseHandler();
        this.patterns = newArrayList();
    }

    public <T> void when(Matcher matcher, CaseHandler<T> caseHandler) {
        patterns.add(matchPattern(matcher, caseHandler));
    }

    public void otherwise(OtherwiseHandler otherwiseHandler) {
        this.otherwiseHandler = otherwiseHandler;
    }

    public void match(final Object object) {

        Optional<MatchPattern> found = tryFind(patterns, matches(object));

        if (found.isPresent()) { found.get().handle(object); return; }

        otherwiseHandler.onOtherwise();
    }

    private Predicate<MatchPattern> matches(final Object target) {
        return new Predicate<MatchPattern>() {
            @Override
            public boolean apply(MatchPattern input) {
                return input.matches(target);
            }
        };
    }

}
