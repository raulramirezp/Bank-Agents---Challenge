package challenge;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main( String [] args) throws ExecutionException, InterruptedException {
    //Create de Subject
        Dispatcher app = new Dispatcher(10);

        //Create de Observer
        ObserverClient observer = new ObserverClient(app);

        observer.createClients();
        int size = observer.sizeQueue();
        for(int i = 0 ; i < size; i++)
            app.execute();
        app.shutdown();
    }
}
