// Merge Two Sets (Duplicate Automatically Removed)

import java.util.*;

public class Q20 {
    public static void main(String[] args) {

        Set<Integer> s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));

        // union using addAll()
        Set<Integer> merged = new HashSet<>(s1);
        merged.addAll(s2);

        System.out.println("Merged Set = " + merged);
    }
}