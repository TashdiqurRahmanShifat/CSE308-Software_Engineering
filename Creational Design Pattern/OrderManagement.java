import java.util.Scanner;

public class OrderManagement
{
    //LinkedHashMap<String,Double>map=new LinkedHashMap<>();
    String str="";
    public  void selectShake(Order order)
    {
        Scanner scanner=new Scanner(System.in);
        ShakeDirector shakeDirector=new ShakeDirector();


        while (true)
        {
            System.out.println("Choose a shake:");
            System.out.println("1. Chocolate");
            System.out.println("2. Coffee");
            System.out.println("3. Strawberry");
            System.out.println("4. Vanilla");
            System.out.println("5. Zero");

            char shakeChoice = scanner.next().charAt(0);
            ShakeBuilder shakeBuilder=null;

            switch (shakeChoice)
            {
                case '1':
                    shakeBuilder = new ChocolateShakeBuilder();
                    ChocolateShakeBuilder chocolateShakeBuilder = (ChocolateShakeBuilder) shakeBuilder;
                    chocolateShakeBuilder.addChocolateSyrup();
                    chocolateShakeBuilder.addChocolateIceCream();
                    break;
                case '2':
                    shakeBuilder = new CoffeeShakeBuilder();
                    CoffeeShakeBuilder coffeeShakeBuilder = (CoffeeShakeBuilder) shakeBuilder;
                    coffeeShakeBuilder.addCoffee();
                    coffeeShakeBuilder.addJello();
                    break;
                case '3':
                    shakeBuilder = new StrawberryShakeBuilder();
                    StrawberryShakeBuilder strawberryShakeBuilder = (StrawberryShakeBuilder) shakeBuilder;
                    strawberryShakeBuilder.addStrawberrySyrup();
                    strawberryShakeBuilder.addStrawberryIceCream();
                    break;
                case '4':
                    shakeBuilder = new VanillaShakeBuilder();
                    VanillaShakeBuilder vanillaShakeBuilder = (VanillaShakeBuilder) shakeBuilder;
                    vanillaShakeBuilder.addVanillaFlavoring();
                    vanillaShakeBuilder.addJello();
                    break;
                case '5':
                    shakeBuilder = new ZeroShakeBuilder();
                    ZeroShakeBuilder zeroShakeBuilder = (ZeroShakeBuilder) shakeBuilder;
                    zeroShakeBuilder.addVanillaFlavoring();
                    zeroShakeBuilder.addSugarFreeJello();
                    break;
                default:
                    System.out.println("Invalid Shake Choice");
                    continue;
            }

            Shake shake = shakeDirector.construct(shakeBuilder);

            customizeShake(shake);

            order.addShake(shake);
            System.out.println("Do you want to add another shake?If yes press 'y' and press 'n' if you do not want to add another shake");
            char anotherChoice=scanner.next().charAt(0);
            if (anotherChoice!='y') {
                break;
            }
        }
    }

    public void customizeShake(Shake shake) {
        Scanner scanner = new Scanner(System.in);
        boolean button=true;
        while (button) {

            System.out.println("Wanna Add Something?");
            System.out.println("1. Make lactose-free (additional Tk 60)");
            System.out.println("2. Add candy on top (additional Tk 50)");
            System.out.println("3. Add cookies on top (additional Tk 40)");
            System.out.println("If you do not want to add anything,press 4");
            System.out.println("You can choose any of the 3 items.Select any number (1-3):");

            char customizationChoice = scanner.next().charAt(0);
            switch (customizationChoice) {
                case '1':
                    int flag=0;
                    shake.getIngredients().remove(shake.getIngredients().get(0));

                    for(int i=0;i<shake.getIngredients().size();i++)
                    {
                        if(shake.getIngredients().get(i).equalsIgnoreCase("Almond Milk"))
                        {
                            flag=1;
                            break;
                        }
                    }

                    if(flag==0)shake.addIngredient("Almond Milk");
                    shake.setAdditionalPrice(60);
                    str+="Item : Almond Milk ; Price : Tk 60.0"+System.lineSeparator();
                    break;

                case '2':
                    shake.addIngredient("Candy");
                    shake.setAdditionalPrice(50);
                    str+="Item : Candy ; Price : Tk 50.0"+System.lineSeparator();
                    break;

                case '3':
                    shake.addIngredient("Cookies");
                    shake.setAdditionalPrice(40);
                    str+="Item : Cookies ; Price : Tk 40.0"+System.lineSeparator();
                    break;
                case '4':
                    button=false;
                    break;

                default:
                    System.out.println("Invalid customization choice.");
                    continue;
            }

            if(button==false){
                //button=true;
                continue;
            }
            System.out.println("Do you want to customize more?If yes press 'y' and press 'n' if you do not want to");
            char continueChoice=scanner.next().charAt(0);
            if (continueChoice!='y') {
                break;
            }
        }
    }

    public void printOrderDetails(Order order) {
        System.out.println("You have ordered the following items:");
        System.out.println();

        double initial_additional_price=0;
        for (Shake shake : order.getShakeList()) {
            System.out.println("Shake : " + shake.getName());
            System.out.println("Ingredients : " + String.join(", ", shake.getIngredients()));
            System.out.println("Price : Tk " + shake.getPrice());

            System.out.println("Additional Price : Tk "+shake.getAdditional_price());
            initial_additional_price=shake.getAdditional_price();


//            if(map.size()!=0)
//            {
//                System.out.println("Because of the additional items the price has increased by "+initial_additional_price);
//                System.out.println("The additional items are:");
//                for(Map.Entry<String,Double>set: map.entrySet())
//                {
//                    System.out.println("Item : "+set.getKey()+" Price: "+set.getValue());
//                }
//                map.clear();
//            }

            if(initial_additional_price!=0)
                System.out.println("Because of the additional items the price has increased by "+initial_additional_price);
            System.out.println();

        }

        if(initial_additional_price!=0)
            System.out.println("Here is the list of your additional ingredients & their respective prices:");

        System.out.println(str);
        str="";


        System.out.println();
        System.out.println("Total Price: Tk " + order.getTotalPrice());
        System.out.println();
    }

}
