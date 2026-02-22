// Convert Full Names → First Names Only

import java.util.*;
import java.util.stream.*;

public class Q6 {
    public static void main(String[] args){
        
        List<String> names = Arrays.asList("John Doe", "Rahul Sharma", "Amit Singh");

        List<String> firstNames = names.stream()
                                       .map(n -> n.split(" ")[0])
                                       .collect(Collectors.toList());

        System.out.println(firstNames);
    }
}