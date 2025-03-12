public class VanillaShakeBuilder implements ShakeBuilder
{
    private Shake shake;
    public VanillaShakeBuilder()
    {
        shake=new Shake("Vanilla Shake",190);
        shake.addIngredient("Milk");
        shake.addIngredient("Sugar");
    }
    public ShakeBuilder addVanillaFlavoring()
    {
        shake.addIngredient("Vanilla");
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
