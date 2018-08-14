package challenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Dispatcher {
    private ExecutorService executor;
    private List<Agent> suppliers;
    private Queue<Agent> cashier;
    private Queue<Agent> supervisor;
    private Queue<Agent> director;

    private Queue<Client> queueClients;

    public Dispatcher(int nthreads) {
        this.executor = Executors.newFixedThreadPool(nthreads);
        this.suppliers = new ArrayList<>();

        this.cashier = new LinkedList<>();
        this.supervisor = new LinkedList<>();
        this.director = new LinkedList<>();

        this.queueClients = new LinkedList<>();

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

    public void shutdown() {
        executor.shutdown();
    }

    public long getAvailable() {
        long count = this.suppliers.stream()
                .filter(callabe -> !callabe.isBusy())
                .count();
        //System.out.println("Available " + count);
        return count;
    }


   public void attend(Client client) throws ExecutionException, InterruptedException {
        if ( getAvailable() > 0 & queueClients.size() == 0) {
                this.suppliers.stream()
                        .filter(agent -> !agent.isBusy())
                        .limit(1)
                        .forEach(agent -> {
                            agent.setBusy(true);
                            agent.setAssignedClient(client);
                            CompletableFuture
                                    .supplyAsync(agent, executor)
                                    .thenAccept(System.out::println);
                        });
        }else {
            System.out.println("\n All of agents are busy \n");
            queueClients.add(client);
            while ( getAvailable() < 1){
             //   System.out.println("Waiting for be attended");
            }
            attend(queueClients.remove());
        }
    }
}

