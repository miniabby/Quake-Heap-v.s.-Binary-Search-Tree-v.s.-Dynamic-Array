/*
 * NAME: Kechen Zhao
 * ID: 957398
 */

import java.util.ArrayList;

// Experiment 2: Time vs Delete-Min Percentage
public class Experiment2 {

    public static void main(String[] args) {

        QuakeHeap QuakeHeap = new QuakeHeap();
        DynamicArray DynamicArray = new DynamicArray();
        BinaryHeap BinaryHeap = new BinaryHeap();
        int M = (int) Math.pow(10,6);
        long startTime = 0, endTime = 0, totalTime = 0;


        ArrayList<String> S = new ArrayList<>();
        ArrayList<Double> percentage = new ArrayList<Double>();
        percentage.add(0.1/100);
        percentage.add(0.5/100);
        percentage.add(0.01);
        percentage.add(0.05);
        percentage.add(0.1);
        for (Double percent : percentage) {
            int insert = 0;
            int delete = 0;
            for (int i = 0; i < M; i++) {
                if (insert == delete) {
                    S.add("insert");
                    insert = insert + 1;
                }
                else {
                    double random = Math.random();
                    if (random < percent) {
                        S.add("delete");
                        delete = delete + 1;
                    }
                    else {
                        S.add("insert");
                        insert = insert + 1;
                    }
                }
            }

            // Quake Heap
            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (String operation : S) {
                //System.out.println(operation);
                if (operation.equals("delete")) {
                    QuakeHeap.gen_delete_min();
                }
                else {
                    QuakeHeap.gen_insert();
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion & Delete-Min for Quake Heap: ");
            System.out.println("Percentage of delete-min: " + percent*100 + "%");
            System.out.println("Total time taken to insert & delete: " + totalTime + " ms");
            System.out.println();

            // Dynamic Array
            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (String operation : S) {
                if (operation.equals("insert")) {
                    DynamicArray.insert((int) (Math.random()*100));
                }
                else {
                    DynamicArray.delete_min();
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion & Delete-Min for Dynamic Array: ");
            System.out.println("Percentage of delete-min: " + percent*100 + "%");
            System.out.println("Total time taken to insert & delete: " + totalTime + " ms");
            System.out.println();

            // Binary Heap
            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (String operation : S) {
                if (operation.equals("insert")) {
                    BinaryHeap.insert((int) (Math.random()*100));
                }
                else {
                    BinaryHeap.delete_min();
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion & Delete-Min for Binary Heap: ");
            System.out.println("Percentage of delete-min: " + percent*100 + "%");
            System.out.println("Total time taken to insert & delete: " + totalTime + " ms");
            System.out.println();
        }
    }
}
