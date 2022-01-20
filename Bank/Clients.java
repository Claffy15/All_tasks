package Bank;

import java.util.ArrayList;

public class Clients extends Bank{
    protected String name,surname;
    protected
    ArrayList<Account> accounts;

    public Clients(String name, String surname,  ArrayList<Account> accounts) {
        this.name = name;
        this.surname = surname;
        this.accounts = accounts;
    }

}
