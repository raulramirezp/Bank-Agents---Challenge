package challenge;


public class Cashier extends Agent{

    public Cashier(String name ){
        super(name);
    }

    public Cashier(int id,Client client){
        super(id,client);
    }

    public String getDescription(){
        return "Im am Cashier " + this.getName();
    }
}
