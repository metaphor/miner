package metaphor.miner.match;

import metaphor.miner.model.CallableOtherwiseHandler;
import metaphor.miner.model.OtherType;
import metaphor.miner.model.SomeType;
import metaphor.miner.model.SomeTypeCaseHandler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PatternMatchTest {

    private SomeTypeCaseHandler someTypeCaseHandler;
    private CallableOtherwiseHandler otherwiseHandler;
    private PatternMatcher patternMatcher;
    private CallableOtherwiseHandler defaultOtherwiseHandler;

    private SomeType someType;
    private OtherType otherType;

    @BeforeMethod
    public void setUp() throws Exception {
        someTypeCaseHandler = new SomeTypeCaseHandler();
        otherwiseHandler = new CallableOtherwiseHandler();
        defaultOtherwiseHandler = new CallableOtherwiseHandler();

        patternMatcher = new PatternMatcher();

        someType = new SomeType();
        otherType = new OtherType();
    }

    @Test
    public void case_handler_is_called_given_type_matched() throws Exception {
        patternMatcher.when(instanceOf(SomeType.class), someTypeCaseHandler);
        patternMatcher.match(someType);

        assertThat(someTypeCaseHandler.called(), is(true));
    }

    @Test
    public void otherwise_handler_is_called_given_nothing_matched() throws Exception {
        patternMatcher.when(instanceOf(SomeType.class), someTypeCaseHandler);
        patternMatcher.otherwise(otherwiseHandler);
        patternMatcher.match(otherType);

        assertThat(otherwiseHandler.called(), is(true));
    }

    @Test
    public void do_nothing_given_nothing_matched_and_no_otherwise_handler_specified() throws Exception {
        patternMatcher.when(instanceOf(SomeType.class), someTypeCaseHandler);
        patternMatcher.match(otherType);

        assertThat(defaultOtherwiseHandler.called(), is(false));
    }


}
