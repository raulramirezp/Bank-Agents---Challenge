package challenge;

public class Director extends Agent{

    public Director( String name){
        super(name);
    }

    public Director(int id,Client client){
        super(id,client);
    }

    public String callMethod() {
        return this.assignedClient.makeOperation() + " finish in the " + this.name;
    }
}
