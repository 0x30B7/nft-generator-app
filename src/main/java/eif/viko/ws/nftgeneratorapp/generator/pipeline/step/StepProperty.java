package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

/**
 * Class representing an image processing step property
 *
 * @param <T> The type of the value of this property
 */
public class StepProperty<T> {

    private final Class<?> expectedType;
    private T value;

    public StepProperty(Class<?> expectedType) {
        this.expectedType = expectedType;
    }

    /**
     * Checks if this property has been assigned a value
     *
     * @return {@code true} if a value has been assigned
     */
    public boolean isSet() {
        return value != null;
    }

    /**
     * @return The value assigned to this property
     */
    public T get() {
        return value;
    }

    /**
     * Checks if a value of the given type can be assigned to this property
     *
     * @param type The given type
     * @return {@code true} if the property value can be assigned to this property
     */
    public boolean isCompatibleWith(Class<?> type) {
        return type.isAssignableFrom(expectedType);
    }

    /**
     * Updates the value assigned to this property
     *
     * @param value The given value
     */
    public void set(Object value) {
        this.value = (T) value;
    }

}
