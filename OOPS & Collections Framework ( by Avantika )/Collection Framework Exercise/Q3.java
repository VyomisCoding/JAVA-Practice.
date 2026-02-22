
import java.util.*;
import java.util.stream.*;

public class Q3 {
    public static void main(String[] args){
        List<String> products = Arrays.asList("New Laptop", "Old Phone outdated", "Smart TV", "outdated Printer");

        // filter() → remove "outdated"
        List<String> updated = products.stream()
                                       .filter(name -> !name.toLowerCase().contains("outdated"))
                                       .collect(Collectors.toList());

        System.out.println(updated);
    }
}