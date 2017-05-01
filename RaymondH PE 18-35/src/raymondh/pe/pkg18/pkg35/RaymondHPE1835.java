package raymondh.pe.pkg18.pkg35;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Raymond Haynes
 * 1/20/17
 * PE 18-35
 * Create an H-tree through recursion based on user input
 */
public class RaymondHPE1835 extends Application {
    
    @Override
    public void start(Stage primaryStage) { // Accepts user order input and calls the HTreePane to create the H-tree
        HTreePane pane = new HTreePane();
        TextField tfOrder = new TextField();
        tfOrder.setOnAction( e -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);
        
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hbox.setAlignment(Pos.CENTER);
        
        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(hbox);
        
        Scene scene = new Scene(root, 200, 210);
        primaryStage.setTitle("Exercise18_35");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
