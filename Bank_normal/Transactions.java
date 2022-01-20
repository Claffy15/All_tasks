package Bank_normal;

public class Transactions {
    protected Account from;
    protected Account to;
    protected double Sum;
    protected String date;

    public Transactions(Account from,Account to, double Sum, String date) throws AmountException{
        if (from.amount<Sum) throw new AmountException("You don't have enough money");

        this.from = from;
        this.to = to;
        this.Sum = Sum;
        this.date = date;

        to.amount += Sum;
        from.amount -= Sum;
    }
}
