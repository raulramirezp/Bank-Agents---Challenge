package challenge;

public class Main {
    public static void main( String [] args) {
        //Create the Observer
        Dispatcher app = new Dispatcher(10);

        /*Create clients for attend*/
        for(int i = 0; i < 20; i++)
            app.attend(new Client(1,"Client " + i, Operation.DEPOSIT));
    }
}
