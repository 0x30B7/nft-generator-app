package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import java.util.Optional;

public abstract class ProcessorStepResource<T> {

    private int id = Integer.MIN_VALUE;

    public final Optional<T> get() {
        if (isUsable()) {
            return Optional.of(provide());
        }

        return Optional.empty();
    }

    public abstract Class<?> getResourceValueType();

    protected abstract boolean isUsable();

    protected abstract T provide();

    public void validateResource() throws IllegalStateException {
        if (id == Integer.MIN_VALUE) {
            throw new IllegalStateException("'id' not assigned");
        }
    }

    public boolean isIdSet() {
        return id != Integer.MIN_VALUE;
    }

    public final int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

