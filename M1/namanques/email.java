
// email without regex


import java.util.*;
public class email {
    static boolean check(String s){
        String[] modes = {"sales","marketing","IT","product"};
        for(String find : modes){
            if(find.equals(s)){
                return true;
            }
        }
        return false;
    }
    
    static boolean valid(String s){
        if(!s.contains("@")){
            return false;
        }
        String[] parts = s.split("@");
    //    if(parts.length!=2){
      //      return false;
    //    }
        String first = parts[0];
        String second = parts[1];
        String[] part = second.split("\\.");
     //   if(part.length!=3){
       //     return false;
        //}
        String f = part[0];
        String d = part[1];
        String e = part[2];
        if(!check(f)){
            return false;
        }
        if(!d.equals("company") || !e.equals("com")){
            return false;
        }
        String[] p =first.split("\\.");
      //  if(p.length!=2){
        //    return false;
        //}
        String low = p[0];
        String high = p[1];
        if(low.length()<3 || high.length()<3){
            return false;
        }
        int pos = -1;
        int c = 0;
        int i = high.length()-1;
        char[] cu = high.toCharArray();
        while(i>=0){

            if(cu[i]>='0' && cu[i]<='9'){
                c++;
                
            }else{
                pos = i;
                break;
            }
            
            i--;
        }
        if(c<4){
            return false;
        }
        String chuk = high.substring(0 , pos + 1);
        for(int j=0;j<low.length();j++){
            char co = low.charAt(j);
            if(co<'a' || co>'z'){
                return false;
            }
        }
        for(int j=0;j<chuk.length();j++){
            char co = chuk.charAt(j);
            if(co<'a' || co>'z'){
                return false;
            }
        }
        return true;
        
    }
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    sc.nextLine();
	    while(n-->0){
	        String s = sc.nextLine();
	        if(valid(s)){
	            System.out.println("YES");
	        }else{
	            System.out.println("no");
	        }
	    }
	//	System.out.println("Hello World");
	}
}