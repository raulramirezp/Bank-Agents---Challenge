package challenge;

public class Supervisor extends Agent{

    public Supervisor(String name){
        super(name);
    }

    public Supervisor(int id,Client client){
        super(id,client);
    }

    public String callMethod() {
        System.out.println(" The client + " + getAssignedClient().getName() );
        return getAssignedClient().makeOperation() + " finish in the " + getName();
    }

}
