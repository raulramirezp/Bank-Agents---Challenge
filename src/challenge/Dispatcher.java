package challenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Dispatcher {
    private ExecutorService executor;
    private List<Agent> suppliers;

    private BlockingQueue<Agent> cashier;
    private BlockingQueue<Agent> supervisor;
    private BlockingQueue<Agent> director;
    private List<Observer> observers = new ArrayList<>();

    public Dispatcher(int nthreads){
        this.executor = Executors.newFixedThreadPool(nthreads);
        this.suppliers = new ArrayList<>();

        this.cashier = new LinkedBlockingQueue<>();
        this.supervisor = new LinkedBlockingQueue<>();
        this.director = new LinkedBlockingQueue<>();

        for (int i = 0; i < 5; i++)
            cashier.add(new Cashier("Cashier" + i));
        for (int i = 0; i < 4; i++)
            supervisor.add(new Supervisor("Supervisor" + i));
        director.add(new Director("Director"));

        for (int i = 0; i < 5; i++)
            suppliers.add(new Cashier("Cashier" + i));
        for (int i = 0; i < 4; i++)
            suppliers.add(new Supervisor("Supervisor" + i));
        suppliers.add(new Director("Director"));

    }

    public void add(Observer observer){
        this.observers.add(observer);
    }

    public void execute() throws ExecutionException, InterruptedException {
        System.out.println("Here!");
        for(Observer observer: observers)
        {
            observer.update();
            System.out.println("fefdefefef");
        }
    }
    public void shutdown() {
        executor.shutdown();
    }


    public void makeAFuture( Queue<Agent> agents, Client client){
        Agent agentForBusy = agents.remove();
        agentForBusy.setBusy(true);
        agentForBusy.setAssignedClient(client);
        CompletableFuture
                .supplyAsync(agentForBusy, executor)
                .thenAccept( response -> {
                    agents.add(response);
                    try {
                        System.out.println("Size of queue now " + agents.size());
                        this.execute();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });

    }

   public void attend(Client client) throws ExecutionException, InterruptedException {
            if(this.cashier.size() > 0){
                makeAFuture(this.cashier, client);
            } else if (this.supervisor.size() > 0)
                makeAFuture(this.supervisor, client);
            else if( this.director.size() > 0 )
                makeAFuture(this.director, client);
    }
}

