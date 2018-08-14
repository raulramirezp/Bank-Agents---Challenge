package challenge;
import java.util.Random;
//import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Agent  {
    private int id;
    private boolean isBusy;
    private String name;
    private Client assignedClient;
    private Long atentionTime;

    public Agent(String name ){
        this.name = name;
        this.isBusy = false;
    }


    public Agent(int id, Client assignedClient){
        this.id = id;
        this.assignedClient = assignedClient;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public void setBusy(boolean busy) {
        this.isBusy = busy;
    }

    public void setAtentionTime(long atentionTime){
        this.atentionTime = atentionTime;
    }

    public boolean isBusy(){
        return this.isBusy;
    }

    public Long getAtentionTime(){
        return this.atentionTime;
    }

    public Client getAssignedClient(){
        return this.assignedClient;
    }

    public String getName(){
        return this.name;
    }

    public long generateAtentionTime(){
       this.atentionTime = ThreadLocalRandom.current().nextLong(10000, 15000 + 1);
       return this.atentionTime;
    }


}
