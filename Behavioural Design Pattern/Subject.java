public interface Subject
{
    void addSubscriber(Observer observer);
    void removeSubscriber(Observer observer);
    void notifySubscriber(String name);
}
