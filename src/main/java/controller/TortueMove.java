package controller;

import model.Segment;
import model.Tortue;
import view.VueDessin;
import view.VueTortue;

import javax.swing.*;
import java.awt.event.ActionEvent;
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


            for (int i=0; i<nbTortue;i++){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                controller.setTortueCourante(i);

                Segment deplacement = controller.moveTurtle();
                if(deplacement == null){
                    Random rand = new Random();
                    controller.setSegmentValue(rand.nextInt(360));
                    ActionEvent action = new ActionEvent(this,0,"Droite");
                    controller.doAction(action);

                    controller.setSegmentValue(rand.nextInt(5));
                    ActionEvent action2 = new ActionEvent(this,0,"Avancer");
                    controller.doAction(action2);
                }
                else{
                    controller.setSegmentValue((int) (Math.atan2(deplacement.getDeplacementY(),deplacement.getDeplacementX())/Tortue.ratioDegRad));
                    ActionEvent action = new ActionEvent(this,0,"Angle");
                    controller.doAction(action);

                    controller.setSegmentValue(deplacement.getNorme());
                    ActionEvent action2 = new ActionEvent(this,0,"Avancer");
                    controller.doAction(action2);
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
}
