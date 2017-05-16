package model;

import view.VueTortue;

import java.awt.*;

/**
 * Created by Quentin on 16/05/2017.
 */
public class ObstacleBord implements Obstacle {

    public boolean isColliding(Point pointStart, Point pointEnd) {
        if(pointEnd.x <0 || pointEnd.x > VueTortue.LARGEUR || pointEnd.y <0 || pointEnd.y > VueTortue.HAUTEUR)
            return true;
        return false;
    }

    public void drawObstacle(Graphics g){
        return;
    }
}
