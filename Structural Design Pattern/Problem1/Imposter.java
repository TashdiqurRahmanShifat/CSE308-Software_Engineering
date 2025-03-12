public class Imposter extends Decorator
{
    public Imposter(Task task) {
        super(task);
    }

    public void kill()
    {
        System.out.println("Trying to kill a crewmate.");
        System.out.println("Successfully killed a crewmate.");
    }
    public void damage()
    {
        System.out.println("Damaging the spaceship.");
    }
    public void welcome()
    {
        task.welcome();
        System.out.println("We won’t tell anyone; you are an imposter.");
    }
    public void doingWork() {
        task.doingWork();
        kill();

    }
    public void doingRepair()
    {
        task.doingRepair();
        damage();
    }
    public void logout()
    {
        task.logout();
        System.out.println("See you again Comrade Imposter.");
    }

}
