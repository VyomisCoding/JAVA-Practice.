// Calculate Total Revenue From Customer Orders

import java.util.*;

public class Q5 {
    public static void main(String[] args){
        List<Double> orders = Arrays.asList(1200.0, 800.5, 650.0, 999.99);

        double total = orders.stream()
                             .mapToDouble(Double::doubleValue)
                             .sum();

        System.out.println("Total Revenue = " + total);
    }
}