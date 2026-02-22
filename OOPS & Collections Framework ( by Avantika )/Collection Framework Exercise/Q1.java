import java.util.*;
import java.util.stream.*;

public class Q1 {
    public static void main(String[] args){
        List<Integer> scores = Arrays.asList(78, 95, 88, 92, 99, 67);

        // Sort descending → limit 3---------------------------------
        List<Integer> top3 = scores.stream()
                                   .sorted(Comparator.reverseOrder())
                                   .limit(3)
                                   .collect(Collectors.toList());

        System.out.println("Top 3 Scores = " + top3);
    }
}