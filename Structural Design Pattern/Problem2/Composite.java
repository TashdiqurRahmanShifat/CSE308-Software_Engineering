public class Composite extends FileSystemComponent
{
    public Composite(String name, int size, String type, String creation_time,FileSystemComponent parent) {
        super(name, size, type, creation_time,parent);
    }

    @Override
    public String getDirectory() {
        if(getParent()==null)
            return getName();
        else return getParent().getDirectory()+":"+"\\"+getName();
    }

    @Override
    public int getCount()
    {
        int componentCount=0;
        for(FileSystemComponent component:children)
        {
            componentCount++;
        }
        return componentCount;
    }

    @Override
    public int getChildCount()
    {
        return children.size();
    }

    @Override
    public FileSystemComponent getChildIndex(int index)
    {
        return children.get(index);
    }

    public String getList()
    {
        String path="";
        for(FileSystemComponent fileSystemComponent:children)
        {
            path=path+fileSystemComponent.getName()+" "+fileSystemComponent.getSize()+"KB"+" "+fileSystemComponent.getCreationTime()+"\n";
        }
        return path;
    }

    @Override
    public void addComponent(FileSystemComponent comp)
    {
        children.add(comp);
    }
    public void removeComponent(FileSystemComponent comp)
    {
        children.remove(comp);
    }
}
