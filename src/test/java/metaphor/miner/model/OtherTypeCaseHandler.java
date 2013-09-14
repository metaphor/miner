package metaphor.miner.model;

import metaphor.miner.match.CaseHandler;

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
