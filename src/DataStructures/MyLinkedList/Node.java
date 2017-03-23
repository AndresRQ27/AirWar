package DataStructures.MyLinkedList;

/**
 * Created by andres on 16/03/17.
 * Project AirWar in EstructurasDeDatos.ListasEnlazadas
 */
public class Node <T> {

    private Node next;
    private Node previous;
    private T object;

    /**
     *
     * @param object objeto que contiene el nodo
     * @param next
     * @param previous
     */
    public Node(T object, Node next, Node previous){
        this.object = object;
        this.next = next;
        this.previous = previous;
    }

    public Node(T object, Node next){
        this.object = object;
        this.next = next;
        this.previous = null;
    }

    public Node(T object){
        this.object = object;
        this.previous = null;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
