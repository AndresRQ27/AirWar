package Aircraft;

import DataStructures.MyLinkedList.AbstractLinkedList;
import DataStructures.MyLinkedList.SimpleLinkedList;

/**
 * Created by andres on 15/04/17.
 * AirWar
 * Aircraft
 */
public class Enemies {

    public static final AbstractLinkedList<String> planesList = new SimpleLinkedList();
    public static final AbstractLinkedList<String> turretList = new SimpleLinkedList();

        public Enemies(){
            planesList.addFirst("BOMBER");
            planesList.addFirst("JET");
            planesList.addFirst("KAMIKAZE");

            turretList.addFirst("MISSILETURRET");
            turretList.addFirst("TURRET");
        }
}
