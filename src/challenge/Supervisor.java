package challenge;

public class Supervisor extends Agent{

    public Supervisor(String name){
        super(name);
    }

    public Supervisor(int id,Client client){
        super(id,client);
    }

    public String callMethod() {
        return this.assignedClient.makeOperation() + " finish in the " + this.name;
    }

}
