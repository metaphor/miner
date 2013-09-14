package metaphor.pmatch;

import org.hamcrest.Matcher;

public class MatchPattern {

    private final Matcher matcher;
    private final CaseHandler handler;

    public MatchPattern(final Matcher matcher, final CaseHandler handler) {
        this.matcher = matcher;
        this.handler = handler;
    }

    public boolean match(Object object) {
        return matcher.matches(object);
    }

    public void handle(Object object) {
        handler.onCase(object);
    }
}
