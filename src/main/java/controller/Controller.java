package controller;

import model.FeuilleDessin;
import model.Obstacle;
import model.Segment;
import model.Tortue;
import view.VueDessin;
import view.VueTortue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Quentin on 12/04/2017.
 */
public class Controller{
    private Tortue tortueCourante;
    private int segmentValue;
    private FeuilleDessin model;
    private VueDessin observer;

    public Controller() {
        this.tortueCourante = new Tortue();
        segmentValue = 40;
        model = new FeuilleDessin();

    }

    public void setObserver(VueDessin observer){
        this.observer = observer;
    }

    public void init(int hauteur, int largeur){
        tortueCourante.setPosition(hauteur, largeur);
        tortueCourante.addObserver(observer);
        observer.addTortue(tortueCourante);
        model.addTortue(tortueCourante);
        model.creerLabyrinthe(hauteur, largeur);
        //model.dessinerMickey(hauteur,largeur);
        //model.ajouterObstacles();
    }

    public void initObstacles(){
        for (Obstacle o: model.getListeObstacle()) {
            observer.addObstacle(o);
        }
    }

    public void doAction(ActionEvent e)
    {
        int nbCotes;
        String action = e.getActionCommand();
        switch(action){
            case "Avancer":
                tortueCourante.avancer(segmentValue);
                break;
            case "Droite":
                tortueCourante.droite(segmentValue);
                break;
            case "Gauche":
                tortueCourante.gauche(segmentValue);
                break;
            case "Angle":
                tortueCourante.setAngle(segmentValue);
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
        tortueCourante = model.getTortueI(number);
    }

    public void setCouranteColor(int n) {
        tortueCourante.setColor(n);
    }

    public void setSegmentValue(int segmentValue) {
        this.segmentValue = segmentValue;
    }

    public Segment moveTurtle(){
        ArrayList<Tortue> tortuesClose = model.tortuesVues(tortueCourante);
        if(tortuesClose.isEmpty()){
            Random random = new Random();
            Point depart = new Point (tortueCourante.getX(), tortueCourante.getY());
            Point destination = new Point((int)Math.round(tortueCourante.getX()+ random.nextInt(2)),(int)Math.round(tortueCourante.getY()+ random.nextInt(2)));
            Segment segment = new Segment(depart.x, depart.y, destination.x, destination.y);
            turnMovement(depart, destination, segment);
            return segment;
        }
        ArrayList<Segment> segments = new ArrayList<>();
        double distance = 0;
        int compteur = 0;
        for (Tortue tortue:tortuesClose) {
            if(tortue.getLastSegment() != null){
                segments.add(tortue.getLastSegment());
                distance += this.distanceSegment(tortue.getLastSegment());
                compteur++;
            }
        }
        double distanceMoyenne;
        if(compteur == 0){
            return null;
        }
        else{
            distanceMoyenne = distance/compteur;
        }
        double xStartMoyen = 0;
        double yStartMoyen = 0;
        double xEndMoyen = 0;
        double yEndMoyen = 0;
        int n = segments.size();
        for (Segment s: segments){
            xStartMoyen +=s .ptStart.getX();
            yStartMoyen += s.ptStart.getY();
            xEndMoyen += s.ptEnd.getX();
            yEndMoyen += s.ptEnd.getY();
        }
        xStartMoyen = xStartMoyen/n;
        yStartMoyen = yStartMoyen/n;
        xEndMoyen = xEndMoyen/n;
        yEndMoyen = yEndMoyen/n;

        double diffXmoyen = xEndMoyen-xStartMoyen;
        double diffYmoyen = yEndMoyen-yStartMoyen;

        double norme = Math.sqrt(Math.pow(diffXmoyen,2) + Math.pow(diffYmoyen,2));
        double ratio = distanceMoyenne/norme;


        Segment deplacement = new Segment(tortueCourante.getX(),(int)Math.round(tortueCourante.getX()+diffXmoyen*ratio),tortueCourante.getY(),(int)Math.round(tortueCourante.getY()+diffYmoyen*ratio));
        Point depart = new Point (tortueCourante.getX(), tortueCourante.getY());
        Point destination = new Point((int)Math.round(tortueCourante.getX()+diffXmoyen*ratio),(int)Math.round(tortueCourante.getY()+diffYmoyen*ratio));

        for(Tortue t:tortuesClose){
            Point posTortue = new Point(t.getX(),t.getY());
            while(posTortue.distance(destination)<Tortue.personnalSpace){
                Random random = new Random();
                destination.setLocation(destination.x + random.nextInt(20)-10,destination.y + random.nextInt(20)-10);
            }
            deplacement.setPtEnd(destination);
        }
        turnMovement(depart,destination, deplacement);
        return deplacement;

    }

    private void turnMovement(Point depart, Point destination,Segment moveOrigin) {
        Random random = new Random();
        boolean leftOrRight = random.nextBoolean();
        int iterations = 0;
        while(model.isColliding(depart,destination) && iterations<36){
            int theta = leftOrRight ? 10 : -10;
            int destinationX = (int) (Math.cos(theta)*(destination.getX()-depart.getX()) - Math.sin(theta)*(destination.getY()-depart.getY()) + depart.getX());
            int destinationY = (int) (Math.sin(theta)*(destination.getX()-depart.getX()) + Math.cos(theta)*(destination.getY()-depart.getY()) + depart.getY());
            destination.setLocation(destinationX, destinationY);
            moveOrigin.setPtEnd(destination);
            iterations ++;
        }
    }

    public double distanceSegment(Segment segment){
        return segment.ptStart.distance(segment.ptEnd);
    }
}
