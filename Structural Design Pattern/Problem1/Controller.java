public class Controller
{
    private Task currentUser;
    public void login(String name)
    {
        if(name.equalsIgnoreCase("crew1"))
        {
            Crewmate crew=new Crewmate();
            currentUser=crew;
        }
        else if (name.equalsIgnoreCase("imp1"))
        {
            currentUser=new Imposter(new Crewmate());
        }
        currentUser.welcome();
        System.out.println();
    }
    public void work(String name)
    {
        if(currentUser!=null)
        {
            if (name.equalsIgnoreCase("work"))
            {
                currentUser.doingWork();
            }
        }
        System.out.println();
    }
    public void repair(String name)
    {
        if(currentUser!=null)
        {
            if (name.equalsIgnoreCase("repair"))
            {
                currentUser.doingRepair();
            }
        }
        System.out.println();
    }
    public void logout()
    {
        if(currentUser!=null)
        {
            currentUser.logout();
        }
        currentUser=null;
        System.out.println();
    }

}
