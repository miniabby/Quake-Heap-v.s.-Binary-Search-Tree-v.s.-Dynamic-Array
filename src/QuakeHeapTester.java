import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuakeHeapTester {

    private QuakeHeap heap = new QuakeHeap();

    @Before
    public void setup() {
        heap = new QuakeHeap();
        heap.gen_insert();
        heap.gen_insert();
        heap.gen_insert();
        heap.gen_insert();
        for (int i = 0; i < heap.elemList.size(); i++){
            System.out.println(heap.elemList.get(i).get_key());
        }
        System.out.println('\n');
        //heap.merge_tree(heap.rootList);
        heap.gen_delete_min();
        heap.gen_delete_min();
        for (int i = 0; i < heap.rootList.size(); i++) {
            System.out.println(heap.rootList.get(i).get_key());
        }
        System.out.println('\n');
        for (int i = 0; i < heap.elemList.size(); i++){
            System.out.println(heap.elemList.get(i).get_key());
        }
        /*System.out.println('\n');
        heap.gen_delete_min();
        //heap.gen_decrease_key();
        for (int i = 0; i < heap.elemList.size(); i++){
            System.out.println(heap.elemList.get(i).get_key());
        }*/
        //heap.gen_delete_min();
        //heap.gen_decrease_key();
    }

    @Test
    public void testInsert() {
        //assertEquals(6, heap.elemList.size());
        //assertEquals(2, heap.rootList.size());
        //assertEquals(3, heap.rootList.get(0).get_height());
        //assertEquals(1, heap.rootList.stream().filter(r->r.get_height()==3).count());
    }

    // test for gen_element and tie-breaking
    /*public static void main(String[] args) {
        QuakeHeap heap = new QuakeHeap();
        for (int i = 0; i < 30; i++) {
            heap.gen_element();
        }
        System.out.println(heap.elemList.size());
        for (QuakeHeap.element element : heap.elemList) {
            System.out.println(element.get_key());
            System.out.println('\n');
        }
    }*/

    // test for insertion
    /*public static void main(String[] args) {
        QuakeHeap tester = new QuakeHeap();
        tester.gen_insert();
        tester.gen_insert();
        System.out.println(tester.rootList.size());
        for (QuakeHeap.QuakeNode element : tester.rootList) {
            System.out.println(element.get_id());
            System.out.println(element.get_key());
        }
    }*/

}
