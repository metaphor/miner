package metaphor.miner.dispatcher.match;

import metaphor.miner.dispatcher.model.SomeType;
import org.testng.annotations.Test;

import static junit.framework.Assert.fail;
import static metaphor.miner.dispatcher.FluentDispatcher.dispatcher;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class FluentDispatcherLambdaTest {

    @Test
    public void fluent_api_example_match_some_type() throws Exception {
        dispatcher()
                .when(instanceOf(SomeType.class), someType -> assertThat(someType, notNullValue()))
                .otherwise(() -> fail("should not go otherwise"))
                .dispatch(new SomeType());
    }
}
