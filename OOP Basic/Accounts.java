abstract class Account {
    protected String username;
    protected String acc_type;
    protected double balance;
    protected double loan;


    public Account(String username,String acc_type,double balance)
    {
        this.username=username;
        this.acc_type=acc_type;
        this.balance=balance;
        this.loan=0;
    }
    abstract void withdraw(double withdrawn_amount);
    abstract void deposit_amount(double deposit_money);
    public void query()
    {
        if(loan==0)
            System.out.println("Current Balance "+balance+"$");
        else
            System.out.println("Current Balance "+balance+"$, loan "+loan+"$");
    }
    abstract void about_loan_request(double loan_amount);
    abstract void amount_change_per_year();
    public void about_loan_approve(double loan_amount)
    {
        loan=loan+loan_amount;
        balance=balance+loan;
    }
}
class Savings extends Account{
    private double max_loan_amount;
    private double loan_interest_rate;
    private static double deposit_interest_rate;
    private double service_charge;
    private double year;

    public Savings(String username, String acc_type, double balance) {
        super(username, acc_type, balance);
        max_loan_amount=10000;
        loan_interest_rate=0.1;
        deposit_interest_rate=0.1;
        service_charge=500;
        year=0;
    }

    @Override
    void withdraw(double withdrawn_amount) {
        if(balance-withdrawn_amount<1000||withdrawn_amount>balance)
        {
            System.out.println("Invalid transaction; current balance "+balance+"$");
            //return;
        }
        else {
            balance=balance-withdrawn_amount;
            System.out.println("Valid transaction; current balance "+balance+"$");
        }
    }

    public void deposit_amount(double deposit_money)
    {
        balance=balance+deposit_money;
        System.out.println(deposit_money+"$ deposited; current balance "+balance+"$");
    }
    public static void setDeposit_interest_rate(double new_interest_rate)
    {
        Savings.deposit_interest_rate=new_interest_rate;
    }
    /*public double getDeposit_interest_rate()
    {
        return loan_interest_rate;
    }

     */
    public void amount_change_per_year()
    {
        year++;
        balance=balance+balance*deposit_interest_rate;
        balance=balance-(loan*loan_interest_rate);
        balance=balance-service_charge;
    }
    public void about_loan_request(double loan_amount)
    {

        if(loan_amount>max_loan_amount)
            System.out.println("Sorry!You are allowed to receive only "+max_loan_amount+"$ "+"at a time");
        else
        {

            System.out.println("Loan request successful, sent for approval");
        }
    }

}
class Student extends Account{
    private double max_loan_amount;
    private double loan_interest_rate;
    private static double deposit_interest_rate;
    private double service_charge;
    private double year;

    public Student(String username, String acc_type, double balance) {
        super(username, acc_type, balance);
        max_loan_amount=1000;
        loan_interest_rate=0.1;
        deposit_interest_rate=0.05;
        service_charge=0;
        year=0;
    }

    @Override
    void withdraw(double withdrawn_amount) {
        if(withdrawn_amount>10000||withdrawn_amount>balance)
        {
            System.out.println("Invalid transaction; current balance "+balance+"$");
            //return;
        }
        else {
            balance=balance-withdrawn_amount;
            System.out.println("Valid transaction; current balance "+balance+"$");
        }
    }

    public void deposit_amount(double deposit_money)
    {
        balance=balance+deposit_money;
        System.out.println(deposit_money+"$ deposited; current balance "+balance+"$");
    }
    public static void setDeposit_interest_rate(double new_interest_rate)
    {
        Student.deposit_interest_rate=new_interest_rate;
    }
    /*public double getDeposit_interest_rate()
    {
        return loan_interest_rate;
    }

     */
    public void amount_change_per_year()
    {
        year++;
        balance=balance+balance*deposit_interest_rate;
        balance=balance-(loan*loan_interest_rate);
        balance=balance-service_charge;
    }
    public void about_loan_request(double loan_amount)
    {
        if(loan_amount>max_loan_amount)
            System.out.println("Sorry!You are allowed to receive only "+max_loan_amount+"$ "+"at a time");
        else
        {
            //loan=loan+loan_amount;
            System.out.println("Loan request successful, sent for approval");
        }
    }
}
class Fixed_deposit extends Account{
    private double max_loan_amount;
    private double loan_interest_rate;
    private static double deposit_interest_rate;
    private double service_charge;
    private double minimum_deposit_amount;
    private double year;

    public Fixed_deposit(String username, String acc_type, double balance) {
        super(username, acc_type, balance);
        max_loan_amount=100000;
        loan_interest_rate=0.1;
        deposit_interest_rate=0.15;
        service_charge=500;
        minimum_deposit_amount=50000;
        year=0;

    }

    @Override
    void withdraw(double withdrawn_amount) {
        if(year<1)
            System.out.println("It has not reached a maturity period of one year");
        else if (withdrawn_amount>balance) {
            System.out.println("Invalid transaction; current balance "+balance+"$");
        }
        else {
            balance=balance-withdrawn_amount;
            System.out.println("Valid transaction; current balance "+balance+"$");

        }
    }
    public void deposit_amount(double deposit_money)
    {
        if(deposit_money<minimum_deposit_amount)
            System.out.println("The deposit amount must not be less than 50,000$");
        balance=balance+deposit_money;
        System.out.println(deposit_money+"$ deposited; current balance "+balance+"$");
    }
    public static void setDeposit_interest_rate(double new_interest_rate)
    {
        Fixed_deposit.deposit_interest_rate=new_interest_rate;
    }
    /*public double getDeposit_interest_rate()
    {
        return loan_interest_rate;
    }

     */

    public void amount_change_per_year()
    {
        year++;
        balance=balance+balance*deposit_interest_rate;
        balance=balance-(loan*loan_interest_rate);
        balance=balance-service_charge;
    }
    public void about_loan_request(double loan_amount)
    {
        if(loan_amount>max_loan_amount)
            System.out.println("Sorry!You are allowed to receive only "+max_loan_amount+"$ "+"at a time");
        else
        {
            //loan=loan+loan_amount;
            System.out.println("Loan request successful, sent for approval");
        }
    }
}

