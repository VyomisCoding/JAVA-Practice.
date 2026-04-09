/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;
public class emailregex
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    sc.nextLine();
	    while(n-->0){
	        String s = sc.nextLine();
	        if(!s.matches("^[a-z]{3,}\\.[a-z]{3,}[0-9]{4,}@(IT|marketing|product|sales)\\.company\\.com$")){
	           
	            System.out.println("NO");
	        }else{
	            System.out.println("YES");
	        }
	    }
	//	System.out.println("Hello World");
	}
}