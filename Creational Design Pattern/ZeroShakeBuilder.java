public class ZeroShakeBuilder implements ShakeBuilder
{
    private Shake shake;
    public ZeroShakeBuilder()
    {
        shake=new Shake("Zero Shake",240);
        shake.addIngredient("Milk");
    }
    public ShakeBuilder addVanillaFlavoring()
    {
        shake.addIngredient("Vanilla");
        return this;
    }
    public ShakeBuilder addSugarFreeJello()
    {
        shake.addIngredient("Sugar-Free Jello");
        return this;
    }
    public Shake build()
    {
        return shake;
    }
}
