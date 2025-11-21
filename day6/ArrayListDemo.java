package com.day6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {

        // 1. Declaration
        ArrayList list = new ArrayList<>();

        // 2. Add data to ArrayList (Heterogeneous allowed)
        list.add("Apple");
        list.add(101);
        list.add('C');
        list.add(true);
        list.add(null);
        list.add("Apple");

        // 3. Size of ArrayList
        System.out.println("Size: " + list.size());

        // 4. Print ArrayList
        System.out.println("List: " + list);

        // 5. Insert element at particular index
        list.add(3, "Orange");
        System.out.println("After inserting at index 3: " + list);

        // 6. Modify/update element
        list.set(4, false);
        System.out.println("After updating index 4: " + list);

        // 7. Access element by index
        System.out.println("Element at index 5: " + list.get(5));

        // 8. Clear ArrayList
        list.clear();
        System.out.println("After clear: " + list);

        // 9. Check if empty
        System.out.println("Is empty? " + list.isEmpty());

        // 10. Convert array to ArrayList
        Integer[] array = {1, 2, 3, 4, 5};
        List<Integer> list1 = new ArrayList<>(Arrays.asList(array));
        System.out.println("Converted ArrayList: " + list1);

        // 11. Traversal Techniques -------------------------
        System.out.println("For loop:");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }

        System.out.println("Enhanced for-loop:");
        for (Integer temp : list1) {
            System.out.println(temp);
        }

        System.out.println("Iterator:");
        Iterator<Integer> it = list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

