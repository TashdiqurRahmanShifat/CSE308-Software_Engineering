abstract class Employee {
    protected String employee_name;
    protected  String employee_type;

    Account [] account;
    public Employee(String employee_name,String employee_type)
    {
        this.employee_name=employee_name;
        this.employee_type=employee_type;
    }
    public void lookup(String search_name)
    {
        for (int i = 0; i < account.length; i++)
        {
            if (account[i].username.equalsIgnoreCase(search_name))
            {
                System.out.println(account[i].username + "'s current balance " + account[i].balance + "$");
                break;
            }
        }
    }
    abstract void approve_loan(Account acc,double loan_Amount);
    abstract void change_interest_rate(String account_type,double new_acc_interest_rate);

}

class Managing_Director extends Employee
{

    public Managing_Director(String employee_name,String employee_type,Account [] account)
    {
        super(employee_name, employee_type);
        this.account=account;
    }
    public void approve_loan(Account acc,double loan_Amount)
    {
        acc.about_loan_approve(loan_Amount);
    }

    public void change_interest_rate(String account_type,double new_acc_interest_rate)
    {
        if(account_type.equalsIgnoreCase("Savings"))
        {
            Savings.setDeposit_interest_rate(new_acc_interest_rate/100);
            System.out.println("You have changed the deposit interest rate.Current interest rate is "+String.valueOf(new_acc_interest_rate)+"%");
        }
        if(account_type.equalsIgnoreCase("Student"))
        {
            Student.setDeposit_interest_rate(new_acc_interest_rate/100);
            System.out.println("You have changed the deposit interest rate.Current interest rate is "+String.valueOf(new_acc_interest_rate)+"%");
        }
        if(account_type.equalsIgnoreCase("Fixed Deposit"))
        {
            Fixed_deposit.setDeposit_interest_rate(new_acc_interest_rate/100);
            System.out.println("You have changed the deposit interest rate.Current interest rate is "+String.valueOf(new_acc_interest_rate)+"%");
        }
    }
}
class Officer extends Employee
{

    public Officer(String employee_name,String employee_type,Account [] account)
    {
        super(employee_name, employee_type);
        this.account=account;

    }
    public void approve_loan(Account acc,double loan_Amount)
    {
        acc.about_loan_approve(loan_Amount);
    }
    public void change_interest_rate(String account_type,double new_acc_interest_rate)
    {
        System.out.println("You don't have permission for this operation");
    }

}
class Cashier extends Employee
{
    public Cashier(String employee_name,String employee_type,Account [] account)
    {
        super(employee_name, employee_type);
        this.account=account;
    }
    public void approve_loan(Account acc,double loan_Amount)
    {
        System.out.println("You don't have permission for this operation");
    }
    public void change_interest_rate(String account_type,double new_acc_interest_rate)
    {
        System.out.println("You don't have permission for this operation");
    }

}

