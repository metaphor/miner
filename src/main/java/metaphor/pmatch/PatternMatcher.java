package metaphor.pmatch;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.hamcrest.Matcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.collect.Lists.newArrayList;

public class PatternMatcher {

    private Map<String, CaseHandler<?>> cases = new HashMap<String, CaseHandler<?>>();
    private OtherwiseHandler otherwiseHandler;

    private List<MatchPattern> patterns;

    public PatternMatcher() {
        this.otherwiseHandler = new DoNothingOtherwiseHandler();
        this.patterns = newArrayList();
    }

    public <T> void when(Matcher matcher, CaseHandler<T> caseHandler) {
        patterns.add(new MatchPattern(matcher, caseHandler));
    }

    public void otherwise(OtherwiseHandler otherwiseHandler) {
        this.otherwiseHandler = otherwiseHandler;
    }

    public void match(final Object target) {

        Optional<MatchPattern> found = tryFind(patterns, matches(target));

        if (found.isPresent()) { found.get().handle(target); return; }

        otherwiseHandler.onOtherwise();
    }

    private Predicate<MatchPattern> matches(final Object target) {
        return new Predicate<MatchPattern>() {
            @Override
            public boolean apply(MatchPattern input) {
                return input.match(target);
            }
        };
    }

}
