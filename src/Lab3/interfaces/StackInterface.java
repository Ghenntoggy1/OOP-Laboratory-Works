package Lab3.interfaces;

public interface StackInterface<T> {
    void push(T newElement);
    T pop();
    T peek();
    T search();
    boolean isEmpty();
    String toString();
}
