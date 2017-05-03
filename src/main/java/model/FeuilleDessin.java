package model;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created by Quentin on 12/04/2017.
 */
public class FeuilleDessin  {
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
    private int tortueCourante;

    public FeuilleDessin() {
        tortues = new ArrayList<Tortue>();
        tortueCourante = 0;
    }

    public void addTortue(Tortue o) {
        tortues.add(o);
    }

    public void reset() {
        for (Iterator it = tortues.iterator();it.hasNext();) {
            Tortue t = (Tortue) it.next();
            t.reset();
        }
    }


    public Tortue getTortueI(int number) {
        return tortues.get(number);
    }

    public ArrayList<Tortue> tortuesVues(Tortue tortue){
        int tailleChamp = 100;
        ArrayList<Tortue> seen = new ArrayList<>();
        for(int i=0;i<tortues.size();i++){
            if(i == tortue.getTortueNum()){
                continue;
            }
            int x1 = tortue.x;
            int y1 = tortue.y;
            int x2 = tortues.get(i).x;
            int y2 = tortues.get(i).y;

            if(Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2)) < tailleChamp){
                seen.add(tortues.get(i));
            }
        }
        return seen;
    }
}
