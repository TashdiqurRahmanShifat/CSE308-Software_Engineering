public class ChocolateShakeBuilder implements ShakeBuilder
{
    private Shake shake;
    public ChocolateShakeBuilder()
    {
        shake=new Shake("Chocolate Shake",230);
        shake.addIngredient("Milk");
        shake.addIngredient("Sugar");
    }
    public ShakeBuilder addChocolateSyrup()
    {
        shake.addIngredient("Chocolate Syrup");
        return this;
    }
    public ShakeBuilder addChocolateIceCream()
    {
        shake.addIngredient("Chocolate Ice Cream");
        return this;
    }
    public Shake build()
    {
        return shake;
    }
}
