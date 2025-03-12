import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main
{
    private static FileSystemComponent root;
    private static FileSystemComponent currentState;
    private static String command;
    private static String name;
    private static Scanner scn=new Scanner(System.in);
    private static int driveSize=500;
    private static int folderSize=50;
    private static int fileSize;
    private static String [] token;


    public static void createDrive()
    {
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String drivetime=time.format(format);
        currentState=new Composite(name,driveSize,"Drive",drivetime,currentState);
        currentState.getParent().addComponent(currentState);
    }
    public static void createFolder()
    {
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String foldertime=time.format(format);
        currentState=new Composite(name,folderSize,"Folder",foldertime,currentState);
        currentState.getParent().addComponent(currentState);
    }
    public static void createFile()
    {
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String filetime=time.format(format);
        currentState=new Leaf(name,fileSize,"File",filetime,currentState);
        currentState.getParent().addComponent(currentState);
    }

    private static void recursiveDelete(FileSystemComponent componentToDelete)
    {
        if (componentToDelete.getType().equalsIgnoreCase("file"))
        {
            System.out.println("Are you sure you want to delete "+componentToDelete.getName()+" file? (y/n)");
            command = scn.nextLine();
            if (command.equalsIgnoreCase("y"))
            {
                System.out.println(componentToDelete.getName() + " is deleted");
                componentToDelete.getParent().removeComponent(componentToDelete);
            }
        }
        else
        {
            for (int i = componentToDelete.getChildCount()-1;i>=0;i--)
            {
                recursiveDelete(componentToDelete.getChildIndex(i));
            }

            System.out.println(componentToDelete.getName() + " is deleted");
            componentToDelete.getParent().removeComponent(componentToDelete);
        }
    }

    public static void main(String[] args)
    {
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String roottime=time.format(format);
        root=new Composite("HDD",800,"root",roottime,null);
        currentState=root;

        while(true)
        {
            System.out.println("Current Location  "+currentState.getType()+"-"+currentState.getName());
            System.out.println("For closing server,write 'exit'");
            command=scn.nextLine();
            token=command.split(" ");
            if (token[0].equalsIgnoreCase("mkdrive"))
            {
                if(currentState.getType().equalsIgnoreCase("root"))
                {
                    name=token[1];
                    createDrive();
                    System.out.println("New Drive "+name+" is Created");
                }
                else
                {
                    System.out.println("You can't create any drive under a drive");
                }
            }
            else if (token[0].equalsIgnoreCase("mkdir"))
            {
                if(currentState.getType().equalsIgnoreCase("drive")||currentState.getType().equalsIgnoreCase("folder"))
                {
                    name=token[1];
                    createFolder();
                    System.out.println("New Folder "+name+" is Created");
                }
                else
                {
                    System.out.println("For creating a folder,you must first create a drive");
                }
            }
            else if (token[0].equalsIgnoreCase("touch"))
            {
                if(currentState.getType().equalsIgnoreCase("drive")||currentState.getType().equalsIgnoreCase("folder"))
                {
                    name=token[1];
                    fileSize= Integer.parseInt(token[2]);
                    createFile();
                    System.out.println("New File "+name+" is Created");
                }
                else
                {
                    System.out.println("For creating a file,you must first create a drive & you can't create a file under a file");
                }
            }
            else if (token[0].equalsIgnoreCase("cd"))
            {
                int temp=0;
                name=token[1];
                if(name.equalsIgnoreCase("~"))
                {
                    currentState=root;
                    temp=1;
                }
                else if(name.equalsIgnoreCase("\\")&&(!name.equalsIgnoreCase("~")))
                {
                    if(currentState==root)
                    {
                        System.out.println("You have already reached root");
                        temp=1;
                    }
                    else
                    {
                        currentState = currentState.getParent();
                        temp = 1;
                    }
                }

                if(temp==0)
                {
                    int numberOfDestination = currentState.getChildCount();
                    int flag = 0;
                    if (numberOfDestination == 0) {
                        System.out.println("You have no further destination to move");
                    } else {

                        for (int i = 0; i < numberOfDestination; i++) {
                            if (currentState.getChildIndex(i).getName().equalsIgnoreCase(name)) {
                                currentState = currentState.getChildIndex(i);
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 0) {
                            System.out.println("No directory with this name");
                        }
                    }
                }
            }
            else if(token[0].equalsIgnoreCase("delete"))
            {
                name=token[1];
                int flag=0;
                if(!name.equalsIgnoreCase("-r")&&(currentState.getName().equalsIgnoreCase(name)))
                {
                    if(currentState.getType().equalsIgnoreCase("root"))
                    {
                        System.out.println("Root can't be deleted");
                        flag=1;
                    }
                    if(flag==0)
                    {
                        FileSystemComponent systemComponent = currentState.getParent();
                        if (systemComponent != null) {
                            if(currentState.getChildCount()==0)
                            {
                                System.out.println(currentState.getName()+" is deleted");
                                systemComponent.removeComponent(currentState);
                                currentState = systemComponent;
                            }
                            else
                            {
                                System.out.println("You can't delete this using this command because it is not empty.");
                                System.out.println("For deleting this you have to first make it empty or use recursive delete command");
                            }
                        }
                    }
                }
                else if (token[1].equalsIgnoreCase("-r")&& currentState.getName().equalsIgnoreCase(token[2]))
                {
                    if(currentState.getType().equalsIgnoreCase("root"))
                    {
                        System.out.println("Root can't be deleted");
                        flag=1;
                    }
                    if(flag==0) {
                        recursiveDelete(currentState);
                        currentState = currentState.getParent();
                    }
                }
                else
                {
                    System.out.println("There is no directory with this name");
                }
            }
            else if (token[0].equalsIgnoreCase("list"))
            {
                System.out.println(currentState.getList());
            }
            else if (token[0].equalsIgnoreCase("ls"))
            {
                //FileSystemComponent tempstate=null;
                if(token.length>1)
                {
                    FileSystemComponent state = null;
                    name = token[1];
                    int flag = 0, temp = 0;
                    if (currentState.getName().equalsIgnoreCase(name)) {
                        currentState.details();
                        flag = 1;
                    } else {
                        for (FileSystemComponent component : currentState.children)//for children
                        {
                            if (component.getName().equalsIgnoreCase(name)) {
                                state = component;
                                temp = 1;
                                break;
                            }
                            if (temp == 0) {
                                for (FileSystemComponent childComp : component.children)//for grandchilden
                                {
                                    if (childComp.getName().equalsIgnoreCase(name)) {
                                        state = childComp;
                                        break;
                                    }

                                }
                            }

                        }
                    }

                    if (state != null && flag == 0) {
                        currentState = state;
                        currentState.details();
                    } else if (state == null && flag == 0) {
                        System.out.println("No such directory with this name");

                    }
                }
                else
                {
                    System.out.println("You have not given the component name");
                }
            }
            else if (token[0].equalsIgnoreCase("exit"))
            {
                System.out.println("Server is closing..");
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid Command!");
            }
        }

    }
}
