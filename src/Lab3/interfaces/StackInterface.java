package Lab3.interfaces;

public interface StackInterface<T> {
    void push(T newElement);
    T pop();
    T peek();
    void search(T searchedElement);
    boolean isEmpty();
    String toString();
    void empty();
}
