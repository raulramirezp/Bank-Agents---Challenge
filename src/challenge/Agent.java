package challenge;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


public abstract class Agent implements Supplier<Agent> {
    private boolean isBusy;
    private String name;
    private Client assignedClient;
    private Double attentionTime;

    public Agent(String name ){
        this.name = name;
        this.isBusy = false;
    }


    public Agent(Client assignedClient){
        this.assignedClient = assignedClient;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public void setBusy(boolean busy) {
        this.isBusy = busy;
    }
    

    public boolean isBusy(){
        return this.isBusy;
    }

    public Double getAttentionTime(){
        return this.attentionTime;
    }

    public Client getAssignedClient(){
        return this.assignedClient;
    }

    public String getName(){
        return this.name;
    }

    public Double generateAttentionTime(){
       this.attentionTime = ThreadLocalRandom.current().nextDouble(10000, 15000 + 1);
       return this.attentionTime;
    }

    @Override
    public Agent get() {

        System.out.println("Start client operation for " +  Thread.currentThread().getName());
        //2.Generate random time of service

        try {
            Thread.sleep(this.generateAttentionTime().longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println("Time for this thread " + this.getAttentionTime() + " thread name " + Thread.currentThread().getName());
        System.out.print(" \n The customer served was  " + this.getAssignedClient().getName() );
        System.out.print(" and  finish  at the agent " + this.getName() + " in " + this.getAttentionTime()/1000 + " seconds \n" );
        this.setAssignedClient(null);
       // this.setBusy(false);
        System.out.println ("Finish " + Thread.currentThread().getName());
        return this;
    }
}

