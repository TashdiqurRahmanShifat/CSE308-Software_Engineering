public class Crewmate implements Task
{
    @Override
    public void welcome()
    {
        System.out.println("Welcome Crewmate!");
    }
    @Override
    public void doingWork()
    {
        System.out.println("Doing research");
    }

    @Override
    public void doingRepair()
    {
        System.out.println("Repairing the spaceship.");
    }

    @Override
    public void logout()
    {
        System.out.println("Bye Bye crewmate.");
    }
}
