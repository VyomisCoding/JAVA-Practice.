package M1.SET1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VersionControlledStorageSystem{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n= Integer.parseInt(sc.nextLine());
        
        Map<String,List<String[]>> map = new HashMap<>();

        for(int i=0;i<n;i++){
            String[] p = sc.nextLine().split(" ");
            String cmd = p[0];

            if(cmd.equals("UPLOAD")){
                String file = p[1];
                String version = p[2];
                String size = p[3];

                map.putIfAbsent(file,new ArrayList<>());
                boolean exists = false;

                for(String[] v: map.get(file)){
                    if(v[0].equals(version))
                        exists=true;
                }
                if(!exists)
                    map.get(file).add(new String[]{version,size});
            }

            else if(cmd.equals("FETCH")){

                String file=p[1];

                if(!map.containsKey(file)){
                    System.out.println("File Not Found");
                    continue;
                }

                List<String[]> list=new ArrayList<>(map.get(file));

                list.sort((a,b)->{
                    int s1=Integer.parseInt(a[1]);
                    int s2=Integer.parseInt(b[1]);

                    if(s1!=s2)
                        return s1-s2;
                    return a[0].compareTo(b[0]);
                });

                for(String[] v:list)
                    System.out.println(file+" "+v[0]+" "+v[1]);
            }

            else if(cmd.equals("LATEST")){

                String file=p[1];

                if(!map.containsKey(file)){
                    System.out.println("File Not Found");
                    continue;
                }

                List<String[]> list=map.get(file);
                String[] v=list.get(list.size()-1);

                System.out.println(file+" "+v[0]+" "+v[1]);
            }

            else if(cmd.equals("TOTAL_STORAGE")){

                String file=p[1];

                if(!map.containsKey(file)){
                    System.out.println("File Not Found");
                    continue;
                }

                int sum=0;

                for(String[] v:map.get(file))
                    sum+=Integer.parseInt(v[1]);

                System.out.println(file+" "+sum);
            }
        }
    }
}
