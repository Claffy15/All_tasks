package Bank2;

import java.util.ArrayList;
import java.util.Scanner;

public class BankMain {

    static int Bank_Money_Amount = 0;
    static Bank bank = new Bank();
    static ArrayList<Transactions> transactions = new ArrayList<Transactions>();

    public static Account find(int account) {
        for (int i = 0; i < bank.clients.size(); i++){
            for (int j = 0; j < bank.clients.get(i).accounts.size(); j++) {
                if (bank.clients.get(i).accounts.get(j).account == account){
                    return bank.clients.get(i).accounts.get(j);
                }
            }
        }
        return null;
    }

    static class Action extends Thread
    {
        Clients client;
        Action(Clients c){
            client = c;
        }

        public void run() {
            synchronized (bank) {
                var cont = true;
                while (cont==true) {
                    Scanner in = new Scanner(System.in);
                    System.out.println("What do you want to do?");
                    System.out.println("0. Break");
                    System.out.println("1. Make a transaction");
                    int input = in.nextInt();

                    switch (input) {
                        case 0:
                            cont=false;
                            break;
                        case 1:
                            System.out.print("Where you want to transfer your money? ");
                            int w = in.nextInt();

                            System.out.print("How much money? ");
                            int h = in.nextInt();

                            var acc = find(w);
                            if (acc==null) {
                                System.out.println("This account not exist");
                            }else {
                                try {
                                    Transactions transaction = new Transactions(client.accounts.get(0), find(w), h, "23.10.2021");
                                } catch (AmountException a) {
                                    System.out.println("You don't have enough money");
                                }
                            }
                    }
                }

            }
        }
    }

    public static Thread[] createThreads(int nThreads, Thread[] t){
        for (int i =0; i < nThreads; i++){
            t[i] = new Action(bank.clients.get(i));
        }
        return t;
    }

    public static void main(String[] args) throws AmountException {
        Clients client1 = new Clients("Name", "Surname", null);
        ArrayList<Account> acc = new ArrayList<Account>();
        Account ac_1 = new Account(22, 100);
        acc.add(ac_1);
        client1.accounts = acc;
        bank.Append(client1);

        Clients client2 = new Clients("Shop", "Shop", null);
        ArrayList<Account> acc2 = new ArrayList<Account>();
        Account ac_2 = new Account(11, 100);
        acc2.add(ac_2);
        client2.accounts = acc2;
        bank.Append(client2);

        int nThreads = bank.clients.size();
        Thread[] t = new Thread[nThreads];

        t = createThreads(nThreads, t);;
        for (int i=0; i<nThreads;i++)
        {
            //System.out.println(t[i].getName());
            t[i].start();
        }

        for (int i=0; i<nThreads;i++)
        {
            try{
                t[i].join();
            }catch (Exception e){}
        }
    }
}
