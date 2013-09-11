package metaphor.pmatch;

import metaphor.pmatch.model.CallableOtherwiseHandler;
import metaphor.pmatch.model.OtherType;
import metaphor.pmatch.model.OtherTypeCaseHandler;
import metaphor.pmatch.model.SomeType;
import metaphor.pmatch.model.SomeTypeCaseHandler;
import metaphor.pmatch.model.ThirdType;
import org.junit.Before;
import org.junit.Test;

import static metaphor.pmatch.FluentPatternMatcher.matcher;
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
                .when(SomeType.class, someTypeCaseHandler)
                .when(OtherType.class, otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .match(someType);

        assertThat(someTypeCaseHandler.called(), is(true));
    }

    @Test
    public void fluent_api_example_match_other_type() throws Exception {

        matcher()
                .when(SomeType.class, someTypeCaseHandler)
                .when(OtherType.class, otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .match(otherType);

        assertThat(otherTypeCaseHandler.called(), is(true));
    }

    @Test
    public void fluent_api_example_match_nothing() throws Exception {

        matcher()
                .when(SomeType.class, someTypeCaseHandler)
                .when(OtherType.class, otherTypeCaseHandler)
                .otherwise(otherwiseHandler)
                .match(target);

        assertThat(otherwiseHandler.called(), is(true));
    }
}
