package raymondh.pe.pkg30.pkg19;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HistogramPane extends Pane {
    int[] arr;
    Rectangle[] bars = new Rectangle[50];
    public HistogramPane(int[] arr) {
        this.arr = arr;
        createPane();
    }
    
    public void createPane() { // Creates the Histogram
        this.setPrefSize(170, 150);
        for (int i = 0; i < arr.length; i++) {
            bars[i] = new Rectangle(i * 3 + 10, 200 - arr[i] * 3, 3, arr[i] * 3);
            bars[i].setStroke(Color.BLACK);
            bars[i].setFill(Color.WHITE);
            
            getChildren().add(bars[i]);
        }
    }
    
    public void highlight(int i) { // Highlights the rectangle that is being sorted
        getChildren().remove(bars[i]);
        bars[i].setFill(Color.BLACK);
        getChildren().add(bars[i]);
    }
}
