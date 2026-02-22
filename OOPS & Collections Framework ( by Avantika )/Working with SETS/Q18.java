//Find Largest & Smallest in a Set

import java.util.*;

public class Q18 {
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>(Arrays.asList(50, 20, 100, 5, 70));

        int min = set.stream().min(Integer::compareTo).get();
        int max = set.stream().max(Integer::compareTo).get();

        System.out.println("Smallest = " + min);
        System.out.println("Largest = " + max);
    }
}
