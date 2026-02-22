//Remove Duplicates but Keep Insertion Order

import java.util.*;
import java.util.stream.*;

public class Q4 {
    public static void main(String[] args){
        List<Integer> nums = Arrays.asList(1, 2, 3, 2, 1, 4, 5, 4);
        // distinct() already keeps original order-----------------------
        List<Integer> unique = nums.stream()
                                   .distinct()
                                   .collect(Collectors.toList());

        System.out.println(unique);
    }
}