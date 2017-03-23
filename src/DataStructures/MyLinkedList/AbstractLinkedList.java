package DataStructures.MyLinkedList;

/**
 * Created by andres on 16/03/17.
 * Project AirWar in EstructurasDeDatos.ListasEnlazadas
 */
public abstract class AbstractLinkedList <T>{

    protected Node<T> head;
    protected int length;

    //Methods for adding nodes
    public void addNext(T object){
        addNext(new Node(object, null, null));
    }

    public void addNext(Node node){

        int position = 0;

        if (head != null){
            while (node.getNext() != null) {
                position += 1;
            }
        }
        addInPosition(node, position);
    }

    public void addLast(T object){
        addLast(new Node(object, null, null));
    }

    public void addLast(Node node){
        addInPosition(node, length);
    }

    public abstract void addInPosition(Node node, int position);

    //Methods for removing nodes
    public void removeFirst(){
        if (length > 0) {
            removeInPosition(0);
        }
    }

    public void removeLast(){
        if (length > 0) {

            if (length != 1){
                removeInPosition(length);
            } else {
                removeFirst();
            }
        }
    }

    public abstract void removeInPosition(int position);

    //Methods for substitutions/search
    public void substitute(T object, int position){

        Node aux = head;

        try {

            for (int i = 0; i < position; i++){
                aux = aux.getNext();
            }

            aux.setObject(object);

        } catch (Exception e){

            throw e;
        }
    }

    public boolean isThere(T object){

        boolean result = false;

        if (whereIs(object) != -1){
            result = true;
        }

        return result;
    }

    public int whereIs(T object){

        Node aux = head;
        int result = -1;

        for (int j = 0; j < length; j++){
            if (object.equals(aux.getObject())){
                result = j;
                break;
            }
            aux = aux.getNext();
        }

        return result;
    }

    //Methods switch
    public abstract void switchNode(int position1, int position2);
}
