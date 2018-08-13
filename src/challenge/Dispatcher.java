package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Dispatcher {
    private ExecutorService executor;
    private List<Agent> callables;

    public Dispatcher( int nthreads){
        this.executor = Executors.newFixedThreadPool(nthreads);
        this.callables = new ArrayList<>();

        for( int i = 0 ; i < 6; i++)
            callables.add(new Cashier("Cashier"+i));
        for( int i = 0 ; i < 3; i++)
            callables.add(new Cashier("Supervisor"+i));
        callables.add(new Director("Director"));
    }


    public void printAvailable(){
        long count = callables.stream()
                .filter( callabe -> callabe.isBusy() == false)
                .count();
        System.out.println("Disponibles "+ count);
    }

    public void attend( Client client) throws ExecutionException, InterruptedException {
        printAvailable();
        for(Agent callable : callables){
            if( !callable.isBusy()){
                callable.setAssignedClient(client);
                Future<String> future = executor.submit( callable);
                String result = future.get();
                System.out.println( result );
                return;
            }

        }

        System.out.println("\n All of agents are busy \n");
    }
}
