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
    private ArrayList<Obstacle> listeObstacle;

    public FeuilleDessin() {
        tortues = new ArrayList<Tortue>();
        tortueCourante = 0;
        listeObstacle = new ArrayList<>();
        ObstacleBord bord = new ObstacleBord();
        ObstacleCercle cercle = new ObstacleCercle(50, new Point(0, 0));
        listeObstacle.add(bord);
        listeObstacle.add(cercle);
        for (int i =0; i< 5; i++){
            listeObstacle.add(new ObstacleCercle());
            listeObstacle.add(new ObstacleRectangle());
        }
    }

    public void ajouterObstacles(){
        listeObstacle.clear();
        listeObstacle.add(new ObstacleBord());
        for (int i =0; i< 5; i++){
            listeObstacle.add(new ObstacleCercle());
            listeObstacle.add(new ObstacleRectangle());
        }
    }

    public void creerLabyrinthe(int hauteur, int largeur){

        listeObstacle.clear();
        listeObstacle.add(new ObstacleBord());
        listeObstacle.add(new ObstacleRectangle(new Point(0,0),2*largeur, (int) (hauteur*0.20)));
        listeObstacle.add(new ObstacleRectangle(new Point(0,0), (int) (largeur*0.30), hauteur*2));
        listeObstacle.add(new ObstacleRectangle(new Point(2*largeur,0), (int) (largeur*0.30), hauteur*2));
        listeObstacle.add(new ObstacleRectangle(new Point(0,2*hauteur), largeur*2, (int) (hauteur*0.20)));
        listeObstacle.add(new ObstacleRectangle(new Point(0,hauteur), largeur, (int) (hauteur*0.20)));
        listeObstacle.add(new ObstacleRectangle(new Point((int) (largeur*1.25),hauteur), largeur, (int) (hauteur*0.20)));
        listeObstacle.add(new ObstacleRectangle(new Point((int) (largeur*1.25), (int) (hauteur*0.75)), (int) (largeur*0.25), (int) (hauteur*0.25)));
        listeObstacle.add(new ObstacleRectangle(new Point(0, (int) (hauteur*1.40)), (int) (largeur*1.75), (int) (hauteur*0.25)));
        listeObstacle.add(new ObstacleRectangle(new Point(largeur, (int) (hauteur*1.40)), (int) (largeur*0.25), (int) (hauteur*0.5)));

    }

    public void dessinerMickey(int hauteur, int largeur){
        listeObstacle.clear();
        listeObstacle.add(new ObstacleBord());
        listeObstacle.add(new ObstacleCercle((int) (largeur*0.5),new Point((int)(largeur*0.25+0.5*largeur),(int) (0.75*hauteur))));
        listeObstacle.add(new ObstacleCercle((int) (largeur*0.5),new Point((int)(largeur*1.75+0.5*largeur),(int) (0.75*hauteur))));
        listeObstacle.add(new ObstacleCercle((int) (largeur*0.75),new Point((int)(largeur+0.5*largeur),(int) (hauteur*1.25))));

    }

    public ArrayList<Obstacle> getListeObstacle() {
        return listeObstacle;
        listeObstacle = new ArrayList<>();
        ObstacleBord bord = new ObstacleBord();
        ObstacleCercle cercle = new ObstacleCercle(50, new Point(200, 192));
        listeObstacle.add(bord);
        listeObstacle.add(cercle);
        for (int i =0; i< 5; i++){
            listeObstacle.add(new ObstacleCercle());
            listeObstacle.add(new ObstacleRectangle());
        }
    }

    public ArrayList<Obstacle> getListeObstacle() {
        return listeObstacle;
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

    public boolean isColliding(Point pointStart, Point pointEnd){
        for (Obstacle o:listeObstacle) {
            if(o.isColliding(pointStart, pointEnd)){
                return true;
            }
        }
        return false;
    }
}
