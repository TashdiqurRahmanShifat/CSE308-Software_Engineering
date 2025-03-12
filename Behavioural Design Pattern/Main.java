import java.io.BufferedReader;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        try {
            BufferedReader br = new BufferedReader(new FileReader("init_stocks.txt"));

            String stockName = "";
            int stockCount = -1;
            double stockPrice = -1;

            String str = null;
            String[] token;
            System.out.println("Server Connected...");
            while ((str = br.readLine()) != null) {

                token = str.split(" ");
                stockName = token[0];
                stockCount = Integer.parseInt(token[1]);
                stockPrice = Double.parseDouble(token[2]);

                stockMarket.addStock(stockName, stockCount, stockPrice);


            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Administrator administrator = new Administrator(stockMarket);
        Thread thread = new Thread(administrator);
        thread.start();
        try {
            ServerSocket serverSocket = new ServerSocket(22225);
            while (true) {
                Socket socket = serverSocket.accept();
                Subscriber subscriber = new Subscriber(stockMarket, socket);
                Thread t = new Thread(subscriber);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

