package M1.SET3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorporateEmailAccessControl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=Integer.parseInt(sc.nextLine());

        String regex = "^[a-z]{3,}\\.[a-z]{3,}\\d{4,}@(sales|marketing|IT|product)\\.company\\.com$";

        Pattern pattern = Pattern.compile(regex);
        for(int i = 0; i < n; i++){
            String email = sc.nextLine();
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches())
                System.out.println("Access Granted");
            else
                System.out.println("Access Denied");
        }
    }
}
