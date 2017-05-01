
package raymondh.pe.pkg15.pkg33;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class BeanMachinePane extends Pane{
    private Circle ball = new Circle (100, 25, 2.5), circleMark; // Adds circles to represent the balls in the Bean Machine
    private static int[] slotLanded = new int[8]; // Creates an array that allows the balls to stack on top of each other
    
    public BeanMachinePane() { // Creates the Bean Machine and creates the ball
        ball.setFill(Color.GRAY);
        getChildren().add(ball);
        
        Line line1 = new Line(90, 30, 90, 50);
        Line line2 = new Line(110, 30, 110, 50);
        Line line3 = new Line(90, 50, 60, 125);
        Line line4 = new Line(110, 50, 140, 125);
        Line line5 = new Line(60, 150, 140, 150);
        
        getChildren().addAll(line1, line2, line3, line4, line5);
        
        for (int i = 60; i <= 140; i += 10) {
            getChildren().add(new Line(i, 125, i, 150));
        }
        
        int row = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7 - i; j++) {
                getChildren().add(new Circle((70 + 80 * j / 8 + (i * 5)), 125 - 90 * row / 8, 2.5));
            }
            row++;
        }
    }
    
    public void firstDrop () { // Drops the ball
        ball.setCenterX(100);
        ball.setCenterY(50);
    }
    
    public void moveThroughSlots() { // Moves the ball throught the pegs of the Bean Machine
        double x = ball.getCenterX(), y = ball.getCenterY();
        if ((int) (Math.random() * 2) == 1) {
            x -= 5;
            y += 10;
            ball.setCenterX(x);
            ball.setCenterY(y);
        } else {
            x += 5;
            y += 10;
            ball.setCenterX(x);
            ball.setCenterY(y);
        }
    }
    
    public void finalDrop() { // Drops the ball into each slot
        if (ball.getCenterX() >= 60 && ball.getCenterX() < 70) {
            ball.setCenterX(65);
            ball.setCenterY(148 - 5 * slotLanded[0]);
            slotLanded[0]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else if (ball.getCenterX() >= 70 && ball.getCenterX() < 80) {
            ball.setCenterX(75);
            ball.setCenterY(148 - 5 * slotLanded[1]);
            slotLanded[1]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else if (ball.getCenterX() >= 80 && ball.getCenterX() < 90) {
            ball.setCenterX(85);
            ball.setCenterY(148 - 5 * slotLanded[2]);
            slotLanded[2]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else if (ball.getCenterX() >= 90 && ball.getCenterX() < 100) {
            ball.setCenterX(95);
            ball.setCenterY(148 - 5 * slotLanded[3]);
            slotLanded[3]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else if (ball.getCenterX() >= 100 && ball.getCenterX() < 110) {
            ball.setCenterX(105);
            ball.setCenterY(148 - 5 * slotLanded[4]);
            slotLanded[4]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else if (ball.getCenterX() >= 110 && ball.getCenterX() < 120) {
            ball.setCenterX(115);
            ball.setCenterY(148 - 5 * slotLanded[5]);
            slotLanded[5]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else if (ball.getCenterX() >= 120 && ball.getCenterX() < 130) {
            ball.setCenterX(125);
            ball.setCenterY(148 - 5 * slotLanded[6]);
            slotLanded[6]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        } else {
            ball.setCenterX(135);
            ball.setCenterY(148 - 5 * slotLanded[7]);
            slotLanded[7]++;
            circleMark = new Circle(ball.getCenterX(), ball.getCenterY(), 2.5);
            circleMark.setFill(Color.GRAY);
            getChildren().add(circleMark);
        }
    }
    
    public void reset() { // Resets the ball back to its original position
        ball.setCenterX(100);
        ball.setCenterY(25);
    }
}
