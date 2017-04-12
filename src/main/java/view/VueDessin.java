package view;

import model.FeuilleDessin;
import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Quentin on 12/04/2017.
 */
public class VueDessin extends JPanel implements Observer {

    ArrayList<Tortue> listeTortue;

    public VueDessin(){
        listeTortue = new ArrayList<Tortue>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        //TO CHANGE
        for(Iterator it = listeTortue.iterator(); it.hasNext();) {
            Tortue t = (Tortue) it.next();
            t.drawTurtle(g);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof FeuilleDessin){
            listeTortue = ((FeuilleDessin) o).getTortues();
        }
    }
}
