import java.util.*;
import java.util.stream.*;

public class MapQuestions {

    public static void main(String[] args){

        // 21. Given a Map of student names and scores, find highest score **********************************************************************
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Amit", 87);
        scores.put("Rohit", 92);
        scores.put("Sneha", 95);

        // max() with Comparator
        String topStudent = scores.entrySet()
                                  .stream()
                                  .max(Map.Entry.comparingByValue())
                                  .get()
                                  .getKey();

        System.out.println("Q21 Highest Scoring Student = " + topStudent);

        // 22. Convert Map → Sorted List of students by score **********************************************************************
        List<Map.Entry<String,Integer>> sortedStudents =
                scores.entrySet()
                      .stream()
                      .sorted(Map.Entry.comparingByValue())     // ascending
                      .collect(Collectors.toList());

        System.out.println("Q22 Sorted by Score = " + sortedStudents);


        
        // 23. First Non-Repeating Character in String **********************************************************************
        String str = "swiss";

        // count freq using Map
        Map<Character, Integer> freq = new LinkedHashMap<>(); // maintains order

        for (char c : str.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // find first char with freq = 1
        char nonRepeating = freq.entrySet().stream()
                                .filter(e -> e.getValue() == 1)
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElse('-');

        System.out.println("Q23 First Non-Repeating = " + nonRepeating);


        
        // 24. Map<String, List<String>> courses → students enrolled in ≥ 2 courses **********************************************************************
        Map<String, List<String>> courseMap = new HashMap<>();

        courseMap.put("Math", Arrays.asList("Amit", "Rohit", "Sneha"));
        courseMap.put("Science", Arrays.asList("Rohit", "Sneha"));
        courseMap.put("English", Arrays.asList("Amit", "Sneha"));

        // Flatten → Count occurrences → Keep students with count >= 2
        Map<String, Long> studentCount =
                courseMap.values()
                         .stream()
                         .flatMap(List::stream)
                         .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<String> atleast2 = studentCount.entrySet()
                                            .stream()
                                            .filter(e -> e.getValue() >= 2)
                                            .map(Map.Entry::getKey)
                                            .collect(Collectors.toList());

        System.out.println("Q24 Students Enrolled in ≥ 2 Courses = " + atleast2);


        
        // 25. Convert Map → List of String as "ID: Name" **********************************************************************
        Map<Integer, String> students = new LinkedHashMap<>();
        students.put(101, "Amit");
        students.put(102, "Sneha");

        List<String> formatted =
                students.entrySet()
                        .stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.toList());

        System.out.println("Q25 Formatted List = " + formatted);


        
        // 26. Merge two maps (add values for common keys)**********************************************************************
        Map<String, Integer> m1 = new HashMap<>();
        m1.put("A", 10); m1.put("B", 20);

        Map<String, Integer> m2 = new HashMap<>();
        m2.put("B", 5); m2.put("C", 15);

        // merge() handles sum for same keys
        Map<String, Integer> merged = new HashMap<>(m1);
        m2.forEach((k,v) -> merged.merge(k, v, Integer::sum));

        System.out.println("Q26 Merged Map = " + merged);


        
        // 27. Increase each price by 5% **********************************************************************
        Map<String, Double> items = new HashMap<>();
        items.put("Pen", 10.0);
        items.put("Notebook", 50.0);

        items.replaceAll((k, v) -> v * 1.05);

        System.out.println("Q27 Updated Prices = " + items);


        
        // 28. Filter Map where value > 50 **********************************************************************
        Map<String, Integer> data = new HashMap<>();
        data.put("X", 40);
        data.put("Y", 60);
        data.put("Z", 90);

        Map<String, Integer> filtered =
                data.entrySet()
                    .stream()
                    .filter(e -> e.getValue() > 50)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Q28 Filtered Map = " + filtered);


        
        // 29. Employee with Highest Experience (stored in Map) **********************************************************************
        class Emp {
            String name;
            int experience;
            Emp(String n, int exp) { name = n; experience = exp; }
        }

        Map<Integer, Emp> empMap = new HashMap<>();
        empMap.put(1, new Emp("Amit", 3));
        empMap.put(2, new Emp("Rohit", 5));
        empMap.put(3, new Emp("Sneha", 7));

        Emp mostExperienced = empMap.values()
                                    .stream()
                                    .max(Comparator.comparing(e -> e.experience))
                                    .get();

        System.out.println("Q29 Highest Experience = " + mostExperienced.name);


        
        // 30. Check if two maps have at least one common key **********************************************************************
        Map<String, Integer> k1 = Map.of("A", 1, "B", 2, "C", 3);
        Map<String, Integer> k2 = Map.of("C", 5, "D", 8);

        boolean hasCommonKey =
                k1.keySet().stream().anyMatch(k2::containsKey);

        System.out.println("Q30 Has Common Key? = " + hasCommonKey);
    }
}