package metaphor.miner.dispatcher;

import org.hamcrest.Matcher;

public class DispatchPattern {

    private final Matcher matcher;
    private final CaseHandler handler;

    private DispatchPattern(final Matcher matcher, final CaseHandler handler) {
        this.matcher = matcher;
        this.handler = handler;
    }

    public static DispatchPattern matchPattern(final Matcher matcher, final CaseHandler handler) {
        return new DispatchPattern(matcher, handler);
    }

    public boolean matches(Object object) {
        return matcher.matches(object);
    }

    public void handle(Object object) {
        handler.onCase(object);
    }
}
