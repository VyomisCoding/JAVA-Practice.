import java.util.Scanner;

public class CorporateEmailValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine().trim();

        if(validateEmail(email)){
            System.out.println("VALID");
        }else{
            System.out.println("INVALID");
        }
    }

    private static boolean validateEmail(String email){

        if(email.contains(" ")) return false;            // no spaces allowed

        if(!email.contains("@")) return false;           // basic structure check

        String[] parts = email.split("@");
        if(parts.length != 2) return false;              // split into left side and right side

        String left = parts[0];  // firstname.lastname.digits
        String right = parts[1]; // department.company.com

        // 1) Validate firstname.lastname.digits-------------------------------------------------
        String[] leftParts = left.split("\\.");

        if (leftParts.length != 3) return false;

        String firstname = leftParts[0];
        String lastname = leftParts[1];
        String digits = leftParts[2];

        // firstname & lastname checks
        if (!firstname.matches("[a-z]{3,}")) return false;
        if (!lastname.matches("[a-z]{3,}")) return false;

        // digits: at least 4 digits
        if (!digits.matches("\\d{4,}")) return false;

        

        // 2) Validate department.company.com  ---------------------------------------------------
        String[] rightParts = right.split("\\.");

        // Expected: department.company.com  → 3 parts
        if (rightParts.length != 3) return false;

        String department = rightParts[0];
        String domain1 = rightParts[1];
        String domain2 = rightParts[2];

        // Department validation
        if (!department.equals("hr") &&
            !department.equals("it") &&
            !department.equals("finance") &&
            !department.equals("admin")) {
            return false;
        }

        // Domain must be exactly "company.com"
        if (!domain1.equals("company")) return false;
        if (!domain2.equals("com")) return false;

        return true; // All rules satisfied
    }
}
