public class CoffeeShakeBuilder implements ShakeBuilder
{
    private Shake shake;
    public CoffeeShakeBuilder()
    {
        shake=new Shake("Chocolate Shake",230);
        shake.addIngredient("Milk");
        shake.addIngredient("Sugar");
    }
    public ShakeBuilder addCoffee()
    {
        shake.addIngredient("Coffee");
        return this;
    }
    public ShakeBuilder addJello()
    {
        shake.addIngredient("Jello");
        return this;
    }
    public Shake build()
    {
        return shake;
    }
}
