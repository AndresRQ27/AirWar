package DataStructures.MyLinkedList;

/**
 * Created by andres on 16/03/17.
 * Project AirWar in DataStructures.AbstractLinkedList
 */
public class SimpleLinkedList extends AbstractLinkedList {

    @Override
    public void addInPosition(Node node, int position) {

        if (position == 0){

            node.setNext(head);
            this.head = node;

        } else {

            Node aux1 = head;
            Node aux2 = head.getNext();

            for (int i = 1; i < position; i++){
                aux1 = aux2;
                aux2 = aux2.getNext();
            }

            aux1.setNext(node);
            node.setNext(aux2);
        }

        length += 1;
    }

    @Override
    public void removeInPosition(int position){

        if (position == 0){

            this.head = this.head.getNext();

        } else {
            Node aux1 = head;
            Node aux2 = head.getNext();

            for (int i = 1; i < position; i++){
                aux1 = aux2;
                aux2 = aux2.getNext();
            }

            aux1.setNext(aux2.getNext());
        }

        length -= 1;
    }
}
