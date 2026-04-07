package M1.SET2;

import java.util.*;

public class KeyGeneration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=Integer.parseInt(sc.nextLine());

        for (int i=0;i<n;i++){
            String s = sc.nextLine();
            if(s.length() == 0){
                System.out.println("Invalid Input (empty string)");
                continue;
            }

            if(s.length() < 6){
                System.out.println("Invalidinput(length < 6)");
                continue;
            }

            if(s.contains(" ")){
                System.out.println("Invalid Input (contains space)");
                continue;
            }

            boolean digit =false;
            boolean special = false;

            for(char c : s.toCharArray()) {

                if(Character.isDigit(c))
                    digit = true;

                if(!Character.isLetter(c))
                    special = true;
            }

            if(digit) {
                System.out.println("Invalid Input (contains digits)");
                continue;
            }

            if(special) {
                System.out.println("Invalid Input (contains special character)");
                continue;
            }

            // key generation ----------------------------
            s = s.toLowerCase();
            String temp = "";

            for(char c : s.toCharArray()){
                if(c%2 != 0){
                    temp += c;
                }
            }
            StringBuilder sb = new StringBuilder(temp);
            sb.reverse();

            String result = "";

            for(int j=0;j<sb.length();j++){
                char c = sb.charAt(j);
                if(j%2==0)
                    result += Character.toUpperCase(c);

                else
                    result += c;
            }
            System.out.println("The generated key is - " + result);
        }
    }
}
