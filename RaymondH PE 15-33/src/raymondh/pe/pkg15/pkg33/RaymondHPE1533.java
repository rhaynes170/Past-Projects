
package raymondh.pe.pkg15.pkg33;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Raymond Haynes
 * 1/9/17
 * PE 15-33
 * Creates an animated Bean Machine
 */
public class RaymondHPE1533 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BeanMachinePane machine = new BeanMachinePane();
        
        BorderPane pane = new BorderPane();
        pane.setLeft(machine);
        
        Timeline animation = new Timeline( // Creates the animtaion for the Bean Machine to run
        new KeyFrame(Duration.millis(2750), e -> machine.reset()),
        new KeyFrame(Duration.millis(500), e -> machine.firstDrop()), 
        new KeyFrame(Duration.millis(750), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(1000), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(1250), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(1500), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(1750), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(2000), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(2250), e -> machine.moveThroughSlots()),
        new KeyFrame(Duration.millis(2500), e -> machine.finalDrop()));
        animation.setCycleCount(10);
        animation.play();
            
        Scene scene = new Scene(pane, 200, 175);
        primaryStage.setTitle("Exercise15_33");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
