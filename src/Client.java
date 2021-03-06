import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    Socket socket;
    ChatServer chatServer;

    String nickname;

    Scanner in;
    PrintStream out;
    public Client(Socket socket, ChatServer chatServer) {
        this.chatServer = chatServer;
        this.socket = socket;
        new Thread(this).start();
    }

    void receive(String message){
        out.println(message);
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);

            out.println("Welcome to chat!");
            out.println("Type your nickname");

            nickname = in.nextLine();

            out.println("Now you can chat, " + nickname);

            String input = in.nextLine();
            while (!input.equals("bye")) {
                chatServer.sendAll(input, nickname);
                input = in.nextLine();
            }
            input = in.nextLine();
            socket.close();
            return;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
