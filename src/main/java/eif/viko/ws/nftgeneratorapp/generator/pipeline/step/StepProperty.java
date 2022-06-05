package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

public class StepProperty<T> {

    private final Class<?> expectedType;
    private T value;

    public StepProperty(Class<?> expectedType) {
        this.expectedType = expectedType;
    }

    public boolean isSet() {
        return value != null;
    }

    public T get() {
        return value;
    }

    public boolean isCompatibleWith(Class<?> type) {
        return type.isAssignableFrom(expectedType);
    }

    public void set(Object value) {
        this.value = (T) value;
    }

}
