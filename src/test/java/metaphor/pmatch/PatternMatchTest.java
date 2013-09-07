package metaphor.pmatch;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PatternMatchTest {

    private SomeTypeCaseHandler someTypeCaseHandler;
    private SomeOtherwiseHandler otherwiseHandler;
    private PatternMatcher patternMatcher;
    private SomeOtherwiseHandler defaultOtherwiseHandler;

    private SomeType someType;
    private PatternMatchTest.OtherType otherType;

    @Before
    public void setUp() throws Exception {
        someTypeCaseHandler = new SomeTypeCaseHandler();
        otherwiseHandler = new SomeOtherwiseHandler();
        defaultOtherwiseHandler = new SomeOtherwiseHandler();

        patternMatcher = new PatternMatcher(defaultOtherwiseHandler);

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
    public void default_otherwise_handler_is_called_given_no_otherwise_specified() throws Exception {
        patternMatcher.onCase(SomeType.class, someTypeCaseHandler);
        patternMatcher.match(otherType);

        assertThat(defaultOtherwiseHandler.called(), is(true));
    }


    class SomeTypeCaseHandler implements CaseHandler<SomeType> {

        private boolean called;

        @Override
        public void apply(SomeType someType) {
            this.called = true;
        }

        public boolean called() {
            return called;
        }

    }


    class SomeOtherwiseHandler implements OtherwiseHandler {

        private boolean called;

        @Override
        public void apply() {
            this.called = true;
        }

        public boolean called() {
            return called;
        }

    }

    class SomeType {

    }

    class OtherType {

    }
}
