package challenge;
import java.util.Random;
import java.util.concurrent.Callable;

public abstract class Agent implements Callable {
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

    @Override
    public Object call() throws Exception {
        //Template method
        //.1 Start operation
        this.isBusy = true;
        System.out.println("Start client operation ");

        //2.Generate random time of service
        Random r = new Random();
        this.atentionTime = 10000 + (15000 - 10000) * r.nextLong();
        System.out.println(atentionTime);
        Thread.sleep(4000);

        //3. Finish client operation
        return callMethod();
    }
    public abstract String callMethod();

}
