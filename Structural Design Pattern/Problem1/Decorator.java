abstract class Decorator implements Task {
    protected Task task;
    public Decorator(Task task)
    {
        this.task=task;
    }


    public void welcome()
    {
        task.welcome();
    }

    public void doingWork()
    {
        task.doingWork();
    }
    public void doingRepair()
    {
        task.doingRepair();
    }


    public void logout()
    {
        task.logout();
    }
}
