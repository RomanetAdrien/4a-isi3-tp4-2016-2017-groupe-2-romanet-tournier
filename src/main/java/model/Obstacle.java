package model;

import java.awt.*;

/**
 * Created by Quentin on 16/05/2017.
 */
public interface Obstacle {
    boolean isColliding(Point pointStart, Point pointEnd);
    void drawObstacle(Graphics g);
}
