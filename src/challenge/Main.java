package challenge;

import java.util.concurrent.ExecutionException;

import static challenge.Operation.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Dispatcher app = new Dispatcher(10);
        for(int i = 0; i < 10;i++)
            app.attend(new Client(i, "Raúl " + i, DEPOSIT));

        System.out.println(" Main finish here ");
    }
}
