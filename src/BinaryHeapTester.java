import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryHeapTester {

    private BinaryHeap heap = new BinaryHeap();

    @Before
    public void setup() {
        heap.insert(10);
        heap.insert(6);
        heap.insert(5);

    }

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap();
        for (int i = 0; i < 15; i++) {
            heap.insert((int) (Math.random() * 100));
        }
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get_elem(i));
        }
        /*System.out.println("\n");
        heap.delete_min();
        heap.delete_min();
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get_elem(i));
        }*/
    }


}
