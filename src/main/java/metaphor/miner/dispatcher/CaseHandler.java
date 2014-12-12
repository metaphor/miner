package metaphor.miner.dispatcher;

public interface CaseHandler<T> {

    public void onCase(T type);
}
