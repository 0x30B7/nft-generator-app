package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import java.util.Optional;

/**
 * Class representing a resource to be used by image processor steps
 */
public abstract class ProcessorStepResource<T> {

    private int id = Integer.MIN_VALUE;

    /**
     * @return An optional value of this resource
     */
    public final Optional<T> get() {
        if (isUsable()) {
            return Optional.of(provide());
        }

        return Optional.empty();
    }

    /**
     * @return The type of the value that this resource holds
     */
    public abstract Class<?> getResourceValueType();

    /**
     * @return {@code true} if the current resource may produce a value
     * using {@link ProcessorStepResource#get()}
     */
    protected abstract boolean isUsable();

    /**
     * @return The value associated with this resource
     */
    protected abstract T provide();

    /**
     * Validates the state of the resource after it has been initialized
     * and its value has been set
     *
     * @throws IllegalStateException if a property is detected to have not been
     *                               set or has an illegal value
     */
    public void validateResource() throws IllegalStateException {
        if (id == Integer.MIN_VALUE) {
            throw new IllegalStateException("'id' not assigned");
        }
    }

    /**
     * @return {@code true} if a resource id is assigned to this resource
     */
    public boolean isIdSet() {
        return id != Integer.MIN_VALUE;
    }

    /**
     * @return The resource id assigned to this resource, {@link Integer#MIN_VALUE} if
     * no such id is assigned
     */
    public final int getId() {
        return id;
    }

    /**
     * Updates the resource id assigned to this resource
     *
     * @param id The given resource id
     */
    public void setId(int id) {
        this.id = id;
    }

}

