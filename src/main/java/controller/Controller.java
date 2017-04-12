package controller;

import model.FeuilleDessin;
import model.Tortue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by Quentin on 12/04/2017.
 */
public class Controller{
    Tortue tortueCourante;
    FeuilleDessin listeTortue;
    int segmentValue;

    public Controller() {
        this.tortueCourante = new Tortue();
        listeTortue = new FeuilleDessin();
        segmentValue = 40;
    }

    public void init(int hauteur, int largeur){
        tortueCourante.setPosition(hauteur, largeur);
        listeTortue.addTortue(tortueCourante);
    }

    public void doAction(ActionEvent e)
    {
        int nbCotes;
        String action = e.getActionCommand();
        switch(action){
            case "Avancer":
                System.out.println("commande avancer");
                tortueCourante.avancer(segmentValue);
                break;
            case "Droite":
                tortueCourante.droite(segmentValue);
                break;
            case "Gauche":
                tortueCourante.gauche(segmentValue);
                break;
            case "Lever" :
                tortueCourante.leverCrayon();
                break;
            case "Baisser" :
                tortueCourante.baisserCrayon();
                break;
            case "Carre" :
                tortueCourante.carre(segmentValue);
                break;
            case "Poly":
                nbCotes = 8;
                tortueCourante.poly(segmentValue, nbCotes);
                break;
            case "Spirale":
                nbCotes = 6;
                int nbSegments = 40;
                tortueCourante.spirale(segmentValue, nbSegments, nbCotes);
                break;
            case "Effacer" :
                listeTortue.reset();
                break;
            case "Quitter" :
                System.exit(0);
            default:
                break;
        }
        listeTortue.notifyObservers();
    }

    public void setCouranteColor(int n) {
        tortueCourante.setColor(n);
    }

}
