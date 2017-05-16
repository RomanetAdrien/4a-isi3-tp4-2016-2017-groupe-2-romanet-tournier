package model;

import java.awt.*;

/**
 * Created by Quentin on 12/04/2017.
 */
public class Segment {

    public Point ptStart, ptEnd;
    public Color color;

    public Segment() {
        ptStart = new Point(0,0);
        ptEnd = new Point(0,0);
    }

    public Segment(int x1, int x2, int y1, int y2) {
        ptStart = new Point(x1,y1);
        ptEnd = new Point(x2,y2);
    }

    public int getDeplacementX(){
        return this.ptEnd.x-this.ptStart.x;
    }

    public int getDeplacementY(){
        return this.ptEnd.y-this.ptStart.y;
    }

    public int getNorme(){
        double diffX = this.ptEnd.x-this.ptStart.x;
        double diffY = this.ptEnd.y-this.ptStart.y;

        double norme = Math.sqrt(Math.pow(diffX,2) + Math.pow(diffY,2));
        return (int)norme;
    }

    public void drawSegment(Graphics graph) {
        if (graph==null)
            return;

        graph.setColor(color);
        graph.drawLine(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y);
    }

    public void setPtEnd(Point ptEnd) {
        this.ptEnd = ptEnd;
    }
}
