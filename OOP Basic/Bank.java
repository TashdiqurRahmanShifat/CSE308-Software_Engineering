import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

public class Bank {
    private Account account [];
    private Employee employee [];
    private Vector<String> users;
    private LinkedHashMap<Account,Double> loan_taken;
    private double initial_fund;
    private int clock;
    private int acc_count;
    private double initial_fixed_deposit_amount;
    public Bank()
    {
        account=new Account[50];
        employee=new Employee[8];
        users=new Vector<>();
        loan_taken=new LinkedHashMap<>();
        initial_fund=1000000;
        initial_fixed_deposit_amount=100000;
        clock=0;
        acc_count=0;

        employee[0]=new Managing_Director("MD","Managing Director",account);
        for(int i=1;i<=2;i++)
        {
            employee[i]=new Officer("S"+String.valueOf(i),"Officer",account);
        }
        for(int i=1;i<=5;i++)
        {
            employee[i+2]=new Cashier("C"+String.valueOf(i),"Cashier",account);
        }
        System.out.println("Bank Created; MD,S1,S2,C1,C2,C3,C4,C5 created");
    }
    public void create_Account(String name,String acc_type,double balance)
    {
        int flag=0;
        for(int i=0;i< users.size();i++)
        {
            if(name.equalsIgnoreCase(users.get(i)))
            {
                System.out.println("An Account with same name already exits");
                return;
            }


        }

        if(acc_type.equalsIgnoreCase("Savings"))
        {
            account[acc_count]=new Savings(name,acc_type,balance);
            acc_count++;
            users.add(name);
            initial_fund=initial_fund+balance;
            flag=1;
        }
        if(acc_type.equalsIgnoreCase("Student"))
        {
            account[acc_count]=new Student(name,acc_type,balance);
            acc_count++;
            users.add(name);
            initial_fund=initial_fund+balance;
            flag=1;
        }
        if(acc_type.equalsIgnoreCase("Fixed"))
        {
            if(balance<initial_fixed_deposit_amount)
                System.out.println("For Creating Fixed Deposit Account,you have to deposit minimum "+initial_fixed_deposit_amount+"$");
            else {
                account[acc_count] = new Fixed_deposit(name, acc_type, balance);
                acc_count++;
                initial_fund=initial_fund+balance;
                users.add(name);
                flag=1;
            }
        }
        if(flag==1)
            System.out.println(acc_type+" account for "+name+" Created; initial balance "+balance+"$");
        else System.out.println("Sorry!We are unable to create this account");

    }
    public void withdraw(String name,double withdrawn_amount)
    {
        int flag=0;
        for (int i=0;i<acc_count;i++)
        {
            if(account[i].username.equalsIgnoreCase(name)) {
                account[i].withdraw(withdrawn_amount);
                flag=1;
                initial_fund=initial_fund-withdrawn_amount;
                break;
            }
        }
        if(flag==0)
        {
            System.out.println("There is no user holding this name");
        }
    }
    public void deposit(String name,double deposit_balance)
    {
        for(int i=0;i<acc_count;i++)
        {
            if(account[i].username.equalsIgnoreCase(name))
            {
                account[i].deposit_amount(deposit_balance);
                initial_fund=initial_fund+deposit_balance;
                break;
            }
        }
    }
    public void query(String name)
    {
        for(int i=0;i<acc_count;i++)
        {
            if(account[i].username.equalsIgnoreCase(name))
            {
                account[i].query();
                break;
            }
        }
    }

