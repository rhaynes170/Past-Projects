package raymondh.pe.pkg20.pkg7;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author programming
 */
public class HangMan extends Pane{
    private String word = "";
    private Text blankWord = new Text(""), prompt = new Text("Guess a word: "), missedLetters = new Text(""), bottomText = new Text("Missed letters: ");
    private static ManPane man = new ManPane();
    private int wrongCount = 0;
    private ArrayList<Character> letters = new ArrayList(), missedLetter = new ArrayList(), chars = new ArrayList();
    boolean isGameOver = false;
    
    public HangMan(String word) {
        Line line1 = new Line(55, 25, 55, 150);
        Line line2 = new Line(55, 25, 145, 25);
        
        Arc arc1 = new Arc(55, 170, 40, 20, 0, 180);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.WHITE);
        arc1.setStroke(Color.BLACK);
        
        prompt.setX(145);
        prompt.setY(125);
        
        this.word = word;
        
        setLists();
        
        blankWord.setX(222.5);
        blankWord.setY(125);
        
        missedLetters.setX(222.5);
        missedLetters.setY(145);
        
        bottomText.setX(145);
        bottomText.setY(145);
        
        man.setLayoutX(105);
        man.setLayoutY(25);
        
        getChildren().addAll(line1, line2, arc1, prompt, blankWord, bottomText, missedLetters, man);
        
        
        
        blankWord.requestFocus();
    }
    
    public void setLists() { // Sets the ArrayLists used for the letter guessing
        String placeholder = " ";
        
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
            chars.add('*');
            placeholder += '*';
        }
        
        blankWord.setText(placeholder);
    }
    public void guessLetter(char letter) { // Checks the letter guessed and sees if it is correct or not
        if (letters.contains(letter) && !letters.isEmpty()) {
            
            String placeholder = "";
            
            for (int i = 1; i < chars.size() + 1; i++) {
                if (word.indexOf(letter, i - 1) >= 0) {
                    chars.set(word.indexOf(letter, i - 1), letter);
                }
                placeholder += chars.get(i - 1);
            }
            
            if (!chars.contains('*')) gameOver();
            
            blankWord.setText(placeholder);
        } else {
            String placeholder = "";
            if (!missedLetter.contains(letter)) {
                wrongCount++;
                
                for (int i = 0; i < missedLetter.size() && !missedLetter.isEmpty(); i++) placeholder += missedLetter.get(i);
                placeholder += letter;
                missedLetter.add(letter);
                
                missedLetters.setText(placeholder);
                
                addPart(wrongCount);
            }
        }
        
        if (letters.isEmpty()) {
            gameOver();
        }
    }
    
    public void addPart(int count) { // Adds the parts of the HangMan
        switch(count) {
            case 1:
                man.addRope();
                break;
            case 2:
                man.addHead();
                break;
            case 3:
                man.addLeftArm();
                break;
            case 4:
                man.addRightArm();
                break;
            case 5:
                man.addBody();
                break;
            case 6:
                man.addLeftLeg();
                break;
            default:
                man.addRightLeg();
                man.swingMan();
                gameOver();
                break;
        }
        
        
    }
    
    public void gameOver() { // Creates a game over screen
        getChildren().remove(missedLetters);
        prompt.setText("The word is: ");
        blankWord.setText(word);
        bottomText.setX(100);
        bottomText.setText("To continue the game, press ENTER");
        isGameOver = true;
    }
    
    public void restart(String word) { // Restarts the game
        man.clear();
        
        prompt.setText("Guess a word: ");
        
        blankWord.setText("");
        
        bottomText.setX(145);
        bottomText.setText("Missed Letters: ");
        
        missedLetters.setText(" ");
        getChildren().add(missedLetters);
        
        missedLetter.clear();
        
        chars.clear();
        
        letters.clear();
        
        wrongCount = 0;
        
        this.word = word;
        
        setLists();
        
        isGameOver = false;
    }
    static class ManPane extends Pane {
        private Line rope = new Line(25, 0, 25, 10);
        private Circle head = new Circle(25, 20, 10);
        private Line leftArm = new Line(20, 30, 0, 55), rightArm = new Line(30, 30, 50, 55);
        private Line body = new Line(25, 30, 25, 65);
        private Line leftLeg = new Line(25, 65, 10, 82.5), rightLeg = new Line(25, 65, 40, 82.5);
        
        public ManPane() {
            setWidth(super.getWidth());
            setHeight(super.getHeight());
        }
        
        public void addRope() {
            getChildren().add(rope);
        }
        
        public void addHead() {
            head.setFill(Color.WHITE);
            head.setStroke(Color.BLACK);
            getChildren().add(head);
        }
        
        public void addLeftArm() {
            getChildren().add(leftArm);
        }
        
        public void addRightArm() {
            getChildren().add(rightArm);
        }
        
        public void addBody() {
            getChildren().add(body);
        }
        
        public void addLeftLeg() {
            getChildren().add(leftLeg);
        }
        
        public void addRightLeg() {
            getChildren().add(rightLeg);
        }
        
        public void clear() {
            getChildren().clear();
        }
        
        public void swingMan() {
            RotateTransition animation = new RotateTransition(Duration.millis(500), this);
            animation.setFromAngle(-10);
            animation.setToAngle(10);
            animation.setAutoReverse(true);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }
    }
}
