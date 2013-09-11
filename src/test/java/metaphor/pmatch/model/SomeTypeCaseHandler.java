package metaphor.pmatch.model;

import metaphor.pmatch.CaseHandler;

public class SomeTypeCaseHandler implements CaseHandler<SomeType> {

    private boolean called;

    @Override
    public void onCase(SomeType someType) {
        this.called = true;
    }

    public boolean called() {
        return called;
    }

}