    public void loan_request(String name,double loan_amount)
    {
        for(int i=0;i<acc_count;i++)
        {
            if(account[i].username.equalsIgnoreCase(name)&&loan_amount>0)
            {
                account[i].about_loan_request(loan_amount);
                loan_taken.put(account[i],loan_amount);
                break;
            }
        }
    }
    public void close(String name)
    {
        int index=getEmployeeIndex(name);
        int flag=0;
        if(index!=-1)
        {
            System.out.println("Operations for "+name+" closed");
            flag=1;
        }
        else
        {
            for (int i = 0; i < acc_count; i++) {
                if (account[i].username.equalsIgnoreCase(name)) {
                    System.out.println("Transaction Closed for " + name);
                    flag=1;
                }
            }
        }
        if(flag==0)
            System.out.println("There is no user holding this name");
    }
    public int getEmployeeIndex(String name)
    {
        int index=-1;
        for(int i=0;i< employee.length;i++)
        {
            if(employee[i].employee_name.equalsIgnoreCase(name))
            {
                index=i;
                break;
            }
        }
        return index;
    }
    public void open(String name)
    {
        int flag=0;
        int index=getEmployeeIndex(name);
        if(index!=-1)
            flag=1;
        if(flag==1)
        {
            if(employee[index] instanceof Managing_Director||employee[index] instanceof Officer)
            {
                if(loan_taken.size()>0)
                {
                    System.out.println(name+" active, there are loan approvals pending");
                }
                else
                {
                    System.out.println(name+" active, there are no loan approval pending");
                }
            }
            if(employee[index] instanceof Cashier)
            {
                System.out.println(name+" active");
            }
        }
        else
        {
            int fl=0;
            for(int i=0;i< users.size();i++)
            {
                if(name.equalsIgnoreCase(users.get(i)))
                {
                    System.out.println("Welcome back, "+name);
                    fl=1;
                    break;
                }


            }
            if(fl==0)
                System.out.println("There is no account user holding this name");
        }
    }

    public void loan_approve(String name)
    {
        int index=getEmployeeIndex(name);

        if(index!=-1)
        {
            if (employee[index] instanceof Managing_Director || employee[index] instanceof Officer)
            {
                if (loan_taken.size() > 0)
                {
                    for(Map.Entry<Account,Double> set:loan_taken.entrySet())
                    {
                        employee[index].approve_loan(set.getKey(), set.getValue());
                        System.out.println("Loan for " + set.getKey().username + " approved");
                        initial_fund=initial_fund-set.getValue();
                    }
                    loan_taken.clear();
                }
            }
            else
            {
                if(employee[index] instanceof Cashier)
                {
                    System.out.println("You don't have permission for this operation");
                }
            }
        }
    }

    public void changeInterestRate(String name,String account_type,double new_interest_rate)
    {
        int index=getEmployeeIndex(name);
        if(index!=-1 && employee[index] instanceof Managing_Director)
        {
            employee[index].change_interest_rate(account_type,new_interest_rate);
        }
        if(index!=-1 && (employee[index] instanceof Officer||employee[index] instanceof Cashier))
        {
            employee[index].change_interest_rate(account_type,new_interest_rate);
        }
    }

    public void lookUp(String name)
    {
        int flag=0;
        for(int i=0;i< users.size();i++)
        {
            if(name.equalsIgnoreCase(users.get(i)))
            {
                employee[0].lookup(name);
                flag=1;
                break;
            }


        }
        if(flag==0)
            System.out.println("There is no account user holding this name");

    }

    public void see(String name)
    {
        int index=getEmployeeIndex(name);
        if(index!=-1 && employee[index] instanceof Managing_Director)
        {
            System.out.println("The internal fund of the bank is "+initial_fund+"$");
        }
        else
        {
            System.out.println("You don't have permission for this operation");
        }
    }
    public void INC()
    {
        clock++;
        for(int i=0;i<acc_count;i++)
        {
            double prev_balance=account[i].balance;
            account[i].amount_change_per_year();
            double next_balance=account[i].balance;
            initial_fund=initial_fund-(next_balance-prev_balance);
        }
        if(clock==1)
            System.out.println(String.valueOf(clock)+" year passed");
        else if(clock>1)
            System.out.println(String.valueOf(clock)+" years passed");
    }

}
