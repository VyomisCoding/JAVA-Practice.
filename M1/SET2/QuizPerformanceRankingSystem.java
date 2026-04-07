package M1.SET2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizPerformanceRankingSystem{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<String[]> list = new ArrayList<>();

        for(int i=0;i<n;i++){
            String line = sc.nextLine();
            String[] p = line.split(" ");
            if(p[0].equals("Record")){
                list.add(p);
                System.out.println("Record Added: " + p[1]);
            }
            else if(p[0].equals("Top")){
                if(list.size()==0){
                    System.out.println("No Records Available");
                    continue;
                }
                String query = p[1];

                // Department Top --------
                if(query.equals("Engineering") || query.equals("Electronics")){
                    int max = -1;
                    boolean found = false;
                    for(String[] s : list){
                        if(s[2].equals(query)){
                            found = true;
                            int total = Integer.parseInt(s[3]) + Integer.parseInt(s[4]) + Integer.parseInt(s[5]);
                            if(total > max)
                                max = total;
                        }
                    }

                    if(!found){
                        System.out.println("Department Not Found");
                        continue;
                    }

                    for(String[] s : list){
                        if(s[2].equals(query)){
                            int total = Integer.parseInt(s[3]) + Integer.parseInt(s[4]) + Integer.parseInt(s[5]);

                            if(total == max)
                                System.out.println(s[1] + " " + total);
                        }
                    }
                }

                // Quiz Top --------
                else{
                    int max = -1;
                    for(String[] s : list){
                        int score = 0;
                        if(query.equals("Q1"))
                            score = Integer.parseInt(s[3]);

                        else if(query.equals("Q2"))
                            score = Integer.parseInt(s[4]);

                        else if(query.equals("Q3"))
                            score = Integer.parseInt(s[5]);

                        if(score > max)
                            max = score;
                    }

                    for(String[] s : list){
                        int score = 0;
                        if(query.equals("Q1"))
                            score = Integer.parseInt(s[3]);

                        else if(query.equals("Q2"))
                            score = Integer.parseInt(s[4]);

                        else if(query.equals("Q3"))
                            score = Integer.parseInt(s[5]);

                        if(score == max)
                            System.out.println(s[1] + " " + score);
                    }
                }
            }
        }
    }
}
