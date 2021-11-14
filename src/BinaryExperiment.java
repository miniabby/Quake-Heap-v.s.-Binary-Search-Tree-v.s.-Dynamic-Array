import java.util.ArrayList;

public class BinaryExperiment {

    public static void main(String[] args) {

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
            //System.out.println("Percentage of delete-min: " + percent*100 + "%");
            int insert = 0;
            int delete = 0;
            for (int i = 0; i < M; i++) {
                if (insert == delete) {
                    S.add("insert");
                    insert = insert + 1;
                } else {
                    double random = Math.random();
                    if (random < percent) {
                        S.add("delete");
                        delete = delete + 1;
                    } else {
                        S.add("insert");
                        insert = insert + 1;
                    }
                }
            }
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
