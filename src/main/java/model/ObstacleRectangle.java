package model;

import view.VueTortue;

import java.awt.*;
import java.util.Random;

/**
 * Created by Quentin on 16/05/2017.
 */
public class ObstacleRectangle implements Obstacle {

    private Point topLeft;
    private int width;
    private int height;

    public ObstacleRectangle(){
        Random rand = new Random();
        int x = rand.nextInt(VueTortue.LARGEUR-10);
        int y = rand.nextInt(VueTortue.HAUTEUR-10);
        int width = rand.nextInt(60);
        int height = rand.nextInt(60);
        this.topLeft = new Point(x,y);
        this.width = width;
        this.height = height;
    }

    public ObstacleRectangle(Point topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isColliding(Point pointStart, Point pointEnd) {
        if(pointEnd.x > topLeft.getX() && pointEnd.x <topLeft.getX()+ width){
            if(pointEnd.y > topLeft.getY() && pointEnd.y <topLeft.getY()+ height){
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawObstacle(Graphics graph) {
        if (graph==null)
            return;
        graph.setColor(Color.blue);
        graph.fillRect((int)topLeft.getX(),(int)topLeft.getY(),width , height);
    }
}
