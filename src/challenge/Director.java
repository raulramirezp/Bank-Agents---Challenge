package challenge;

public class Director extends Agent{

    public Director( String name){
        super(name);
    }

    public Director(int id,Client client){
        super(id,client);
    }

    public String callMethod() {
        System.out.println(" The client + " + getAssignedClient().getName() );
        return getAssignedClient().makeOperation() + " finish in the " + getName();
    }
}
