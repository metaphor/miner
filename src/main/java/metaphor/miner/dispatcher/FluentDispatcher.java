package metaphor.miner.dispatcher;

import org.hamcrest.Matcher;

public class FluentDispatcher {

    private Dispatcher dispatcher;

    private FluentDispatcher() {
        dispatcher = new Dispatcher();
    }

    public static FluentDispatcher dispatcher() {
        return new FluentDispatcher();
    }

    public <T> FluentDispatcher when(Matcher matcher, CaseHandler<T> caseHandler) {
        dispatcher.when(matcher, caseHandler);
        return this;
    }

    public FluentDispatcher otherwise(OtherwiseHandler otherwiseHandler) {
        dispatcher.otherwise(otherwiseHandler);
        return this;
    }

    public void dispatch(Object target) {
        dispatcher.dispatch(target);
    }
}