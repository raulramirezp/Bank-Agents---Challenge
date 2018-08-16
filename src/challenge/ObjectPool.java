package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;


public class ObjectPool{


    private BlockingQueue<Agent> cashier;
    private BlockingQueue<Agent> supervisor;
    private BlockingQueue<Agent> director;
    private List<Observer> observers = new ArrayList<>();

    public ObjectPool(){

        this.cashier = new LinkedBlockingQueue<>();
        this.supervisor = new LinkedBlockingQueue<>();
        this.director = new LinkedBlockingQueue<>();

        for (int i = 0; i < 5; i++)
            cashier.add(new Cashier("Cashier" + i));
        for (int i = 0; i < 4; i++)
            supervisor.add(new Supervisor("Supervisor" + i));
        director.add(new Director("Director"));

    }

    public void add(Observer observer){
        this.observers.add(observer);
    }

    public void addToCashier(Agent agent) throws ExecutionException, InterruptedException {
        // Im goin to notify
        this.execute();
        this.cashier.add(agent);

    }

    public void addToSupervisor(Agent agent) throws ExecutionException, InterruptedException {
        this.execute();
        this.supervisor.add(agent);

    }

    public void addToDirector(Agent agent) throws ExecutionException, InterruptedException {
        this.execute();
        this.director.add(agent);
    }


    public void execute() throws ExecutionException, InterruptedException {
        for (Observer theObserver : observers)
            theObserver.update();
    }


    public int cashierSize(){
        return this.cashier.size();
    }

    public int supervisorSize() {
        return this.supervisor.size();
    }


    public int directorSize() {
        return this.director.size();
    }

    public Agent removeFromCashier() {
        return this.cashier.remove();
    }

    public Agent removeFromSupervisor()  {
        return this.supervisor.remove();
    }

    public Agent removeFromDirector()  {
        return this.director.remove();
    }
}
