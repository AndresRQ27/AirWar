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

    public Node getHead(){
        return head;
    }

    @Override
    public void swap(int position1, int position2){

        try {
            if (position1 > position2) {
                int temp = position1;
                position1 = position2;
                position2 = temp;
            }

            Node aux1 = head;
            Node aux2 = head;

            for (int i = 0; i < position2 - 1; i++){
                if (i == position1 - 1){
                    aux1 = aux2;
                } else if (i == position2 - 1){
                    break;
                }
                aux2 = aux2.getNext();
            }

            Node change1 = aux1.getNext();
            Node change2 = aux2.getNext();
            Node temp = change1.getNext();

            aux1.setNext(change2);
            aux2.setNext(change1);

            change1.setNext(change2.getNext());
            change2.setNext(temp);


        } catch (Exception e){
            System.out.printf("%s%n", "Input unavailable");
        }
    }
}
