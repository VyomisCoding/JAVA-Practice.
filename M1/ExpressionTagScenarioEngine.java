package M1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionTagScenarioEngine {
    public static String evaluate(String input){
        
        Pattern pattern = Pattern.compile("\\[(SUM|MUL|MAX|MIN):([^\\]]+)\\]");
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        
        while(matcher.find()){
            String op = matcher.group(1);
            String expr = matcher.group(2);

            String replacement = process(op,expr);

            matcher.appendReplacement(result, replacement);
        }

        matcher.appendTail(result);

        // handle broken tags
        if(result.toString().contains("[") || result.toString().contains("]")){
            return result.toString().replaceAll("\\[.*","ERROR");
        }
        return result.toString();

    }

    public static String process(String op, String expr){
        String[] nums = expr.split(",");

        if(nums.length < 2) return "ERROR";

        List<Integers> list = new ArrayList<>();

        for(String s:nums){
            if(!s.matches("-?(0|[1-9]\\d*)")){
                return "ERROR";
            }
            list.add(Integer.parseInt(s));
        }
        int res = 0;

        switch (op){
            case "SUM":
                res = 0;
                for (int x : list) res += x;
                break;

            case "MUL":
                res = 1;
                for (int x : list) res *= x;
                break;

            case "MAX":
                res = Collections.max(list);
                break;

            case "MIN":
                res = Collections.min(list);
                break;
        }
        return String.valueOf(res);
    }
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            System.out.println(evaluate(input));
        }
    }
    
}
