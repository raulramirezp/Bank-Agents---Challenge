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
        System.out.println("Im am busy ? : " + isBusy() );
        System.out.println(" The client + " + getAssignedClient().getName() );
        setBusy(false);
        return getAssignedClient().makeOperation() + " finish in the " + getName();
    }


}
