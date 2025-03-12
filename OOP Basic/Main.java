import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank b=new Bank();
        Scanner scn=new Scanner(System.in);
        String Current_user="";
        while (true)
        {
            String [] token=scn.nextLine().split(" ");


            switch (token[0])
            {
                case "Create":
                    b.create_Account(token[1],token[2],Double.valueOf(token[3]));
                    Current_user=token[1];
                    break;
                case "Deposit":
                    b.deposit(Current_user,Double.valueOf(token[1]));
                    break;
                case "Withdraw":
                    b.withdraw(Current_user,Double.valueOf(token[1]));
                    break;
                case "Query":
                    b.query(Current_user);
                    break;
                case "Request":
                    b.loan_request(Current_user,Double.valueOf(token[1]));
                    break;
                case "Close":
                    b.close(Current_user);
                    break;
                case "Open":
                    b.open(token[1]);
                    Current_user=token[1];
                    break;
                case "Approve":
                    b.loan_approve(Current_user);
                    break;
                case "Change":
                    b.changeInterestRate(Current_user,token[1],Double.valueOf(token[2]));
                    break;
                case "Lookup":
                    b.lookUp(token[1]);
                    break;
                case "See":
                    b.see(Current_user);
                    break;
                case "INC":
                    b.INC();
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                default :
                    System.out.println("There is no such command");
                    break;

            }
        }
    }
}
