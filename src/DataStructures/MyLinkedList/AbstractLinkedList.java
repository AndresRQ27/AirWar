package DataStructures.MyLinkedList;

/**
 * Created by andres on 16/03/17.
 * Project AirWar in EstructurasDeDatos.ListasEnlazadas
 */
public abstract class AbstractLinkedList <T>{

    Node<T> head;
    int length;

    /**
     * add object as a node to the first place in the list
     * @param object object of the node
     */
    public void addFirst(T object){
        addFirst(new Node(object));
    }

    /**
     * add as the first item in the list
     * @param node node to add to the linked list
     */
    private void addFirst(Node node){
        addInPosition(node, 0);
    }

    /**
     * add object as the last node in the list
     * @param object object of the node
     */
    void addLast(T object){
        addLast(new Node(object));
    }

    /**
     * add node in the last place of the list
     * @param node node to add to the list
     */
    private void addLast(Node node){
        addInPosition(node, length);
    }

    /**
     * add object in the position specified
     * @param object object to add
     * @param position position to add
     */
    public void addInPosition(T object, int position){

        if (position > length){
            position = length;
        } else if (position < 0){
            position = 0;
        }
        addInPosition(new Node(object), position);
    }

    /**
     * add node in the position said
     * @param node node to add in the list
     * @param position position where to add the node
     */
    protected abstract void addInPosition(Node node, int position);

    /**
     * remove the first node of the list
     */
    public void removeFirst(){
        if (length > 0) {
            removeInPosition(0);
        }
    }

    /**
     * remove the last node of the list
     */
    public void removeLast(){
        if (length > 0) {

            if (length != 1){
                removeInPosition(length);
            } else {
                removeFirst();
            }
        }
    }

    /**
     * remove the node in the position said
     * @param position position where to remove the node
     */
    protected abstract void removeInPosition(int position);

    /**
     * Substitute the object in the node said
     * @param object new object of the node
     * @param position position of the node where to replace the object
     */
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

    /**
     * Method that asks the list if it has a certain object
     * @param object object to search in the list
     * @return @true if the object is in the list, @false else
     */
    public boolean isThere(T object){

        boolean result = false;

        if (whereIs(object) != -1){
            result = true;
        }

        return result;
    }

    /**
     * return the position of the object that's being searched
     * @param object object to look for
     * @return @int with the position of the node in the list
     */
    private int whereIs(T object){

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

    /**
     * return the object in the position asked. useful if trying to use it as an array
     * @param position int of the position asked
     * @return object in the position
     */
    public T whatsIn(int position){

        T result = null;

        if (position < length) {
            Node aux = head;

            for (int i = 0; i < position; i++) {
                aux = aux.getNext();
            }

            result = (T) aux.getObject();

        } else {
            System.out.println("Position Not Found");
        }

        return result;
    }

    /**
     * Method that switch the position of two nodes
     * @param position1 position of the first node to swap
     * @param position2 position of the second node to swap
     */
    public abstract void swap(int position1, int position2);

    public int getlength(){
        return this.length;
    }
}
