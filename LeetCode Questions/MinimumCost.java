import java.util.*;

public class MinimumCost {

    // Your original logic
    public static int minimumCost(int[] A) {
        int a = 51, b = 51;

        for (int i = 1; i < A.length; i++) {
            if (A[i] < a) {
                b = a;
                a = A[i];
            } else if (A[i] < b) {
                b = A[i];
            }

            if (a == 1 && b == 1) break;
        }

        return A[0] + a + b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Array size
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] A = new int[n];

        // Input array
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // Call method
        int result = minimumCost(A);

        // Output result
        System.out.println("Minimum Cost = " + result);

        sc.close();
    }
}
