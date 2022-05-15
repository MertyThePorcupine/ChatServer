import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    Socket socket;
    String nickname;
    public Client(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Scanner in = new Scanner(is);
            PrintStream out = new PrintStream(os);

            out.println("Welcome to chat!");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                out.println(input);
                input = in.nextLine();
            }
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
