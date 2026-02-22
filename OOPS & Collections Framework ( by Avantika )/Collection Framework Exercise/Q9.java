// Group Words by Starting Letter

import java.util.*;
import java.util.stream.*;

public class Q9 {
    public static void main(String[] args){

        List<String> words = Arrays.asList("Apple", "Apricot", "Banana", "Blueberry", "Avocado");

        Map<Character, List<String>> grouped =
                words.stream()
                     .collect(Collectors.groupingBy(word -> word.charAt(0)));

        System.out.println(grouped);
    }
}
