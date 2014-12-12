package metaphor.miner.dispatcher.match;

import metaphor.miner.dispatcher.model.CallableOtherwiseHandler;
import metaphor.miner.dispatcher.model.OtherType;
import metaphor.miner.dispatcher.model.SomeType;
import metaphor.miner.dispatcher.model.SomeTypeCaseHandler;
import metaphor.miner.dispatcher.Dispatcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DispatcherTest {

    private SomeTypeCaseHandler someTypeCaseHandler;
    private CallableOtherwiseHandler otherwiseHandler;
    private Dispatcher dispatcher;
    private CallableOtherwiseHandler defaultOtherwiseHandler;

    private SomeType someType;
    private OtherType otherType;

    @BeforeMethod
    public void setUp() throws Exception {
        someTypeCaseHandler = new SomeTypeCaseHandler();
        otherwiseHandler = new CallableOtherwiseHandler();
        defaultOtherwiseHandler = new CallableOtherwiseHandler();

        dispatcher = new Dispatcher();

        someType = new SomeType();
        otherType = new OtherType();
    }

    @Test
    public void case_handler_is_called_given_type_matched() throws Exception {
        dispatcher.when(instanceOf(SomeType.class), someTypeCaseHandler);
        dispatcher.dispatch(someType);

        assertThat(someTypeCaseHandler.called(), is(true));
    }

    @Test
    public void otherwise_handler_is_called_given_nothing_matched() throws Exception {
        dispatcher.when(instanceOf(SomeType.class), someTypeCaseHandler);
        dispatcher.otherwise(otherwiseHandler);
        dispatcher.dispatch(otherType);

        assertThat(otherwiseHandler.called(), is(true));
    }

    @Test
    public void do_nothing_given_nothing_matched_and_no_otherwise_handler_specified() throws Exception {
        dispatcher.when(instanceOf(SomeType.class), someTypeCaseHandler);
        dispatcher.dispatch(otherType);

        assertThat(defaultOtherwiseHandler.called(), is(false));
    }


}
