package controller;

import model.FeuilleDessin;
import model.Tortue;
import view.VueDessin;
import view.VueTortue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by Quentin on 12/04/2017.
 */
public class Controller{
    private Tortue tortueCourante;
    private int segmentValue;
    private FeuilleDessin listeTortue;
    private VueDessin observer;

    public Controller() {
        this.tortueCourante = new Tortue();
        segmentValue = 40;
        listeTortue = new FeuilleDessin();

    }

    public void setObserver(VueDessin observer){
        this.observer = observer;
    }

    public void init(int hauteur, int largeur){
        tortueCourante.setPosition(hauteur, largeur);
        tortueCourante.addObserver(observer);
        observer.addTortue(tortueCourante);
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
                tortueCourante.reset();
                break;
            case "Quitter" :
                System.exit(0);
                break;
            case "Nouvelle" :
                this.newTortue(VueTortue.HAUTEUR, VueTortue.LARGEUR);
            default:
                break;
        }
        tortueCourante.change();
    }



    private void newTortue(int hauteur, int largeur) {
        tortueCourante = new Tortue();
        this.init(hauteur/2,largeur/2);
    }

    public void setTortueCourante(int number){
        tortueCourante = listeTortue.getTortueI(number);
    }

    public void setCouranteColor(int n) {
        tortueCourante.setColor(n);
    }

    public void setSegmentValue(int segmentValue) {
        this.segmentValue = segmentValue;
    }
}
