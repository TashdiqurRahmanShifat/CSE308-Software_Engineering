import java.util.ArrayList;
import java.util.List;

public class Shake
{
    private String name;
    private List<String> ingredients;
    private double baseprice;
    private double additional_price;
    public Shake(String name,double baseprice)
    {
        this.name=name;
        this.ingredients=new ArrayList<>();
        this.baseprice=baseprice;
        this.additional_price=0;
    }
    public void addIngredient(String ingredient)
    {
        ingredients.add(ingredient);
    }

    public List<String>getIngredients()
    {
        return ingredients;
    }

    public void setAdditionalPrice(double price)
    {
        additional_price+=price;
    }
    public double getAdditional_price()
    {
        return additional_price;
    }
    public double getPrice()
    {
        return baseprice;
    }
    public String getName()
    {
        return name;
    }
}
