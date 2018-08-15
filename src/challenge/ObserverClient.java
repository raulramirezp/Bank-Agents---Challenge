package challenge;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import static challenge.Operation.*;

public class ObserverClient extends Observer{

    private BlockingQueue<Client> clients;

    public ObserverClient(Dispatcher subject ){
        this.clients = new LinkedBlockingQueue<>();
        this.subject = subject;
        this.subject.add(this);
    }

    public int sizeQueue(){
        return this.clients.size();
    }
    public void createClients(){
        clients.add(new Client(0, "Raúl " , DEPOSIT));
        clients.add(new Client(1, "Kevin " , WITHDRAWAL));
        clients.add(new Client(2, "Adriana ", DEPOSIT));
        clients.add(new Client(3,"Luisa " , SOLVEISSUE));
        clients.add(new Client(4, "Carol " , WITHDRAWAL));
        clients.add(new Client(5, "Carolina " , DEPOSIT));
        clients.add(new Client(6, "Diana " , WITHDRAWAL));
        clients.add(new Client(7, "Paola " , DEPOSIT));
        clients.add(new Client(8, "Lina " , SOLVEISSUE));
        clients.add(new Client(9, "Juan " , SOLVEISSUE));
        clients.add(new Client(10, "David late" , SOLVEISSUE));
        clients.add(new Client(11, "Carlos late" , SOLVEISSUE));
        clients.add(new Client(12, "Lucia late" , SOLVEISSUE));
        clients.add(new Client(13, "Laura late" , SOLVEISSUE));
    }


    @Override
    public void update() throws ExecutionException, InterruptedException {
        this.subject.attend(this.clients.remove());
    }
}
