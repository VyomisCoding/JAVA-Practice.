// Convert List → Set (Remove Duplicates)

import java.util.*;

public class Q14 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Amit", "Rohit", "Amit", "Sneha");

        // Convert → Set removes duplicates
        Set<String> unique = new HashSet<>(names);

        System.out.println("Unique Names = " + unique);
    }
}
