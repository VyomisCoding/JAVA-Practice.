import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

public class placeholderregex {

    public static String process(String line) {

        Pattern p = Pattern.compile("\\$\\{([A-Z]+):([^}]+)\\}");
        Matcher m = p.matcher(line);

        StringBuffer result = new StringBuffer();

        while (m.find()) {

            String type = m.group(1);
            String value = m.group(2);
            String replacement = "";

            switch(type) {

                case "UPPER":
                    replacement = value.toUpperCase();
                    break;

                case "LOWER":
                    replacement = value.toLowerCase();
                    break;

                case "REPEAT":
                    try {
                        String parts[] = value.split(",");
                        String word = parts[0];
                        int count = Integer.parseInt(parts[1]);

                        StringBuilder sb = new StringBuilder();
                        for(int i=0;i<count;i++)
                            sb.append(word);

                        replacement = sb.toString();
                    }
                    catch(Exception e) {
                        replacement = "INVALID";
                    }
                    break;

                case "DATE":
                    try {
                        DateTimeFormatter input =
                                DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        DateTimeFormatter output =
                                DateTimeFormatter.ofPattern("yyyy/MM/dd");

                        LocalDate date = LocalDate.parse(value, input);
                        replacement = date.format(output);
                    }
                    catch(Exception e) {
                        replacement = "INVALID";
                    }
                    break;

                default:
                    replacement = "INVALID";
            }

            m.appendReplacement(result, replacement);
        }

        m.appendTail(result);

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for(int i=0;i<n;i++) {

            String line = sc.nextLine();
            System.out.println(process(line));
        }
    }
}
