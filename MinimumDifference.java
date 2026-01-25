import java.util.*;

public class MinimumDifference {

    public static int minimumDifference(int[] nums, int k) {
        final int n = nums.length;

        if (k == 1) return 0;

        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;

        for (int l = 0, r = k - 1; r < n; ) {
            diff = Math.min(diff, nums[r++] - nums[l++]);
        }
        return diff;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take number of elements
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        // Take array input
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Take k
        System.out.print("Enter value of k: ");
        int k = sc.nextInt();

        // Call method
        int result = minimumDifference(nums, k);
        System.out.println("Minimum difference = " + result);

        sc.close();
    }
}
