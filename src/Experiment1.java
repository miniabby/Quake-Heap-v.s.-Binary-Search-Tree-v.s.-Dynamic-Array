/*
 * NAME: Kechen Zhao
 * ID: 957398
 */

import java.util.ArrayList;
import java.util.Random;

// Experiment 1: Time vs Number of Insertions
public class Experiment1 {

    public static void main(String[] args) {

        QuakeHeap QuakeHeap = new QuakeHeap();
        DynamicArray DynamicArray = new DynamicArray();
        BinaryHeap BinaryHeap = new BinaryHeap();
        int M = (int) Math.pow(10,6);

        long startTime = 0, endTime = 0, totalTime = 0;
        ArrayList<Integer> lengths = new ArrayList<Integer>();
        lengths.add((int) (0.1*M));
        lengths.add((int) (0.2*M));
        lengths.add((int) (0.5*M));
        lengths.add((int) (0.8*M));
        lengths.add(M);

        // Quake Heap insertion
        for (Integer len : lengths) {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                QuakeHeap.gen_insert();
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion for Quake Heap: ");
            System.out.println("Number of data to insert: " + len);
            System.out.println("Total time taken to insert: " + totalTime + " ms");
            System.out.println();
        }


        // Dynamic Array insertion
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        for (Integer len : lengths) {
            ArrayList<Integer> random1 = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < len; i++) {
                int number1 = (int) rand.nextInt((int) Math.pow(10,6))+1;
                random1.add(number1);
            }
            startTime = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                DynamicArray.insert((random1.get(i)));
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion for Dynamic Array: ");
            System.out.println("Number of data to insert: " + len);
            System.out.println("Total time taken to insert: " + totalTime + " ms");
            System.out.println();
        }

        // Binary Heap insertion
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        for (Integer len : lengths) {
            ArrayList<Integer> random1 = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < len; i++) {
                int number1 = (int) rand.nextInt((int) Math.pow(10,6))+1;
                random1.add(number1);
            }
            startTime = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                BinaryHeap.insert((random1.get(i)));
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion for Binary Heap: ");
            System.out.println("Number of data to insert: " + len);
            System.out.println("Total time taken to insert: " + totalTime + " ms");
            System.out.println();
        }


    }

}
