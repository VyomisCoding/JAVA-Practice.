// Convert HashSet → TreeSet (Sorted Order)

import java.util.*;


public class Q17 {
    public static void main(String[] args) {

        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(9, 3, 7, 1));

        // TreeSet sorts automatically
        TreeSet<Integer> treeSet = new TreeSet<>(hashSet);

        System.out.println(treeSet);
    }
}