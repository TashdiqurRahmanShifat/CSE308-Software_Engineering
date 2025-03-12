import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subscriber implements Observer, Runnable {
    public String clientName;
    private Socket socket;

    public List<String> subscribedStocks;
    private StockMarket stockMarket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    private Scanner scanner=new Scanner(System.in);;


    public Subscriber(StockMarket stockMarket, Socket socket) {

        subscribedStocks = new ArrayList<>();
        this.stockMarket = stockMarket;
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);


            printWriter.println("Enter your name : ");
            clientName=bufferedReader.readLine();
            printWriter.println("Welcome "+clientName);
            printWriter.println("Welcome to the StockMarket");
            for (String stock : stockMarket.stocks.keySet()) {
                Stock s = stockMarket.stocks.get(stock);
                printWriter.println("Stock Name:" + s.getName() + " " + "Stock Count:" + s.getCount() + " " + "Stock Price" + s.getPrice());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void subscribeToStocks(String stockname) {
        int flag = 0;
        for (String stock : stockMarket.stocks.keySet()) {
            Stock s = stockMarket.stocks.get(stock);
            if (s.getName().equalsIgnoreCase(stockname)) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            if (subscribedStocks.contains(stockname))
                printWriter.println("You have already subscribed this stock");

            else {

                System.out.println(clientName + " just subscribed to " + stockname);
                printWriter.println("You have successfully subscribed to " + stockname);
                subscribedStocks.add(stockname);
                stockMarket.addSubscriber(this);

            }
        } else {
            printWriter.println("There exists no stock with this name");
        }

    }

    public void unsubscribeToStocks(String stockname) {
        int flag = 0;
        for (String stock : stockMarket.stocks.keySet()) {
            Stock s = stockMarket.stocks.get(stock);
            if (s.getName().equalsIgnoreCase(stockname)) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            if (subscribedStocks.contains(stockname)) {
                System.out.println(clientName + " just unsubscribe this "+stockname);
                printWriter.println("You have successfully unsubscribed to " + stockname);
                subscribedStocks.remove(stockname);
                stockMarket.removeSubscriber(this);
            } else {
                printWriter.println("You have not subscribed this stock");
            }
        } else {
            printWriter.println("There exists no stock with this name");
        }
    }

    public void viewStocks() {
        if(subscribedStocks.size()==0)
            printWriter.println("You have not subscribed to any stock");
        else {
            for (String name : subscribedStocks) {
                Stock stock = stockMarket.getStock(name);
                if (stock != null) {
                    printWriter.println("Subscribed Stocks : " + stock.getName() + "; Stock Count : " + stock.getCount() + "; Stock Price : " + stock.getPrice());
                }
            }
        }
    }

    @Override
    public void update(String user) {
        if (!socket.isClosed()) {
            for (String name : subscribedStocks) {
                if (name.equalsIgnoreCase(user)) {
                    Stock stock = stockMarket.getStock(name);
                    if (stock != null) {
                        printWriter.println("Updated information, Subscribed Stocks : " + stock.getName() + "; Stock Count : " + stock.getCount() + "; Stock Price : " + stock.getPrice());
                    }
                }
            }
        }

    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                String str = bufferedReader.readLine();
                if (str == null) break;
                else request(str);
            }

        } catch (SocketException e) {
            System.out.println(clientName+" disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void request(String str) {
        String[] token = str.split(" ");
        if (token[0].equalsIgnoreCase("S")) {
            subscribeToStocks(token[1]);
        } else if (token[0].equalsIgnoreCase("U")) {
            unsubscribeToStocks(token[1]);
        } else if (token[0].equalsIgnoreCase("V")) {
            viewStocks();
        } else {
            printWriter.println("Invalid Command!");
        }
    }

}
