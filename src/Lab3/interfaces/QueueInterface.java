package Lab3.interfaces;

public interface QueueInterface {
    void enqueue(Object newElement);
    void deque();
    Object peek();
    void search(Object searchedElement);
    boolean isEmpty();
    void empty();
}
