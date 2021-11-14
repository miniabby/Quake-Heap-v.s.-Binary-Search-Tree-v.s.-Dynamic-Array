/*
 * NAME: Kechen Zhao
 * ID: 957398
 */

import java.util.ArrayList;
import java.util.Random;

// Experiment 3: Time vs Decrease-Key Percentage
public class Experiment3 {

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
            for (int i = 0; i < M; i++) {
                double random = Math.random();
                if (random < percent) {
                    S.add("decrease_key");
                } else {
                    S.add("insert");
                }
            }

            // Quake Heap
            QuakeHeap.gen_insert();
            // generate data for decrease_key
            ArrayList<QuakeHeap.element> targetElem = QuakeHeap.target_element(M);
            ArrayList<Integer> targetKey = QuakeHeap.target_decKey(targetElem);

            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (int i = 0; i < M-1; i++) {
                if (S.get(i).equals("decrease_key")) {
                    QuakeHeap.gen_decrease_key(targetElem.get(i), targetKey.get(i));
                }
                else if (S.get(i).equals("insert")){
                    QuakeHeap.gen_insert();
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion & Decrease-Key for Quake Heap: ");
            System.out.println("Percentage of Decrease-Key: " + percent*100 + "%");
            System.out.println("Total time taken to insert & Decrease-Key: " + totalTime + " ms");
            System.out.println();


            ArrayList<Integer> random1 = new ArrayList<>();
            ArrayList<Integer> random2 = new ArrayList<>();
            ArrayList<Integer> random3 = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < M; i++) {
                int number1 = (int) rand.nextInt((int) Math.pow(10,6))+1;
                int number2 = (int) rand.nextInt((int) Math.pow(10,6))+1;
                int number3 = (int) rand.nextInt(number2);
                random1.add(number1);
                random2.add(number2);
                random3.add(number3);
            }

            // Dynamic Array
            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (int i = 0; i < M; i++) {
                if (S.get(i).equals("insert")) {
                    DynamicArray.insert(random1.get(i));
                }
                else {
                    DynamicArray.decrease_key(random2.get(i), random3.get(i));
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion & Decrease-Key for Dynamic Array: ");
            System.out.println("Percentage of Decrease-Key: " + percent*100 + "%");
            System.out.println("Total time taken to insert & Decrease-Key: " + totalTime + " ms");
            System.out.println();

            // Binary Heap
            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (int i = 0; i < M; i++) {
                if (S.get(i).equals("insert")) {
                    BinaryHeap.insert(random1.get(i));
                }
                else {
                    BinaryHeap.update_key(BinaryHeap.size()-1, random3.get(i));
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Insertion & Decrease-Key for Binary Heap: ");
            System.out.println("Percentage of Decrease-Key: " + percent*100 + "%");
            System.out.println("Total time taken to insert & Decrease-Key: " + totalTime + " ms");
            System.out.println();
        }
    }
}
