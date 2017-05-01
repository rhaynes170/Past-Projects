package raymondh.pe.pkg30.pkg19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Raymond Haynes
 * 2/3/17
 * PE 30-19
 * Creates an animation for a selection sort, an insertion sort, and a bubble sort for an array and displays the values of the array in a histogram
 */
public class RaymondHPE3019 extends Application {
    public HistogramPane select, insert, bubble;
    int[] arrI = new int[50], arrI2 = new int[50], arrI3 = new int[50];
    boolean needNextPass = true;
    
    @Override
    public void start(Stage primaryStage) {
        ArrayList<Integer> arr = new ArrayList(), arr2 = new ArrayList(), arr3 = new ArrayList(); // Creates the random arrays
        
        for (int i = 1; i < 51; i++) {
            arr.add(i);
            arr2.add(i);
            arr3.add(i);
        }
        
        Collections.shuffle(arr);
        Collections.shuffle(arr2);
        Collections.shuffle(arr3);
        
        for (int i = 0; i < arr.size(); i++) {
            arrI[i] = arr.get(i);
            arrI2[i] = arr2.get(i);
            arrI3[i] = arr3.get(i);
        }
        
        select = new HistogramPane(arrI); // Creates the histograms
        insert = new HistogramPane(arrI2);
        bubble = new HistogramPane(arrI3);
        
        BorderPane root = new BorderPane();
        root.setLeft(select);
        root.setCenter(insert);
        root.setRight(bubble);
        
        ExecutorService executor = Executors.newCachedThreadPool(); // Creates the threads to perform a specific task
        
        executor.execute(new selectTask());
        executor.execute(new insertTask());
        executor.execute(new bubbleTask());
        
        Text selectSort = new Text("Selection Sort"); // Creates the labels of each graph
        selectSort.setFont(Font.font("Times", 16));
        selectSort.setLayoutX(50);
        selectSort.setLayoutY(25);
        
        Text insertSort = new Text ("Insertion Sort");
        insertSort.setFont(Font.font("Times", 16));
        insertSort.setLayoutX(220);
        insertSort.setLayoutY(25);
        
        Text bubbleSort = new Text("Bubble Sort");
        bubbleSort.setFont(Font.font("Times", 16));
        bubbleSort.setLayoutX(390);
        bubbleSort.setLayoutY(25);
        
        root.getChildren().addAll(selectSort, insertSort, bubbleSort);
        
        Scene scene = new Scene(root, 510, 210);
        
        
        primaryStage.setTitle("Exercise30_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void insertionSort(int[] arr, int i) { // The interior of the Insertion Sort algorithm
        int currentElement = arr[i];
        int k;
        for (k = i - 1; k >= 0 && arr[k] > currentElement; k--) {
            arr[k + 1] = arr[k];
        }

        arr[k + 1] = currentElement;
    }
    
    public void bubbleSort(int[] arr, int i) { // The interior of the Bubble Sort algorithm
        for (int k = 0; k < arr.length - i; k++) {
            if (arr[k] > arr[k + 1]) {
                int temp = arr[k];
                arr[k] = arr[k + 1];
                arr[k + 1] = temp;

                needNextPass = true;
            }
        }
    }
    
    public void selectionSort(int[] arr, int i) { // The interior of the Selection Sort algorithm
        
        int currentMin = arr[i];
        int currentMinIndex = i;

        for (int j = i + 1; j < arr.length; j++) {
            if (currentMin > arr[j]) {
                currentMin = arr[j];
                currentMinIndex = j;
            }
        }

        if (currentMinIndex != i) {
            arr[currentMinIndex] = arr[i];
            arr[i] = currentMin;
        }
    }
    
    class selectTask implements Runnable { // The thread to run the Selection Sort
        @Override
        public void run() {
            try {
                for (int i = 0; i < arrI.length; i++) {
                    int bar = i;
                    
                    selectionSort(arrI, i);
                    
                    Platform.runLater(() -> {
                            select.arr = arrI;
                            select.getChildren().clear();
                            select.createPane();
                            select.highlight(bar);
                    });
                    
                    Thread.sleep(500);
                }
            } catch (Exception e) {
            }
        }
    }
    
    class insertTask implements Runnable { // The thread to run the Insertion Sort
        @Override
        public void run() {
            try {
                for (int i = 0; i < arrI2.length; i++) {
                    int bar = i;
                    
                    insertionSort(arrI2, i);
                    
                    Platform.runLater(() -> {
                            insert.arr = arrI2;
                            insert.getChildren().clear();
                            insert.createPane();
                            insert.highlight(bar);
                    });
                    
                    Thread.sleep(500);
                }
            } catch (Exception e) {
            }
        }
    }
    
    class bubbleTask implements Runnable { // The thread to run the Bubble Sort
        @Override
        public void run() {
            try {
                for (int i = 1; i < arrI3.length && needNextPass; i++) {
                    needNextPass = false;
                    
                    int bar = i;
 
                    bubbleSort(arrI3, i);

                    Platform.runLater(() -> {
                            bubble.arr = arrI3;
                            bubble.getChildren().clear();
                            bubble.createPane();
                            bubble.highlight(bar);
                    });
                    
                    Thread.sleep(500);
                }
            } catch (Exception e) {
            }
        }
    }
    
}