// Check if Two Lists Contain Same Elements (Order Ignored)

import java.util.*;
import java.util.stream.*;

public class Q10 {
    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(4, 3, 2, 1);

        boolean same = list1.stream().sorted().collect(Collectors.toList())
                        .equals(list2.stream().sorted().collect(Collectors.toList()));

        System.out.println("Same Elements? " + same);
    }
}