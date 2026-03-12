// Check if Set Contains a Specific Product ID

import java.util.*;

public class Q16 {
    public static void main(String[] args) {

        Set<Integer> productIds = new HashSet<>(Arrays.asList(101, 102, 103, 104));

        int searchId = 103;

        System.out.println(productIds.contains(searchId) ? "ID Found" : "ID Not Found");
    }
}