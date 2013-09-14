package metaphor.miner.match;

import org.hamcrest.Matcher;

public class MatchPattern {

    private final Matcher matcher;
    private final CaseHandler handler;

    private MatchPattern(final Matcher matcher, final CaseHandler handler) {
        this.matcher = matcher;
        this.handler = handler;
    }

    public static MatchPattern matchPattern(final Matcher matcher, final CaseHandler handler) {
        return new MatchPattern(matcher, handler);
    }

    public boolean matches(Object object) {
        return matcher.matches(object);
    }

    public void handle(Object object) {
        handler.onCase(object);
    }
}
