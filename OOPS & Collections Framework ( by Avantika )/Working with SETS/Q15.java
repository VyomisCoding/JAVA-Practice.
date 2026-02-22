// Find First Country Starting With “I”

import java.util.*;

public class Q15 {
    public static void main(String[] args) {

        Set<String> countries = new HashSet<>(Arrays.asList(
                "India", "Japan", "Indonesia", "Brazil", "Italy"
        ));

        // findFirst() in streams
        String result = countries.stream()
                                 .filter(c -> c.startsWith("I"))
                                 .findFirst()
                                 .orElse("No country found");

        System.out.println(result);
    }
}