public class Leaf extends FileSystemComponent
{
    public Leaf(String name,int size,String type,String creation_time,FileSystemComponent parent)
    {
        super(name,size,type,creation_time,parent);
    }

    @Override
    public String getDirectory()
    {
        return getParent().getDirectory()+":"+"\\"+getName();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public FileSystemComponent getChildIndex(int index) {
        return null;
    }

    @Override
    public void addComponent(FileSystemComponent comp)
    {

    }

    @Override
    public void removeComponent(FileSystemComponent comp)
    {

    }

    public String getList()
    {
        String path="";
        return path;
    }



}
