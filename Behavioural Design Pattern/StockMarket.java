import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockMarket implements Subject {
    public List<Observer> observerList;

    public Map<String, Stock> stocks;

    public StockMarket() {
        observerList = new ArrayList<>();
        stocks = new HashMap<>();
    }

    public void addStock(String stockname, int count, double price) {
        Stock s = new Stock(stockname, count, price);
        stocks.put(stockname, s);
    }

    public Stock getStock(String stockname) {
        return stocks.get(stockname);
    }

    public void increaseStockPrice(String stockname, double price) {
        Stock currentStock = stocks.get(stockname);
        if (currentStock != null) {
            currentStock.setPrice(currentStock.getPrice() + price);
            notifySubscriber(stockname);
        }
    }


    public void decreaseStockPrice(String stockname, double price) {
        Stock currentStock = stocks.get(stockname);
        if (currentStock != null) {
            double currentVal = currentStock.getPrice() - price;
            if (currentVal < 0) {
                System.out.println("Stock price can't be negative");
                currentStock.setPrice(currentStock.getPrice());
                //notifySubscriber(stockname);
            } else {
                currentStock.setPrice(currentStock.getPrice() - price);
                notifySubscriber(stockname);
            }
        }
    }

    public void changeInCount(String stockname, int count) {
        Stock currentStock = stocks.get(stockname);
        int currentCount = currentStock.getCount() + count;
        if (currentCount < 0) {
            System.out.println("Stock count can't be negative");
            currentStock.setCount(currentStock.getCount());
            //notifySubscriber(stockname);
        } else {
            currentStock.setCount(currentStock.getCount() + count);
            notifySubscriber(stockname);
        }
    }

    @Override
    public void addSubscriber(Observer observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);

        }
    }

    @Override
    public void removeSubscriber(Observer observer) {
        if (observerList.contains(observer))
            observerList.remove(observer);
    }

    @Override
    public void notifySubscriber(String name) {
        for (Observer observer : observerList) {
            observer.update(name);
        }
    }


}
