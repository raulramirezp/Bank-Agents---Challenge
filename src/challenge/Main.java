package challenge;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static challenge.Operation.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Dispatcher app = new Dispatcher(10);
     //   List<Client> clients = new ArrayList<>();


        app.attend(new Client(0, "Raúl " , DEPOSIT));
        app.attend(new Client(1, "Kevin " , WITHDRAWAL));
        app.attend(new Client(2, "Adriana ", DEPOSIT));
        app.attend(new Client(3,"Luisa " , SOLVEISSUE));
        app.attend(new Client(4, "Carol " , WITHDRAWAL));
        app.attend(new Client(5, "Carolina " , DEPOSIT));
        app.attend(new Client(6, "Diana " , WITHDRAWAL));
        app.attend(new Client(7, "Paola " , DEPOSIT));
        app.attend(new Client(8, "Lina " , SOLVEISSUE));
        app.attend(new Client(9, "Juan " , SOLVEISSUE));
        app.attend(new Client(10, "David late" , SOLVEISSUE));
        app.attend(new Client(10, "Carlos late" , SOLVEISSUE));
        app.attend(new Client(10, "Lucia late" , SOLVEISSUE));
        app.attend(new Client(10, "Laura late" , SOLVEISSUE));

      //  app.attend(clients);

        System.out.println(" Main finish here ");
        app.shutdown();
    }
}
