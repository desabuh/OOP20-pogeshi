package controllers.maincontroller;

import java.util.function.Supplier;


public final class SwitchControllerRequest<T, R> implements Request<T, R> {

    private Supplier<R> dataSupplier;
    private T context;


    public SwitchControllerRequest(final T context, final Supplier<R> dataSupplier) {
        this.context = context;
        this.dataSupplier = dataSupplier;
    }

    @Override
    public T getRoutingData() {
        return this.context;
    }

    @Override
    public R supplyData() {
        return (R) this.dataSupplier.get();
    }

}
