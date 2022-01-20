package Bank_normal;

import java.util.ArrayList;

public class BankMain {
    public static void main(String[] args) throws AmountException {
        Bank bank = new Bank();
        ArrayList<Transactions> transactions = new ArrayList<Transactions>();

        Clients client1 = new Clients("Name", "Surname", null);
        Account ac_1 = new Account(22, 100);
        Account[] acc = new Account[0];
        acc = bank.addAccount(acc, ac_1);
        client1.accounts = acc;
        bank.Append(client1);

        Clients shop = new Clients("Shop", "Shop", null);
        Account ac_2 = new Account(11, 100);
        Account[] acc2 = new Account[0];
        acc2 = bank.addAccount(acc2, ac_2);
        shop.accounts = acc2;
        bank.Append(shop);

        Transactions transaction1 = new Transactions(client1.accounts[0], shop.accounts[0], 100, "23.10.2021");
        transactions.add(transaction1);
        System.out.println(client1.accounts[0].amount);
        System.out.println(shop.accounts[0].amount);
    }
}
