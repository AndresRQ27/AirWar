package DataStructures.MyLinkedList;

/**
 * Created by andres on 15/04/17.
 * AirWar
 * DataStructures.MyLinkedList
 */
public class MyQueue<T> extends SimpleLinkedList{

    public T peek(){
        return (T) head.getObject();
    }

    public void enqueue(T object){
        addLast(object);
    }

    public T dequeue (){
        T result = peek();
        removeFirst();
        return result;
    }
}
