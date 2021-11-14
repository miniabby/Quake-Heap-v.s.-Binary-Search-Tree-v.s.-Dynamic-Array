/*
 * NAME: Kechen Zhao
 * ID: 957398
 */

/*
 * This is the class of Dynamic Array
 */


import java.util.ArrayList;


public class DynamicArray {

    ArrayList<Integer> dynamicArray = new ArrayList<Integer>();

    // push_back
    public void insert(int x) {
        dynamicArray.add(x);
    }

    public void delete_min() {
        int min = Integer.MAX_VALUE;
        int min_index = 0;
        for (int i = 0; i < dynamicArray.size(); i++) {
            if (dynamicArray.get(i) < min) {
                min = dynamicArray.get(i);
                min_index = i;
            }
        }
        int last_value = dynamicArray.get(dynamicArray.size()-1);
        dynamicArray.set(dynamicArray.size()-1, min);
        dynamicArray.set(min_index, last_value);
        dynamicArray.remove(dynamicArray.size()-1);
    }

    public void decrease_key(int id_target, int key_target) {
        int target_index = 0;
        for (int i = 0; i < dynamicArray.size(); i++) {
            if (dynamicArray.get(i) == id_target) {
                target_index = i;
            }
        }
        dynamicArray.set(target_index, key_target);
    }

}
