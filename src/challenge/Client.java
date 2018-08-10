package challenge;

public class Client {
    String name;
    Integer id;
    Operation operation;

    public Client(String name, Operation operation) {
        this.name = name;
        this.operation = operation;
    }

    public Client(int id, String name, Operation operation) {
        this.id = id;
        this.name = name;
        this.operation = operation;
    }

    public String makeOperation(){
        return  "The client operation :  " + this.operation;
    }

    public String getName() {
        return this.name;
    }
}
