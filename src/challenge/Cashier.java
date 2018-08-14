package challenge;


public class Cashier extends Agent{

    public Cashier(String name ){
        super(name);
    }

    public String getDescription(){
        return "Im am Cashier " + this.getName();
    }
}
