import java.util.Scanner;

public class Administrator implements Runnable {
    private StockMarket stockMarket;
    private Scanner scn;
    String str;

    public Administrator(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
        scn = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (scn.hasNextLine()) {
            str = scn.nextLine();
            try {


                String[] token = str.split(" ");
                String stockName = token[1];

                if (token[0].equalsIgnoreCase("I")) {
                    stockMarket.increaseStockPrice(stockName, Double.parseDouble(token[2]));
                } else if (token[0].equalsIgnoreCase("D")) {
                    stockMarket.decreaseStockPrice(stockName, Double.parseDouble(token[2]));
                } else if (token[0].equalsIgnoreCase("C")) {
                    stockMarket.changeInCount(stockName, Integer.parseInt((token[2])));
                } else {
                    System.out.println("Invalid Command!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
