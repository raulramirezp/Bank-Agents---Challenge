package challenge;

public class Client {
    private String name;
    private Integer id;
    private Operation operation;

    public Client(String name, Operation operation) {
        this.name = name;
        this.operation = operation;
    }

    public Client(int id, String name, Operation operation) {
        this.id = id;
        this.name = name;
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getName() {
        return this.name;
    }
}
