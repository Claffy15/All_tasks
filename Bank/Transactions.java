package Bank;

public class Transactions {
    protected int from;
    protected int to;
    protected double Sum;
    protected String date;

    public Transactions(Account from,Account to, double Sum, String date) throws AmountException{
        if (from.amount<Sum) throw new AmountException("You don't have enough money");

        this.from = from.account;
        this.to = to.account;
        this.Sum = Sum;
        this.date = date;

        to.amount += Sum;
        from.amount -= Sum;
    }
}
