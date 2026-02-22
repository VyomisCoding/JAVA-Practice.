import java.util.*;
import java.util.stream.*;

public class Q11 {
    public static void main(String[] args) {

        Set<String> s1 = new HashSet<>(Arrays.asList("Rahul", "Amit", "Sneha"));
        Set<String> s2 = new HashSet<>(Arrays.asList("Sneha", "Vikram", "Amit"));

        // retainAll() → intersection
        Set<String> common = s1.stream()
                               .filter(s2::contains)
                               .collect(Collectors.toSet());

        System.out.println("Common Users = " + common);
    }
}