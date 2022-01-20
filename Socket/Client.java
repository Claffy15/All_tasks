package Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    static Scanner in;// = new Scanner(socket.getInputStream());

    public static void main(String[] args) throws IOException {
        var socket = new Socket("localhost", 59023);
        in = new Scanner(socket.getInputStream());
        var out = new PrintWriter(socket.getOutputStream(),true);
        var input = new Scanner(System.in);
        Client instance = new Client();
        Thread thr = new Thread(instance);
        thr.start();
        while(true) {
            String msg = input.nextLine();
            if (msg.equals("end")) break;
            out.println(msg);
        }
    }
    @Override
    public void run() {
        while(true)
        {
            synchronized (in) {
                if (in.hasNextLine())
                    System.out.println("Partner:" + in.nextLine());
            }
        }
    }
}
