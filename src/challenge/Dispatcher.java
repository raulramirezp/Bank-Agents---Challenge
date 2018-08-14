package challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Dispatcher implements Supplier {
    private ExecutorService executor;
    private List<Agent> suppliers;

    public Dispatcher( int nthreads){
        this.executor = Executors.newFixedThreadPool(nthreads);
        this.suppliers = new ArrayList<>();

        for( int i = 0 ; i < 6; i++)
            suppliers.add(new Cashier("Cashier"+i));
        for( int i = 0 ; i < 3; i++)
            suppliers.add(new Supervisor("Supervisor"+i));
        suppliers.add(new Director("Director"));
    }

    public void shutdown(){
        executor.shutdown();
    }

    public void printAvailable(){
        long count = suppliers.stream()
                .filter( callabe -> callabe.isBusy() == false)
                .count();
        System.out.println("Disponibles "+ count);
    }

    public void attend( Client client) throws ExecutionException, InterruptedException {
        printAvailable();
        for(int i = 0; i < suppliers.size(); i++){
            Agent currentAgent = suppliers.get(i);

            if( !currentAgent.isBusy()){
                currentAgent.setBusy(true);
                currentAgent.setAssignedClient(client);
                System.out.println("Asignado " + currentAgent.getAssignedClient().getName());


                CompletableFuture
                        .supplyAsync(this, executor)
                        .thenAccept(response -> {
                            System.out.println(response);
                        });
                return;
            }
        }

        System.out.println("\n All of agents are busy \n");
    }

    @Override
    public String get() {
        Agent currentAgent = suppliers.get(this.index);

        System.out.println("Start client operation ");
        //2.Generate random time of service

        try {
            Thread.sleep(currentAgent.generateAtentionTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Time for this thread " + currentAgent.getAtentionTime() + " thread name " + Thread.currentThread().getName());
        System.out.println(" The client is + " + currentAgent.getAssignedClient().getName() );
        System.out.println(  " finish in the " + currentAgent.getName() );
        currentAgent.setAssignedClient(null);
        currentAgent.setBusy(false);
        return ("Finish " + Thread.currentThread().getName());
    }
}

