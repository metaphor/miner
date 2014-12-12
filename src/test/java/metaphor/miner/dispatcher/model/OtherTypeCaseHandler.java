package metaphor.miner.dispatcher.model;

import metaphor.miner.dispatcher.CaseHandler;

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
