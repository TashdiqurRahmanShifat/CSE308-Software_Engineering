public class StrawberryShakeBuilder implements ShakeBuilder
{
    private Shake shake;
    public StrawberryShakeBuilder()
    {
        shake=new Shake("Strawberry Shake",200);
        shake.addIngredient("Milk");
        shake.addIngredient("Sugar");
    }
    public ShakeBuilder addStrawberrySyrup()
    {
        shake.addIngredient("Strawberry Syrup");
        return this;
    }
    public ShakeBuilder addStrawberryIceCream()
    {
        shake.addIngredient("Strawberry Ice Cream");
        return this;
    }
    public Shake build()
    {
        return shake;
    }
}
