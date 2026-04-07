package M1;

import java.util.*;

public class URLCheck {
    static Set<String> set = new HashSet<>();

    public static String validateURL(String url){
        // Duplicated check-----------------------------
        if(set.contains(url)){
            return "DUPLICATE URL FOUND";
        }
        
        if(!url.matches("^(http://|https://)[a-z]+\\.[a-z]+$")){// 1. Format check ---------------
            return "INVALID URL: format is invalid";
        }

        if(!(url.startsWith("http://") || url.startsWith("https://"))){// 2. Protocol check-----
            return "INVALID URL: protocol is invalid";
        }

        // Extract parts
        String temp = url.replace("http://", "").replace("https://", "");
        String[] parts = temp.split("\\.");

        String name = parts[0];           // website ka name nikalne ke lie
        String domain = "." + parts[1];   // domain ke liye

        if(!name.matches("[a-z]+") || name.length() > 10){   // 3. Website name check------------
            return "INVALID URL: website name is invalid";
        }

        // 4. Domain check
        if(!(domain.equals(".com") || domain.equals(".co") || domain.equals(".in") || domain.equals(".org") || domain.equals(".gov"))) {
            return "INVALID URL: domain is invalid";
        }

        set.add(url);
        return "VALID URL";
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String url = sc.nextLine();
            System.out.println(validateURL(url));
        }
    }

}
