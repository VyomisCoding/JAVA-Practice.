// Find Most Recent Transaction

import java.time.*;
import java.util.*;

public class Q8 {
    public static void main(String[] args) {

        List<LocalDateTime> transactions = Arrays.asList(
                LocalDateTime.of(2024, 2, 1, 10, 0),
                LocalDateTime.of(2024, 2, 5, 12, 30),
                LocalDateTime.of(2024, 2, 3, 9, 15)
        );

        // max() for latest timestamp
        LocalDateTime latest = transactions.stream()
                                           .max(LocalDateTime::compareTo)
                                           .orElse(null);

        System.out.println("Most Recent = " + latest);
    }
}