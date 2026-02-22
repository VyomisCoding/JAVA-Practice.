import java.util.*;
import java.util.stream.*;

public class Q12 {
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>(Arrays.asList(5, 1, 9, 3, 7));

        // stream → sorted → list
        List<Integer> sortedList = set.stream()
                                      .sorted()
                                      .collect(Collectors.toList());

        System.out.println(sortedList);
    }
}