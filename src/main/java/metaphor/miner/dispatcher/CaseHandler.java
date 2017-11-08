package metaphor.miner.dispatcher;

@FunctionalInterface
public interface CaseHandler<T> {

    void onCase(T type);
}
