package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {
    ExecutorService executor;
    List<Agent> callables;

    public Dispatcher( int nthreads){
        this.executor = Executors.newFixedThreadPool(nthreads);
        this.callables = new ArrayList<>();

        for( int i = 0 ; i < 6; i++)
            callables.add(new Cashier("Cashier"+i));
        for( int i = 0 ; i < 3; i++)
            callables.add(new Cashier("Supervisor"+i));
        callables.add(new Director("Director"));
    }

    public void attend( Client client){
        //for()
    }
}
