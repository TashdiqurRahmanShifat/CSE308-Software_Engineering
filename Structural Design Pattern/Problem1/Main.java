import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        Controller controller=new Controller();
        Scanner scn=new Scanner(System.in);
        String str;
        int flag=0;
        while(true)
        {
            str=scn.nextLine();
            String [] token=str.split(" ");

            if(token[0].equalsIgnoreCase("login"))
            {
                if(flag==0) {
                    if (token[1].equalsIgnoreCase("crew1")) {
                        controller.login(token[1]);
                        flag=1;
                    } else if (token[1].equalsIgnoreCase("imp1")) {
                        controller.login(token[1]);
                        flag=1;
                    }
                    else
                    {
                        System.out.println("There is no user having this name");
                    }

                }
                else
                {
                    System.out.println("Already an user is operating");
                }

            }
            else if (token[0].equalsIgnoreCase("repair"))
            {
                controller.repair(token[0]);
            }

            else if (token[0].equalsIgnoreCase("work"))
            {
                controller.work(token[0]);
            }

            else if (token[0].equalsIgnoreCase("logout"))
            {
                controller.logout();
                flag=0;
            }
            else if (token[0].equalsIgnoreCase("exit"))
            {
                System.out.println("Server is closing..");
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid Command!");
            }
        }
    }
}
