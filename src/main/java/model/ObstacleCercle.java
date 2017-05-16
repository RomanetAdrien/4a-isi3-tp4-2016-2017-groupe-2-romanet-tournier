package model;

import view.VueDessin;
import view.VueTortue;

import java.awt.*;
import java.util.Random;

/**
 * Created by Quentin on 16/05/2017.
 */
public class ObstacleCercle implements Obstacle{
    public int rayon;
    public Point centre;

    public ObstacleCercle(){
        Random randx = new Random();
        int x = randx.nextInt(VueTortue.LARGEUR-10);
        Random randy = new Random();
        int y = randy.nextInt(VueTortue.HAUTEUR-10);
        Random randr = new Random();
        int r = randr.nextInt(60);
        this.centre = new Point(x,y);
        this.rayon=r;
    }

    public ObstacleCercle(int rayon, Point centre){
        this.rayon=rayon;
        this.centre=centre;
    }

    public boolean isColliding(Point pointStart, Point pointEnd) {
        if(pointEnd.distance(this.centre)<=this.rayon)
            return true;
        return false;
    }

    public void drawObstacle (Graphics graph) {
        if (graph==null)
            return;
        graph.setColor(Color.red);
        graph.fillOval((int)centre.getX()-rayon,(int)centre.getY()-rayon, 2*rayon, 2*rayon);
    }


}
