package raymondh.pe.pkg20.pkg13;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Raymond Haynes
 * 2/1/17
 * PE 20-13
 * Create a 24 point card game
 */
public class RaymondHPE2013 extends Application {
    private HBox cardBox = new HBox(5); // Creates a row of card Images
    private ArrayList<Integer> cardValue = new ArrayList<>();
    private ArrayList<Integer> cards = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        
        
        for (int i = 1; i < 52; i++) {
            cards.add(i);
        }
        
        getCards();
        
        HBox top = new HBox(5); // Sets the top row
        top.setAlignment(Pos.CENTER_RIGHT);
        
        Text verify = new Text("");
        Button shuffle = new Button("Shuffle");
        
        top.getChildren().addAll(verify, shuffle);
        
        shuffle.setOnAction(e -> { // Shuffles the cards
            cardBox.getChildren().clear();
            getCards();           
        });
        
        HBox bottom = new HBox(5); // Sets the bottom row
        bottom.setAlignment(Pos.CENTER_RIGHT);
        Text prompt = new Text("Enter an expression: ");
        TextField expression = new TextField();
        expression.setPrefWidth(250);
        Button btnVerify = new Button("Verify");
        
        bottom.getChildren().addAll(prompt, expression, btnVerify);
        
        btnVerify.setOnAction(e -> { // Checks and sees if the correct numbers were used and if the result is correct
            try {
                if (evaluateExpression(expression.getText()) == 24) {
                    verify.setText("Correct");
                } else if (evaluateExpression(expression.getText()) != -999){
                    verify.setText("Incorrect");
                } else {
                    verify.setText("The numbers in the expression don't\nmatch the numbers in the set");
                }
            } catch (Exception ex) {
                verify.setText("Invalid");
            }
        });
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(cardBox);
        root.setBottom(bottom);
        
        Scene scene = new Scene(root, 685, 300);
        primaryStage.setTitle("Exercise20_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void getCards() { // Prints the images of the cards and determines the visible cards' values
        java.util.Collections.shuffle(cards);
        
        if (!cardValue.isEmpty()) {
            cardValue.clear();
        }
        
        for (int i = 0; i < 4; i++) {
            File file = new File("image\\" + cards.get(i) + ".jpg");
            cardBox.getChildren().add(new ImageView(new Image(file.toURI().toString())));
        }
        
        for (int i = 0; i < 4; i++) {
            if (cards.get(i) >= 1 && cards.get(i) <= 4) {
                cardValue.add(1);
            } else if (cards.get(i) >= 5 && cards.get(i) <= 8) {
                cardValue.add(8);
            } else if (cards.get(i) >= 9 && cards.get(i) <= 12) {
                cardValue.add(5);
            } else if (cards.get(i) >= 13 && cards.get(i) <= 16) {
                cardValue.add(4);
            } else if (cards.get(i) >= 17 && cards.get(i) <= 20) {
                cardValue.add(11);
            } else if (cards.get(i) >= 21 && cards.get(i) <= 24) {
                cardValue.add(13);
            } else if (cards.get(i) >= 25 && cards.get(i) <= 28) {
                cardValue.add(9);
            } else if (cards.get(i) >= 29 && cards.get(i) <= 32) {
                cardValue.add(12);
            } else if (cards.get(i) >= 33 && cards.get(i) <= 36) {
                cardValue.add(7);
            } else if (cards.get(i) >= 37 && cards.get(i) <= 40) {
                cardValue.add(6);
            } else if (cards.get(i) >= 41 && cards.get(i) <= 44) {
                cardValue.add(10);
            } else if (cards.get(i) >= 45 && cards.get(i) <= 48) {
                cardValue.add(3);
            } else {
                cardValue.add(2);
            }
            
            
        }
    }
    
    public int evaluateExpression(String expression) { // Evaluates the expression and returns the result
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        
        expression = insertBlanks(expression);
        
        String[] tokens = expression.split(" ");
        
        for (String token: tokens) {
            if (token.length() == 0) {
                continue;
            } else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                while (!operatorStack.isEmpty() && 
                      (operatorStack.peek() == '+' ||
                       operatorStack.peek() == '-' ||
                       operatorStack.peek() == '*' ||
                       operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }
                
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                while (!operatorStack.isEmpty() && 
                      (operatorStack.peek() == '*' ||
                       operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }
                
                operatorStack.push(token.charAt(0));
            } else if (token.trim().charAt(0) == '(') {
                operatorStack.push('(');
            } else if (token.trim().charAt(0) == ')') {
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }
                
                operatorStack.pop();
            } else {
                operandStack.push(new Integer(token));
                if (cardValue.contains(operandStack.peek())) {
                    cardValue.remove(operandStack.peek());
                } else {
                    return -999;
                }
            }
        }
        
        while(!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }
        
        return operandStack.pop();
    }
    
    public void processAnOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) { // Processes the operators to add values
        char op = operatorStack.pop();
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        
        if (op == '+') {
            operandStack.push(op2 + op1);
        } else if (op == '-') {
            operandStack.push(op2 - op1);
        } else if (op == '*') {
            operandStack.push(op2 * op1);
        } else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }
    
    public String insertBlanks(String expression) { // Creates spaces for the expression if they are not there
        String result = "";
        
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(' || expression.charAt(i) == ')' || 
                expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                expression.charAt(i) == '*' || expression.charAt(i) == '/')
                result += " " + expression.charAt(i) + " ";
            else result += expression.charAt(i);
        }
        
        
        return result;
    }
}