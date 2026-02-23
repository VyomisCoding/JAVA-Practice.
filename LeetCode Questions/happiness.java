import java.util.*;

public class happiness{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of children: ");
        int n = sc.nextInt();
        int[] happiness = new int[n];
        System.out.println("Enter happiness values:");
        for (int i = 0; i < n; i++) {
            happiness[i] = sc.nextInt();
        }
        System.out.print("Enter number of children to select (k): ");
        int k = sc.nextInt();

        Arrays.sort(happiness);
        long sum = 0;
        int turns = 0;

        for (int i = n - 1; i >= 0 && turns < k; i--) {
            int currentHappiness = happiness[i] - turns;
            if (currentHappiness > 0) {
                sum += currentHappiness;
            }
            turns++;
        }
        System.out.println("Maximum Happiness Sum = " + sum);
    }
}

