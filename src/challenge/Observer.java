package challenge;

import java.util.concurrent.ExecutionException;

abstract class Observer {
    protected Dispatcher subject;
    public abstract void update() throws ExecutionException, InterruptedException;
}
