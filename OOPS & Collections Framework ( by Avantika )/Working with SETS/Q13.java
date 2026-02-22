// Symmetric Difference (Numbers in either but NOT both)

import java.util.*;
import java.util.stream.*;

public class Q13 {
    public static void main(String[] args) {

        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6));

        // symmetric difference = (A union B) - (A intersect B)
        Set<Integer> symDiff = new HashSet<>(
                Stream.concat(a.stream(), b.stream())
                      .collect(Collectors.toSet())
        );

        // remove common elements
        Set<Integer> common = a.stream().filter(b::contains).collect(Collectors.toSet());
        symDiff.removeAll(common);

        System.out.println("Symmetric Difference = " + symDiff);
    }
}