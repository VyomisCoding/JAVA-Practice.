import java.time.*;
import java.time.format.*;
import java.util.*;

public class placeholdermanual {

    static String process(String s){

        StringBuilder ans = new StringBuilder();

        for(int i=0;i<s.length();){

            if(i+1<s.length() && s.charAt(i)=='$' && s.charAt(i+1)=='{'){

                int end = -1;

                for(int j=i+1;j<s.length();j++){
                    if(s.charAt(j)=='}'){
                        end = j;
                        break;
                    }
                }

                if(end == -1){
                    ans.append(s.charAt(i));
                    i++;
                    continue;
                }

                String find = s.substring(i+2 , end);
                String[] parts = find.split(":");

                if(parts.length != 2){
                    ans.append("INVALID");
                    i = end + 1;
                    continue;
                }

                String type = parts[0];
                String value = parts[1];
                String replacement = "INVALID";

                switch(type){

                    case "UPPER":
                        replacement = value.toUpperCase();
                        break;

                    case "LOWER":
                        replacement = value.toLowerCase();
                        break;

                    case "REPEAT":
                        try{
                            String[] div = value.split(",");
                            String word = div[0];
                            int num = Integer.parseInt(div[1]);

                            StringBuilder temp = new StringBuilder();
                            for(int k=0;k<num;k++){
                                temp.append(word);
                            }

                            replacement = temp.toString();

                        } catch(Exception e){
                            replacement = "INVALID";
                        }
                        break;

                    case "DATE":
                        try{
                            DateTimeFormatter in =
                                DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            DateTimeFormatter out =
                                DateTimeFormatter.ofPattern("yyyy/MM/dd");

                            LocalDate d = LocalDate.parse(value , in);
                            replacement = d.format(out);

                        } catch(Exception e){
                            replacement = "INVALID";
                        }
                        break;

                    default:
                        replacement = "INVALID";
                }

                ans.append(replacement);

                // VERY IMPORTANT
                i = end + 1;

            } else {
                ans.append(s.charAt(i));
                i++;
            }
        }

        return ans.toString();
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