package model;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created by Quentin on 12/04/2017.
 */
public class FeuilleDessin extends Observable {
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees

    public FeuilleDessin() {
        tortues = new ArrayList<Tortue>();
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

    public ArrayList<Tortue> getTortues() {
        return tortues;
    }
}
