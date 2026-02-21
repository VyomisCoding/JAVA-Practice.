
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnrollmentManager{
    private static List<Enrollment> enrollments = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    // Add Enrollment
    private static void addEnrollment(){
        System.out.println("Enter student name");
        String student = sc.nextLine();
        System.out.print("Enter Course Name: ");
        String course = sc.nextLine();
        System.out.print("Enter Course Category: ");
        String category = sc.nextLine();
        System.out.println("Enter Enrollment Date");
        String dateInput = sc.nextLine();
        LocalDate date = LocalDate.parse(dateInput);

        enrollments.add(new Enrollment(student, course , category, date));
        System.out.println("Enrollment Added Successfully!");
    }

    // Filter by course
    private static void filterByCourse(){
        System.out.println("Enter course name");
        String course = sc.nextLine();

        enrollments.stream().filter(e -> e.getCourseName().equalsIgnoreCase(course)).forEach(System.out::println);
    }

    // filter by category
    private static void filterByCategory(){
        System.out.print("Enter Category");
        String category = sc.nextLine();
        enrollments.stream().filter(e -> e.getCourseCategory().equalsIgnoreCase(category)).forEach(System.out::println);
    }

    // group by course
    private static void groupByCourse(){
        Map<String, List<Enrollment>> grouped = enrollments.stream().collect(Collectors.groupingBy(Enrollment::getCourseName));
        grouped.forEach((course,list) -> {
            System.out.println("\nCourse: "+ course);
            list.forEach(System.out::println);
        });
    }

    // Count per Category
    private static void countPerCategory(){
        Map<String, Long> count = enrollments.stream().collect(Collectors.groupingBy( Enrollment::getCourseCategory, Collectors.counting()));
        count.forEach((category, total) -> System.out.println(category + " -> " + total));
    }

    // Sort by Date
    private static void sortByDate(){
        enrollments.stream().sorted(Comparator.comparing(Enrollment::getEnrollmentDate)).forEach(System.out::println);
    }




    public static void main(String[] args) {
        while(true){
            System.out.println("\n-Online Course Enrollment Manager-");
            System.out.println("1. Add Enrollment");
            System.out.println("2. Filter by Course");
            System.out.println("3. Filter by Category");
            System.out.println("4. Group by Course");
            System.out.println("5. Count per Category");
            System.out.println("6. Sort by Enrollment Date");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();   //consume new line

            switch (choice){
                case 1:
                    addEnrollment();
                    break;

                case 2:
                    filterByCourse();
                    break;

                case 3:
                    filterByCategory();
                    break;
                
                case 4:
                    groupByCourse();
                    break;

                case 5:
                    countPerCategory();
                    break;
                
                case 6:
                    sortByDate();
                    break;
                
                case 7:
                    System.out.println("Exiting...");
                    return;
      
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }
    }

    
}
