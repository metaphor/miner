package metaphor.miner.match;

import metaphor.miner.model.CallableOtherwiseHandler;
import metaphor.miner.model.OtherType;
import metaphor.miner.model.OtherTypeCaseHandler;
import metaphor.miner.model.SomeType;
import metaphor.miner.model.SomeTypeCaseHandler;
import metaphor.miner.model.ThirdType;
import org.junit.Before;
import org.junit.Test;

import static metaphor.miner.match.FluentPatternMatcher.matcher;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FluentPatternMatcherTest {

    private SomeTypeCaseHandler someTypeCaseHandler;
    private OtherTypeCaseHandler otherTypeCaseHandler;
    private CallableOtherwiseHandler otherwiseHandler;
    private SomeType someType;
    private OtherType otherType;
    private ThirdType target;

    @Before
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

        matcher()
                .when(instanceOf(SomeType.class), someTypeCaseHandler)
                .when(instanceOf(OtherType.class), otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .match(someType);

        assertThat(someTypeCaseHandler.called(), is(true));
    }

    @Test
    public void fluent_api_example_match_other_type() throws Exception {

        matcher()
                .when(instanceOf(SomeType.class), someTypeCaseHandler)
                .when(instanceOf(OtherType.class), otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .match(otherType);

        assertThat(otherTypeCaseHandler.called(), is(true));
    }

    @Test
    public void fluent_api_example_match_nothing() throws Exception {

        matcher()
                .when(instanceOf(SomeType.class), someTypeCaseHandler)
                .when(instanceOf(OtherType.class), otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .match(target);

        assertThat(otherwiseHandler.called(), is(true));
    }
}
