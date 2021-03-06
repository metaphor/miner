package metaphor.miner.dispatcher.model;

import metaphor.miner.dispatcher.OtherwiseHandler;

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
