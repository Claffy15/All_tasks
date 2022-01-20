package Bank;

import java.util.ArrayList;

public class Bank {
    ArrayList<Clients> clients = new ArrayList<Clients>();

    public void Append(Clients client) {
        clients.add(client);
    }

    public Account[] addAccount(Account[] account, Account what) {
        int size = account.length+1;
        Account[] tmpArr = new Account[size];
        for (int i = 0; i < size-1; i++)
            tmpArr[i] = account[i];
        tmpArr[size-1]=what;
        account = tmpArr;
        return account;
    }

    public void print() {
        System.out.println(clients);
    }
}
