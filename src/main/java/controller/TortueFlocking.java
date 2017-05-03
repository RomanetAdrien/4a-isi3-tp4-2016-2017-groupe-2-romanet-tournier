package controller;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by adrie on 03/05/2017.
 */
public class TortueFlocking implements Runnable {
    Controller controller;

    public TortueFlocking(Controller controller){
        this.controller = controller;
    }
    @Override
    public void run() {
        int nbTortue = 6;
        for (int i=0; i<nbTortue;i++){
            ActionEvent action = new ActionEvent(this,0,"Nouvelle");
            controller.doAction(action);
        }
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i=0;i<nbTortue;i++){
                controller.setTortueCourante(i);


            }

        }
    }
}

