package metaphor.pmatch;

import metaphor.pmatch.model.CallableOtherwiseHandler;
import metaphor.pmatch.model.SomeTypeCaseHandler;
import metaphor.pmatch.model.OtherType;
import metaphor.pmatch.model.SomeType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PatternMatchTest {

    private SomeTypeCaseHandler someTypeCaseHandler;
    private CallableOtherwiseHandler otherwiseHandler;
    private PatternMatcher patternMatcher;
    private CallableOtherwiseHandler defaultOtherwiseHandler;

    private SomeType someType;
    private OtherType otherType;

    @Before
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
        patternMatcher.onCase(SomeType.class, someTypeCaseHandler);
        patternMatcher.match(someType);

        assertThat(someTypeCaseHandler.called(), is(true));
    }

    @Test
    public void otherwise_handler_is_called_given_nothing_matched() throws Exception {
        patternMatcher.onCase(SomeType.class, someTypeCaseHandler);
        patternMatcher.onOtherwise(otherwiseHandler);
        patternMatcher.match(otherType);

        assertThat(otherwiseHandler.called(), is(true));
    }

    @Test
    public void do_nothing_given_nothing_matched_and_no_otherwise_handler_specified() throws Exception {
        patternMatcher.onCase(SomeType.class, someTypeCaseHandler);
        patternMatcher.match(otherType);

        assertThat(defaultOtherwiseHandler.called(), is(false));
    }


}
