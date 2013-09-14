package metaphor.miner.model;

import metaphor.miner.match.OtherwiseHandler;

public class CallableOtherwiseHandler implements OtherwiseHandler {

    private boolean called;

    @Override
    public void onOtherwise() {
        this.called = true;
    }

    public boolean called() {
        return called;
    }

}
