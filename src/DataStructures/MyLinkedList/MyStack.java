package DataStructures.MyLinkedList;

/**
 * Created by andres on 15/04/17.
 * AirWar
 * DataStructures.MyLinkedList
 */
public class MyStack<T> extends SimpleLinkedList{

    private T peek(){
        return (T) head.getObject();
    }

    public void push(T object){
        addFirst(object);
    }

    public T pop (){
        T result = peek();
        removeFirst();
        return result;
    }
}