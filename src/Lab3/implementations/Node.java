package Lab3.implementations;

public class Node<T> {
    private T storedObject;
    private Node<T> pointer;

    public Node(T storedObject) {
        this.storedObject = storedObject;
        this.pointer = null;
    }

    public T getStoredObject() {
        return storedObject;
    }

    public void setStoredObject(T storedObject) {
        this.storedObject = storedObject;
    }

    public Node<T> getPointer() {
        return pointer;
    }

    public void setPointer(Node<T> pointer) {
        this.pointer = pointer;
    }
}
