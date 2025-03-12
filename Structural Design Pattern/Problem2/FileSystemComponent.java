import java.util.LinkedList;
import java.util.List;

public abstract class FileSystemComponent
{
    private String name;
    private int size;
    private String type;
    private String creation_time;
    private FileSystemComponent parent;
    List<FileSystemComponent>children;

    public FileSystemComponent(String name, int size, String type, String creation_time,FileSystemComponent parent)
    {
        this.name=name;
        this.size=size;
        this.type=type;
        this.creation_time=creation_time;
        this.parent=parent;
        children=new LinkedList<>();
    }

    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    public String getType()
    {
        return type;
    }

    public String getCreationTime()
    {
        return creation_time;
    }

    public FileSystemComponent getParent()
    {
        return parent;
    }
    public abstract String getDirectory();
    public abstract int getCount();
    public abstract int getChildCount();
    public abstract FileSystemComponent getChildIndex(int index);
    public abstract void addComponent(FileSystemComponent comp);
    public abstract void removeComponent(FileSystemComponent comp);
    public abstract String getList();

    public void details()
    {
        System.out.println("Name:"+getName());
        System.out.println("Type:"+getType());
        System.out.println("Size:"+getSize()+" KB");
        System.out.println("Directory:"+"\""+getDirectory()+"\"");
        System.out.println("Component Count:"+getCount());
        System.out.println("Creation time:"+getCreationTime());
    }
}
