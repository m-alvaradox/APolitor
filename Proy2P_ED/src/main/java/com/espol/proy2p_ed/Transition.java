package com.espol.proy2p_ed;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

    public class Transition {
       
        
        public void Transitionround(Circle c1, Circle c2, Circle c3, Circle c4, Circle c5, Circle c6){

            TranslateTransition traducirc11 = new TranslateTransition();
            traducirc11.setNode(c1);
            traducirc11.setDuration(Duration.millis(2000));
            traducirc11.setByY(125);

            TranslateTransition traducirc12 = new TranslateTransition();
            traducirc12.setNode(c1);
            traducirc12.setDuration(Duration.millis(5000)); 
            traducirc12.setByY(-440);

            TranslateTransition traducirc13 = new TranslateTransition();
            traducirc13.setNode(c1);
            traducirc13.setDuration(Duration.millis(5000)); 
            traducirc13.setByY(315);

            SequentialTransition sequence = new SequentialTransition(traducirc11, traducirc12, traducirc13);
            sequence.setCycleCount(SequentialTransition.INDEFINITE);

            sequence.play();


            TranslateTransition traducirc21 = new TranslateTransition();
            traducirc21.setNode(c2);
            traducirc21.setDuration(Duration.millis(5000)); 
            traducirc21.setByY(416);

            TranslateTransition traducirc22 = new TranslateTransition();
            traducirc22.setNode(c2);
            traducirc22.setDuration(Duration.millis(5000)); 
            traducirc22.setByY(-440);

            TranslateTransition traducirc23 = new TranslateTransition();
            traducirc23.setNode(c2);
            traducirc23.setDuration(Duration.millis(2000)); 
            traducirc23.setByY(25);

            SequentialTransition sequence2 = new SequentialTransition(traducirc21, traducirc22, traducirc23);
            sequence2.setCycleCount(SequentialTransition.INDEFINITE);

            sequence2.play();


            TranslateTransition traducirc31 = new TranslateTransition();
            traducirc31.setNode(c3);
            traducirc31.setDuration(Duration.millis(3000)); 
            traducirc31.setByY(273);

            TranslateTransition traducirc32 = new TranslateTransition();
            traducirc32.setNode(c3);
            traducirc32.setDuration(Duration.millis(5000)); 
            traducirc32.setByY(-440);

            TranslateTransition traducirc33 = new TranslateTransition();
            traducirc33.setNode(c3);
            traducirc33.setDuration(Duration.millis(3000));
            traducirc33.setByY(171);

            SequentialTransition sequence3 = new SequentialTransition(traducirc31, traducirc32, traducirc33);
            sequence3.setCycleCount(SequentialTransition.INDEFINITE); 

            sequence3.play();


            TranslateTransition traducirc41 = new TranslateTransition();
            traducirc41.setNode(c4);
            traducirc41.setDuration(Duration.millis(5000)); 
            traducirc41.setByY(372);

            TranslateTransition traducirc42 = new TranslateTransition();
            traducirc42.setNode(c4);
            traducirc42.setDuration(Duration.millis(5000)); 
            traducirc42.setByY(-440);

            TranslateTransition traducirc43 = new TranslateTransition();
            traducirc43.setNode(c4);
            traducirc43.setDuration(Duration.millis(2000)); 
            traducirc43.setByY(71);

            SequentialTransition sequence4 = new SequentialTransition(traducirc41, traducirc42, traducirc43);
            sequence4.setCycleCount(SequentialTransition.INDEFINITE);

            sequence4.play();


            TranslateTransition traducirc51 = new TranslateTransition();
            traducirc51.setNode(c5);
            traducirc51.setDuration(Duration.millis(2000)); 
            traducirc51.setByY(205);

            TranslateTransition traducirc52 = new TranslateTransition();
            traducirc52.setNode(c5);
            traducirc52.setDuration(Duration.millis(5000)); 
            traducirc52.setByY(-440);

            TranslateTransition traducirc53 = new TranslateTransition();
            traducirc53.setNode(c5);
            traducirc53.setDuration(Duration.millis(3000)); 
            traducirc53.setByY(235);

            SequentialTransition sequence5 = new SequentialTransition(traducirc51, traducirc52, traducirc53);
            sequence5.setCycleCount(SequentialTransition.INDEFINITE);

            sequence5.play();


            TranslateTransition traducirc61 = new TranslateTransition();
            traducirc61.setNode(c6);
            traducirc61.setDuration(Duration.millis(2000)); 
            traducirc61.setByY(123);

            TranslateTransition traducirc62 = new TranslateTransition();
            traducirc62.setNode(c6);
            traducirc62.setDuration(Duration.millis(5000)); 
            traducirc62.setByY(-440);

            TranslateTransition traducirc63 = new TranslateTransition();
            traducirc63.setNode(c6);
            traducirc63.setDuration(Duration.millis(4000)); 
            traducirc63.setByY(325);

            SequentialTransition sequence6 = new SequentialTransition(traducirc61, traducirc62, traducirc63);
            sequence6.setCycleCount(SequentialTransition.INDEFINITE);

            sequence6.play();
           

            FadeTransition desvanecimiento = new FadeTransition();
            desvanecimiento.setNode(c1);
            desvanecimiento.setDuration(Duration.millis(1000));
            desvanecimiento.setCycleCount(TranslateTransition.INDEFINITE);
            desvanecimiento.setInterpolator(Interpolator.LINEAR);
            desvanecimiento.setFromValue(0);
            desvanecimiento.setToValue(1);  
    }
       
        
        
        
    public void rotateLogo(ImageView image){
        RotateTransition rotar = new RotateTransition();
        rotar.setNode(image);
        rotar.setDuration(Duration  .millis(4000));
        rotar.setCycleCount(TranslateTransition.INDEFINITE);
        rotar.setInterpolator(Interpolator.LINEAR);
        rotar.setByAngle(360);
        rotar.setAxis(Rotate.Y_AXIS);
        rotar.play();
   }
}
