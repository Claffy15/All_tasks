package Socket;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.Executors;

class User
{
    Socket sock;
    ArrayList<User> partners;
    public Scanner in;
    public PrintWriter out;
    int i=0;
}

public class Server {
    static ArrayList<Socket> SockArray = new ArrayList<Socket>();
    public static void main(String[] args)
    {
        try {
            var listener = new ServerSocket(59023);
            System.out.println("Creating socket");
            var n = 3;
            var pool = Executors.newFixedThreadPool(n);
            while (true)
            {
                System.out.println("WAITING");
                for (int i = 0; i<n; i++) {
                    var sock = listener.accept();
                    var out = new PrintWriter(sock.getOutputStream(), true);
                    SockArray.add(sock);
                    out.println("User is connected! User IP: " + sock.getInetAddress().toString());
                }
                for (int i = 0; i<n; i++) {
                    pool.execute(new ChatServer(SockArray.get(i),i));
                }
            }
        } catch(Exception e){}
    }

    private static class ChatServer implements Runnable {
        User partner;
        User me;
        ArrayList<User> partners = new ArrayList<User>();;
        ChatServer(Socket sock, int myIndex)
        {
            me = new User();
            me.sock = sock;
            for (int i = 0; i<SockArray.size(); i++) {
                if (i!=myIndex) {
                    partner = new User();
                    partner.sock = SockArray.get(i);
                    partners.add(partner);
                }
            }
            me.partners = partners;
            try {
                me.in = new Scanner(sock.getInputStream());
                me.out = new PrintWriter(sock.getOutputStream(),true);
                int j = 0;
                for (int i = 0; i<SockArray.size(); i++) {
                    if (i!=myIndex) {
                        me.partners.get(j).in = new Scanner(SockArray.get(i).getInputStream());
                        me.partners.get(j).out = new PrintWriter(SockArray.get(i).getOutputStream(),true);
                        j++;
                    }
                }
            } catch(Exception e)
            {}
        }
        @Override
        public void run() {
            try
            {
                while (me.in.hasNextLine()) {
                    var msg = me.in.nextLine();
                    System.out.println("Message received: " + msg);
                    synchronized (SockArray) {
                        for (int i = 0; i < me.partners.size(); i++) {
                            me.partners.get(i).out.println(msg);
                        }
                    }
                }
            }catch (Exception e)
            {}
            finally
            {
                try
                {
                    me.sock.close();
                } catch (Exception e){}
            }
        }
    }
}
