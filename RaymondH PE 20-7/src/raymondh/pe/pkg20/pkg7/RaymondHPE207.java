package raymondh.pe.pkg20.pkg7;

import javafx.application.Application;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Raymond Haynes
 * 1/31/17
 * PE 20-7
 * Create a HangMan GUI
 */
public class RaymondHPE207 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        String[] words = {"hello", "goodbye", "gaming", "complications", "conspicuous"};
        HangMan pane = new HangMan(words[(int)(Math.random() * 4)]);
        root.setCenter(pane);
        
        
        Scene scene = new Scene(root, 300, 200);
        scene.setOnKeyPressed(e -> { // accepts key input for guessing letters and restarting the game when it ends
            if (!pane.isGameOver) {
                if (Character.isLetter(e.getText().charAt(0))) pane.guessLetter(e.getText().toLowerCase().charAt(0));
            } else {
                if (e.getCode() == ENTER) {
                    pane.restart(words[(int)(Math.random() * 4)]);
                }
            }
        });
        
        primaryStage.setTitle("Exercise20_7");
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
