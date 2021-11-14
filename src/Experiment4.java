/*
 * NAME: Kechen Zhao
 * ID: 957398
 */

import java.util.ArrayList;
import java.util.Random;

// Experiment 4: Time vs Mixed Operation Sequence
public class Experiment4 {

    public static void main(String[] args) {

        QuakeHeap QuakeHeap = new QuakeHeap();
        DynamicArray DynamicArray = new DynamicArray();
        BinaryHeap BinaryHeap = new BinaryHeap();
        int M = (int) Math.pow(10,6);
        long startTime = 0, endTime = 0, totalTime = 0;

        ArrayList<String> S = new ArrayList<>();
        ArrayList<Integer> length = new ArrayList<Integer>();
        length.add((int) (0.1*M));
        length.add((int) (0.2*M));
        length.add((int) (0.5*M));
        length.add((int) (0.8*M));
        length.add(M);

        for (Integer len : length) {
            int insert = 0;
            int delete = 0;
            for (int i = 0; i < len; i++) {
                if (insert == delete) {
                    S.add("insert");
                    insert = insert + 1;
                } else {
                    double random = Math.random();
                    if (random < 0.05) {
                        S.add("delete-min");
                        delete = delete + 1;
                    } else if ((0.05 <= random) & (random < 0.1)) {
                        S.add("decrease-key");
                    } else {
                        S.add("insert");
                        insert = insert + 1;
                    }
                }
            }

            QuakeHeap.gen_insert();
            // generate data for decrease_key
            ArrayList<QuakeHeap.element> targetElem = QuakeHeap.target_element(len);
            ArrayList<Integer> targetKey = QuakeHeap.target_decKey(targetElem);

            // Quake Heap
            startTime = System.currentTimeMillis();
            for (int i = 0; i < len-1; i++) {
                if (S.get(i).equals("insert")) {
                    QuakeHeap.gen_insert();;
                }
                else if (S.get(i).equals("delete-min")) {
                    QuakeHeap.gen_delete_min();
                } else {
                    QuakeHeap.gen_decrease_key(targetElem.get(i), targetKey.get(i));
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Operations for Quake Heap: ");
            System.out.println("Number of operations " + len);
            System.out.println("Total time taken: " + totalTime + " ms");
            System.out.println();

            ArrayList<Integer> random1 = new ArrayList<>();
            ArrayList<Integer> random2 = new ArrayList<>();
            ArrayList<Integer> random3 = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < len; i++) {
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
            for (int i = 0; i < len; i++) {
                if (S.get(i).equals("insert")) {
                    DynamicArray.insert(random1.get(i));
                } else if (S.get(i).equals("delete-min")) {
                    DynamicArray.delete_min();
                } else {
                    DynamicArray.decrease_key(BinaryHeap.size() - 1, random3.get(i));
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Operations for Dynamic Array: ");
            System.out.println("Number of operations " + len);
            System.out.println("Total time taken to insert & delete: " + totalTime + " ms");
            System.out.println();

            // Binary Heap
            startTime = 0;
            endTime = 0;
            totalTime = 0;
            startTime = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                if (S.get(i).equals("insert")) {
                    BinaryHeap.insert(random1.get(i));
                } else if (S.get(i).equals("delete-min")) {
                    BinaryHeap.delete_min();
                } else {
                    BinaryHeap.update_key(BinaryHeap.size()-1, random1.get(3));
                }
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime-startTime;
            System.out.println("Operations for Binary Heap: ");
            System.out.println("Number of operations " + len);
            System.out.println("Total time taken to insert & delete: " + totalTime + " ms");
            System.out.println();
        }
    }

}
