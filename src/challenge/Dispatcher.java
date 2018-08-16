package challenge;

import java.util.concurrent.*;

public class Dispatcher extends Observer {

    private ExecutorService executor;
    private BlockingQueue<Client> queueClients;
    private ObjectPool agents;


    public Dispatcher(int nthreads){
        this.name = "Dispatcher";
        this.queueClients = new LinkedBlockingQueue<>();
        this.agents = new ObjectPool();

        this.subject = agents;
        this.subject.add(this);

        this.executor = Executors.newFixedThreadPool(nthreads);

    }


    @Override
    public void update()  {
        System.out.println("Clients in Queue:\t" + this.queueClients.size());
        if( this.queueClients.size() >0 )
            this.attend(this.queueClients.remove());
        else
            this.shutdown();
    }


    public void shutdown() {
        executor.shutdown();
    }


   public void attend(Client client)  {
        if(this.agents.cashierSize() > 0){
            Agent agentForBusy = agents.removeFromCashier();
            agentForBusy.setAssignedClient(client);
            CompletableFuture
                        .supplyAsync(agentForBusy, executor)
                        .thenAccept( response -> {
                            try {
                                agents.addToCashier(response);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });

        }else if (this.agents.supervisorSize() > 0){
            Agent agentForBusy = agents.removeFromSupervisor();
            agentForBusy.setAssignedClient(client);
            CompletableFuture
                        .supplyAsync(agentForBusy, executor)
                        .thenAccept( response -> {
                            try {
                                agents.addToSupervisor(response);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
        }

        else if( this.agents.directorSize() > 0 ){
            Agent agentForBusy = agents.removeFromDirector();
            agentForBusy.setAssignedClient(client);
            CompletableFuture
                        .supplyAsync(agentForBusy, executor)
                        .thenAccept( response -> {
                            try {
                                agents.addToDirector((response));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
        }
        else
            queueClients.add(client);
    }
}

