import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<Shake> shakeList;
    public Order()
    {
        this.shakeList=new ArrayList<>();
    }
    public void addShake(Shake shake)
    {
        shakeList.add(shake);
    }
    public List<Shake>getShakeList()
    {
        return shakeList;
    }
    public double getTotalPrice()
    {
        double totalPrice=0;
        for(int i=0;i<shakeList.size();i++)
        {
            totalPrice+=shakeList.get(i).getPrice();
            totalPrice+=shakeList.get(i).getAdditional_price();
        }
        return totalPrice;
    }

}
