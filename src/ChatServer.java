import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class ChatServer {
    ArrayList<Client> clients= new ArrayList();
    ServerSocket server;

    ChatServer() throws IOException {
        server = new ServerSocket(1234);
    }
    void sendAll(String message){

    }
    public void run(){
        while (true){
            System.out.println("Waiting...");
            try{
                Socket socket = server.accept();
                System.out.println();
                Client client = new Client(socket);
                clients.add(client);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
