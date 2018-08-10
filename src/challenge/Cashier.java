package challenge;


public class Cashier extends Agent{

    public Cashier(String name ){
        super(name);
    }

    public Cashier(int id,Client client){
        super(id,client);
    }

    @Override
    public String callMethod() {
        return this.assignedClient.makeOperation() + " finish in the " + this.name;
    }


}
