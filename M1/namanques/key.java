import java.util.*;

public class key {

    public static String process(String s){

        if(s.length() == 0) return "Invalid Input (empty string)";

        if(s.length() < 6){
            return "Invalid Input (length < 6)";
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == ' ')
                return "Invalid Input (contains space)";

            else if(c >= '0' && c <= '9')
                return "Invalid Input (contains digits)";

            else if(!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
                return "Invalid Input (contains special character)";
        }

        // Step 1: lowercase
        StringBuilder se = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(c >= 'A' && c <= 'Z')
                c = (char)(c + 32);

            se.append(c);
        }

        String ans = se.toString();

        // Step 2: remove even ASCII
        StringBuilder ans2 = new StringBuilder();
        for(int i = 0; i < ans.length(); i++){
            char c = ans.charAt(i);

            if(c % 2 != 0)
                ans2.append(c);
        }

        // Step 3: reverse
        StringBuilder rev = new StringBuilder(ans2).reverse();

        // Step 4: uppercase even index
        StringBuilder finalAns = new StringBuilder();

        for(int i = 0; i < rev.length(); i++){
            char c = rev.charAt(i);

            if(i % 2 == 0 && c >= 'a' && c <= 'z')
                c = (char)(c - 32);

            finalAns.append(c);
        }

        return "The generated key is - " + finalAns.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        while(n-- > 0){
            String s = sc.nextLine();
            System.out.println(process(s));
        }
    }
}