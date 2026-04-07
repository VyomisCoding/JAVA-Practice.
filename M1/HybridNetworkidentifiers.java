package M1;

import java.util.Scanner;

public class HybridNetworkidentifiers{
    public static String validateIdentifier(String input){
        String regex = "([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}" + "::" + "([0-9A-F]{2}:){5}[0-9A-F]{2}$" ;

        if(input.matches(regex)){
            return "AUTHENTIC DEVICE";
        }else{
            return "REJECTED DEVICE";
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();

        for(int i=0;i<n;i++){
            String s = sc.nextLine();
            System.out.println(validateIdentifier(s));
        }
    }
    
}
