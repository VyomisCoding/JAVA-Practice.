
import java.util.*;

class quiz{
    String name;
    String dept;
    int q1 ; 
    int q2 ; 
    int q3;
    quiz(String name , String dept , int q1 , int q2 , int q3){
        this.name = name;
        this.dept = dept;
        this.q1 = q1 ; 
        this.q2 = q2;
        this.q3 = q3;
    }
}
class start{
    List<quiz> list  = new ArrayList<>();
    public void add(String name , String dept , int q1 , int q2 , int q3){
        list.add(new quiz(name , dept , q1 , q2 , q3));
        System.out.println("Recode Adeded:" + " " + name);
    }
    public void findbydept(String dept){
        int max = Integer.MIN_VALUE;
        for(quiz q : list){
            if(q.dept.equals(dept)){
                max = Math.max(max , q.q1 + q.q2 + q.q3);
        
            }
        }
        //int sum = 0;
        for(quiz q : list){
            if(q.dept.equals(dept)){
                if(q.q1 + q.q2 + q.q3 == max){
                    System.out.println(q.name + " " + max);
                    
                }
            }
        }
    }
    public void findbyquiz(String p){
        int max = Integer.MIN_VALUE;
        for(quiz q : list){
            if(p.equals("Q1")){
                max = Math.max(max , q.q1);
            }
            else if(p.equals("Q2")){
                max = Math.max(max , q.q2);
            }
            else{
                max = Math.max(max , q.q3);
            }
        }
        for(quiz q : list){
            if(p.equals("Q1")){
                if(max == q.q1){
                    System.out.println(q.name + " " + max);
                }
            }
            else if(p.equals("Q2")){
                if(max == q.q2){
                    System.out.println(q.name + " " + max);
                }
            }else{
                if(max == q.q3){
                    System.out.println(q.name + " " + max);
                }
            }
        }
    }
    
}
public class QuizperformanceRankingSystem
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    start st = new start();
	    int n = sc.nextInt();
	    sc.nextLine();
	    while(n-->0){
	        String s = sc.nextLine();
	        String[] p =s.split(" ");
	        String first = p[0];
	        if(first.equals("Record")){
	            st.add(p[1],p[2],Integer.parseInt(p[3]),Integer.parseInt(p[4]),Integer.parseInt(p[5]));
	            
	        }
	        if(first.equals("Top")){
	            if(p[1].equals("Q1") || p[1].equals("Q2") || p[1].equals("Q3")){
	                st.findbyquiz(p[1]);
	            }else{
	                st.findbydept(p[1]);
	            }
	        }
	    }
	//	System.out.println("Hello World");
	}
} 3