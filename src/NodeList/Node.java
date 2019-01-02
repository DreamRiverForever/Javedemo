package NodeList;

public class Node {
    Node previous;
    Node next;
    Object element;

    public Node(Node previous, Node next, Object element) {
        this.previous = previous;
        this.next = next;
        this.element = element;
    }

    public Node(Object element) {
        this.element = element;
    }
}
