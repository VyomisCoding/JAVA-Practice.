import java.util.*;

public class NextGreatestLetter {

    // Your original logic
    public static char nextGreatestLetter(char[] letters, char target) {
        char res = letters[0];
        boolean flag = false;

        for (char ch : letters) {
            if (!flag) {
                if (ch > target) {
                    res = ch;
                    flag = !flag;
                }
            } else {
                if (ch > target && ch < res) res = ch;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take size of array
        System.out.print("Enter number of letters: ");
        int n = sc.nextInt();

        char[] letters = new char[n];

        // Input characters
        System.out.println("Enter characters one by one:");
        for (int i = 0; i < n; i++) {
            letters[i] = sc.next().charAt(0);
        }

        // Target char
        System.out.print("Enter target character: ");
        char target = sc.next().charAt(0);

        // Calling function
        char result = nextGreatestLetter(letters, target);

        //
    }
}