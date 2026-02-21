
import java.util.*;

public class EchoWeaveEncryption{
    public static void  main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the input");
        String input = sc.nextLine();

        // input validation
        if(!input.matches("[a-zA-Z]+")){
            System.out.println(input + "is an invalid input");
            return;
        }

        // normalize to uppercase
        String str = input.toUpperCase();
        
        // find unique characters + frequency
        LinkedHashMap<Character, Integer> freqMap = new LinkedHashMap<>();

        for(char c: str.toCharArray()){
            freqMap.put(c, freqMap.getOrDefault(c,0)+1);
        }

        // prepare groups
        List<Character>  evenGroup = new ArrayList<>();
        List<Character>  oddGroup = new ArrayList<>();

        // split into groups based on frequency
        for(char c: freqMap.keySet()){
            int f  = freqMap.get(c);
            if(f%2==0){
                evenGroup.add(c);
            }else
                oddGroup.add(c);
        }

        // Build encrypted initial String
        StringBuilder encrypted = new StringBuilder();

        // Append Group 1 (even frequency, one occurrence each)
        for(char c : evenGroup){
            encrypted.append(c);
        }

        // Append Group 2 (odd frequency, one occurrence each)
        for(char c : oddGroup){
            encrypted.append(c);
        }

        // append remaining occurences
        for(char c:freqMap.keySet()){
            int total = freqMap.get(c);
            for(int i=1;i<total;i++){
                encrypted.append(c);
            }
        }

        // count characters with single occurence
        int singleCount = 0;
        for(int f : freqMap.values()){
            if(f == 1) singleCount++;
        }

        // if 0 -> do not insert count
        if(singleCount > 0){
            StringBuilder finalEncrypted = new StringBuilder(encrypted);

            int len  = finalEncrypted.length();
            int insertPos;

            // center insertion rule
            if(len % 2 ==0){
                insertPos = len / 2 - 1;    // after left-middle
            }else{

            }
        }
        // PRINT RESULT
        System.out.println("Encrypted output: " + encrypted.toString());
    }
    
}
