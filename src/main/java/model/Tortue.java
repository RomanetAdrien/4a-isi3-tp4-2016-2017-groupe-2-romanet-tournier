package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created by Quentin on 12/04/2017.
 */
public class Tortue extends Observable {

    protected static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    private static int counter;

    protected ArrayList<Segment> listSegments; // Trace de la tortue

    protected int x, y;
    protected int dir;
    protected boolean crayon;
    protected int coul;
    private int numTortue;

    public void setColor(int n) {coul = n;}
    public int getColor() {return coul;}

    public Tortue() {
        listSegments = new ArrayList<Segment>();
        reset();
        numTortue = counter;
        counter++;
    }

    public void reset() {
        x = 0;
        y = 0;
        dir = -90;
        coul = 0;
        crayon = true;
        listSegments.clear();
    }

    public int getTortueNum(){return this.numTortue;}

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void drawTurtle (Graphics graph) {
        if (graph==null)
            return;

        // Dessine les segments
        for(Iterator it = listSegments.iterator(); it.hasNext();) {
            Segment seg = (Segment) it.next();
            seg.drawSegment(graph);
        }

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(x,y);
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta=ratioDegRad*(-dir);
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float)rb / (float)rp );
        //Rayon de la fleche
        double r=Math.sqrt( rp*rp + rb*rb );
        //Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
                (int) Math.round(p.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x,p2.y);
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graph.setColor(Color.green);
        graph.fillPolygon(arrow);
    }

    protected Color decodeColor(int c) {
        switch(c) {
            case 0: return(Color.black);
            case 1: return(Color.blue);
            case 2: return(Color.cyan);
            case 3: return(Color.darkGray);
            case 4: return(Color.red);
            case 5: return(Color.green);
            case 6: return(Color.lightGray);
            case 7: return(Color.magenta);
            case 8: return(Color.orange);
            case 9: return(Color.gray);
            case 10: return(Color.pink);
            case 11: return(Color.yellow);
            default : return(Color.black);
        }
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
        int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));

        if (crayon==true) {
            Segment seg = new Segment();

            seg.ptStart.x = x;
            seg.ptStart.y = y;
            seg.ptEnd.x = newX;
            seg.ptEnd.y = newY;
            seg.color = decodeColor(coul);

            listSegments.add(seg);
        }

        x = newX;
        y = newY;
    }

    public void droite(int ang) {
        dir = (dir + ang) % 360;
    }

    public void gauche(int ang) {
        dir = (dir - ang) % 360;
    }

    public void baisserCrayon() {
        crayon = true;
    }

    public void leverCrayon() {
        crayon = false;
    }

    public void couleur(int n) {
        coul = n % 12;
    }

    public void couleurSuivante() {
        couleur(coul+1);
    }

    public void carre(int tailleSegment) {
        for (int i=0;i<4;i++) {
            avancer(tailleSegment);
            droite(90);
        }
    }

    public void poly(int tailleSegment, int nbCotes) {
        for (int j=0;j<nbCotes;j++) {
            avancer(tailleSegment);
            droite(360/nbCotes);
        }
    }

    public void spirale(int tailleSegment, int nbSegments, int nbCotes) {
        for (int i = 0; i < nbSegments; i++) {
            couleur(coul+1);
            avancer(tailleSegment);
            droite(360/nbCotes);
            tailleSegment = tailleSegment+1;
        }
    }

    public void change(){
        this.setChanged();
        this.notifyObservers();
    }
}
