import java.util.*;

public class FitnessTrackerApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter workout routine (activity:hours:intensity)");
        String routine = sc.nextLine();

        System.out.println("Enter weight in kg:");
        double weight = sc.nextDouble();

        try {
            double calories = FitnessUtil.calculateCalories(routine, weight);
            System.out.println("Total Calories Burned: " + calories);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Thank you for using Fitness Tracker!");
    }
}