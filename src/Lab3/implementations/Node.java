package Lab3.implementations;

public class Node {
    private Object storedObject;
    private Node pointer;

    public Node(Object storedObject) {
        this.storedObject = storedObject;
        this.pointer = null;
    }

    public Object getStoredObject() {
        return storedObject;
    }

    public Node getPointer() {
        return pointer;
    }

    public void setPointer(Node pointer) {
        this.pointer = pointer;
    }
}