package challenge;
import java.util.Random;
import java.util.concurrent.Callable;

public abstract class Agent implements Callable {
    int id;
    boolean isBusy;
    String name;
    Client assignedClient;
    Long atentionTime;

    public Agent(String name ){
        this.name = name;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public Agent(int id, Client assignedClient){
        this.id = id;
        this.assignedClient = assignedClient;
    }
    public boolean getStatus(){
        return this.isBusy;
    }

    public Long getAtentionTime(){
        return this.atentionTime;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(" The client + " + this.assignedClient.getName() );
        Random r = new Random();
        this.atentionTime = 10000 + (15000 - 10000) * r.nextLong();
        Thread.sleep(atentionTime);
        return callMethod();
    }
    public abstract String callMethod();

}
