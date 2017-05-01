package raymondh.pe.pkg18.pkg35;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


public class HTreePane extends Pane {
    private int order = 0;
    
    public void setOrder(int order) { // Sets the order for recursion
        this.order = order;
        paint();
    }
    
    public HTreePane() {}
    
    protected void paint() { // Clears the pane before creating a new H-tree
        getChildren().clear();
        displayHTree(order, getWidth() / 2, getHeight() / 2, 75);
    }
    
    private void displayHTree(int order, double centerX, double centerY, int length) { // Creates the H-tree
        if (order >= 0) {
            getChildren().addAll(new Line(centerX - length / 2, centerY - length / 2, centerX - length / 2, centerY + length / 2),
            new Line(centerX + length / 2, centerY - length / 2, centerX + length / 2, centerY + length / 2),
            new Line(centerX - length / 2, centerY, centerX + length / 2, centerY));
            
            displayHTree(order - 1, centerX - length / 2, centerY - length / 2, length / 2);
            displayHTree(order - 1, centerX + length / 2, centerY - length / 2, length / 2);
            displayHTree(order - 1, centerX - length / 2, centerY + length / 2, length / 2);
            displayHTree(order - 1, centerX + length / 2, centerY + length / 2, length / 2);
        }
    }
}
