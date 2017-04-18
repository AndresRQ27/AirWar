package Aircraft;

import DataStructures.MyLinkedList.AbstractLinkedList;
import DataStructures.MyLinkedList.SimpleLinkedList;

/**
 * Created by andres on 15/04/17.
 * AirWar
 * Aircraft
 */
public class EnemiesList {

    public static final AbstractLinkedList<EnemyTypes> planesList = new SimpleLinkedList();
    public static final AbstractLinkedList<EnemyTypes> turretList = new SimpleLinkedList();

        public EnemiesList(){
            planesList.addFirst(EnemyTypes.BOMBER);
            planesList.addFirst(EnemyTypes.JET);
            planesList.addFirst(EnemyTypes.KAMIKAZE);

            turretList.addFirst(EnemyTypes.MISSILETURRET);
            turretList.addFirst(EnemyTypes.TURRET);
        }
}
