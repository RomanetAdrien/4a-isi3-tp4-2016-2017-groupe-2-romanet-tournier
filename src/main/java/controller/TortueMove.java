package controller;

import model.Segment;
import model.Tortue;
import view.VueDessin;
import view.VueTortue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Quentin on 03/05/2017.
 */
public class TortueMove implements Runnable {
    Controller controller;

    public TortueMove(Controller controller){
        this.controller = controller;
    }
    @Override
    public void run() {
        int nbTortue = 50;
        for (int i=0; i<nbTortue;i++){
            ActionEvent action = new ActionEvent(this,0,"Nouvelle");
            controller.doAction(action);
        }
        while(true){
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            ArrayList<Integer> listLength = new ArrayList<>();
            ArrayList<ActionEvent> moves = new ArrayList();
            for (int i=0; i<nbTortue;i++){

                controller.setTortueCourante(i);

                Segment deplacement = controller.moveTurtle();
                if(deplacement == null){
                    Random rand = new Random();
                    int num = rand.nextInt(360);
                    listLength.add(num);
                    controller.setSegmentValue(num);
                    ActionEvent action = new ActionEvent(this,0,"Droite");
                    moves.add(action);

                    int num2 = rand.nextInt(5);
                    listLength.add(num2);
                    controller.setSegmentValue(num2);
                    ActionEvent action2 = new ActionEvent(this,0,"Avancer");
                    moves.add(action2);
                }
                else{
                    int num = (int)(Math.atan2(deplacement.getDeplacementY(),deplacement.getDeplacementX())/Tortue.ratioDegRad);
                    listLength.add(num);
                    controller.setSegmentValue(num);
                    ActionEvent action = new ActionEvent(this,0,"Angle");
                    moves.add(action);

                    int num2 = deplacement.getNorme();
                    listLength.add(num2);
                    controller.setSegmentValue(num2);

                    ActionEvent action2 = new ActionEvent(this,0,"Avancer");
                    moves.add(action2);
                }

            }
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0 ; i<nbTortue; i++){
                controller.setSegmentValue(listLength.get(i*2));
                controller.setTortueCourante(i);
                controller.doAction(moves.get(i*2));
                controller.setSegmentValue(listLength.get(i*2+1));
                controller.doAction(moves.get(i*2+1));
            }

                /*
                Random rand = new Random();
                controller.setSegmentValue(rand.nextInt(360));
                ActionEvent action = new ActionEvent(this,0,"Droite");
                controller.doAction(action);

                controller.setSegmentValue(rand.nextInt(100));
                ActionEvent action2 = new ActionEvent(this,0,"Avancer");
                controller.doAction(action2);*/
        }
    }
}
