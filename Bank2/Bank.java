package Bank2;

import java.util.ArrayList;

public class Bank {
    ArrayList<Clients> clients = new ArrayList<Clients>();

    public void Append(Clients client) {
        clients.add(client);
    }

    public void print() {
        System.out.println(clients);
    }
}
