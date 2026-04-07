package M1.SET4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dynamicTemplateProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        Pattern pattern = Pattern.compile("\\$\\{(\\w+):(.*?)\\}");

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);

            StringBuffer result = new StringBuffer();

            while (matcher.find()) {
                String type = matcher.group(1);
                String value = matcher.group(2);

                String replacement = "INVALID";

                if (type.equals("UPPER")) {
                    replacement = value.toUpperCase();
                }

                else if (type.equals("LOWER")) {
                    replacement = value.toLowerCase();
                }

                else if (type.equals("DATE")){
                    try {
                        DateTimeFormatter input = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        LocalDate date = LocalDate.parse(value, input);
                        replacement = date.format(output);
                    }catch (Exception e){
                        replacement = "INVALID";
                    }
                }

                else if(type.equals("REPEAT")){
                    String[] parts = value.split(",");
                    if(parts.length == 2){
                        String word = parts[0];
                        int count = Integer.parseInt(parts[1]);
                        StringBuilder temp = new StringBuilder();

                        for(int j=0;j<count;j++)
                            temp.append(word);

                        replacement = temp.toString();
                    }
                }
                matcher.appendReplacement(result, replacement);
            }
            matcher.appendTail(result);
            System.out.println(result.toString());
        }
    }
}
