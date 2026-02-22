import java.util.*;
import java.util.stream.*;


public class Q2 {
    public static void main(String[] args){
        List<Double> salaries = Arrays.asList(30000.0, 45000.0, 50000.0);

        // map() → 10% increment-----------------------------------------
        List<Double> updated = salaries.stream()
                                       .map(s -> s * 1.10)
                                       .collect(Collectors.toList());

        System.out.println(updated);
    }
}