package metaphor.pmatch.model;

import metaphor.pmatch.CaseHandler;

public class OtherTypeCaseHandler implements CaseHandler<OtherType> {

    private boolean called;

    @Override
    public void onCase(OtherType otherType) {
        this.called = true;
    }

    public boolean called() {
        return called;
    }

}
