package metaphor.miner.dispatcher.match;

import metaphor.miner.dispatcher.model.SomeType;
import metaphor.miner.dispatcher.model.SomeTypeCaseHandler;
import metaphor.miner.dispatcher.model.CallableOtherwiseHandler;
import metaphor.miner.dispatcher.model.OtherType;
import metaphor.miner.dispatcher.model.OtherTypeCaseHandler;
import metaphor.miner.dispatcher.model.ThirdType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static metaphor.miner.dispatcher.FluentDispatcher.dispatcher;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FluentDispatcherTest {

    private SomeTypeCaseHandler someTypeCaseHandler;
    private OtherTypeCaseHandler otherTypeCaseHandler;
    private CallableOtherwiseHandler otherwiseHandler;
    private SomeType someType;
    private OtherType otherType;
    private ThirdType target;

    @BeforeMethod
    public void setUp() throws Exception {
        someTypeCaseHandler = new SomeTypeCaseHandler();
        otherTypeCaseHandler = new OtherTypeCaseHandler();
        otherwiseHandler = new CallableOtherwiseHandler();
        someType = new SomeType();
        otherType = new OtherType();
        target = new ThirdType();
    }

    @Test
    public void fluent_api_example_match_some_type() throws Exception {

        dispatcher()
                .when(instanceOf(SomeType.class), someTypeCaseHandler)
                .when(instanceOf(OtherType.class), otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .dispatch(someType);

        assertThat(someTypeCaseHandler.called(), is(true));
    }

    @Test
    public void fluent_api_example_match_other_type() throws Exception {

        dispatcher()
                .when(instanceOf(SomeType.class), someTypeCaseHandler)
                .when(instanceOf(OtherType.class), otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .dispatch(otherType);

        assertThat(otherTypeCaseHandler.called(), is(true));
    }

    @Test
    public void fluent_api_example_match_nothing() throws Exception {

        dispatcher()
                .when(instanceOf(SomeType.class), someTypeCaseHandler)
                .when(instanceOf(OtherType.class), otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .dispatch(target);

        assertThat(otherwiseHandler.called(), is(true));
    }
}
