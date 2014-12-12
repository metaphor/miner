package metaphor.miner.dispatcher;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.hamcrest.Matcher;

import java.util.List;

import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.collect.Lists.newArrayList;
import static metaphor.miner.dispatcher.DispatchPattern.matchPattern;

public class Dispatcher {

    private final List<DispatchPattern> patterns;

    private OtherwiseHandler otherwiseHandler;

    public Dispatcher() {
        this.otherwiseHandler = new DoNothingOtherwiseHandler();
        this.patterns = newArrayList();
    }

    public <T> void when(Matcher matcher, CaseHandler<T> caseHandler) {
        patterns.add(matchPattern(matcher, caseHandler));
    }

    public void otherwise(OtherwiseHandler otherwiseHandler) {
        this.otherwiseHandler = otherwiseHandler;
    }

    public void dispatch(final Object object) {

        Optional<DispatchPattern> found = tryFind(patterns, matches(object));

        if (found.isPresent()) { found.get().handle(object); return; }

        otherwiseHandler.onOtherwise();
    }

    private Predicate<DispatchPattern> matches(final Object target) {
        return new Predicate<DispatchPattern>() {
            @Override
            public boolean apply(DispatchPattern input) {
                return input.matches(target);
            }
        };
    }

}
