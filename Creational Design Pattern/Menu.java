import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Order currentOrder=null;
        OrderManagement orderManagement = new OrderManagement();


        while (true)
        {
            System.out.println("Press 'o' for opening an order & press 'e' for closing the current order");
            System.out.println("For close the program press 'q'");
            char choice = scanner.next().charAt(0);
            if (choice=='o')
            {
                if (currentOrder!=null)
                {
                    System.out.println("Close the current order before opening a new one");
                }
                else
                {
                    currentOrder=new Order();
                    orderManagement.selectShake(currentOrder);
                }
            }
            else if (choice=='e')
            {
                if (currentOrder==null) {
                    System.out.println("No order to close.Open an order first");
                }
                else
                {
                    orderManagement.printOrderDetails(currentOrder);
                    currentOrder=null;
                }
            }
            else if(choice=='q')
            {
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid choice.Please enter 'o' if you have no current order or 'e' to close the current order");
            }
        }
    }

}
