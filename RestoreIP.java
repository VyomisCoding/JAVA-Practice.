import java.util.*;

public class RestoreIP {

    List<String> ans = new ArrayList<>();
    String str;

    // Your original logic
    public List<String> restoreIpAddresses(String s) {
        str = s;
        magical("", 0, 0);
        return ans;
    }

    void magical(String path, int index, int dots) {
        if (dots > 4) return;

        if (dots == 4 && index >= str.length()) {
            ans.add(path.substring(0, path.length() - 1)); // remove last dot
            return;
        }

        for (int length = 1; length <= 3 && index + length <= str.length(); length++) {
            String num = str.substring(index, index + length);

            // No leading zeros unless single digit
            if (num.charAt(0) == '0' && length != 1) break;

            // Check <= 255
            if (Integer.parseInt(num) <= 255) {
                magical(path + num + ".", index + length, dots + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take input string
        System.out.print("Enter the string of digits: ");
        String s = sc.nextLine();

        // Create object and call method
        RestoreIP obj = new RestoreIP();
        List<String> result = obj.restoreIpAddresses(s);

        // Print results
        System.out.println("Possible valid IP addresses:");
        for (String ip : result) {
            System.out.println(ip);
        }

        sc.close();
    }
}
