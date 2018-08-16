package challenge;

import java.util.concurrent.ExecutionException;

public abstract class Observer {
    public String name;
    protected ObjectPool subject;

    public abstract void update() throws ExecutionException, InterruptedException;

    public String getName(){
        return this.name;
    }
}
