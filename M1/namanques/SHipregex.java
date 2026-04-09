import java.time.*;
import java.util.*;

public class SHipregex {

    static boolean validcode(String s){
        if(!s.matches("SHIP-[1-9]\\d{5}")){
            return false;
        }
        String ans = s.substring(5);
        int c =1;
        for(int i=1;i<ans.length();i++){
            if(ans.charAt(i) == ans.charAt(i-1)){
                c++;
                if(c>3){
                    return false;
                }
            }else{
                c =1;
            }
        }
        return true;
    }


    static boolean validdate(String s){
        if(!s.matches("20\\d{2}-\\d{2}-\\d{2}")){
            return false;
        }
        try{
            LocalDate d = LocalDate.parse(s);
            int year = d.getYear();

            if(year < 2000 || year > 2099)
                return false;

        }catch(Exception e){
            return false;
        }
        return true;
    }


    static boolean validmode(String s){
        if(!s.matches("AIR|ROAD|RAIL|SEA|EXPRESS|FREIGHT")){
            return false;
        }
       
    
    return true;
}

    static boolean validweight(String s){
        if(!s.matches("(0|[1-9]\\d{0,5})(\\.\\d{1,2}?)")){
            return false;
        }
          double value = Double.parseDouble(s);

        if (value <= 0 || value > 999999.99){
            return false;
}
        return true;
    
  

      
    }


    static boolean validmode2(String s){
        String[] modes = {"DELIVERED","CANCELLED","IN_TRANSIT"};

        for(String m : modes){
            if(m.equals(s)) return true;
        }
        return false;
    }


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        while(n-- > 0){

            String s = sc.nextLine();

            String[] p = s.split("\\|");

            if(p.length != 5){
                System.out.println("NO");
                continue;
            }

            boolean valid =
                validcode(p[0]) &&
                validdate(p[1]) &&
                validmode(p[2]) &&
                validweight(p[3]) &&
                validmode2(p[4]);

            if(valid){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}