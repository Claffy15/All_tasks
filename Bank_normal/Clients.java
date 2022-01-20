package Bank_normal;

public class Clients extends Bank{
    protected String name,surname;
    protected Account[] accounts;

    public Clients(String name, String surname, Account[] accounts) {
        this.name = name;
        this.surname = surname;
        this.accounts = accounts;
    }

}
