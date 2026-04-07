package M1.SET3;

import java.util.HashMap;
import java.util.Scanner;

public class EventPlannerFestivalManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String,String[]> map = new HashMap<>();
        while (true) { 
            String line = sc.nextLine();
            if(line.equals("EXIT"))
                break;

            String[] parts = line.split(" ");

            if(parts[0].equals("ADD_FESTIVAL")){
                String type = parts[1];
                String name = parts[2];

                map.put(name, parts);
            }
            else if(parts[0].equals("DISPLAY_DETAILS")) {

                String name = parts[1];

                if(!map.containsKey(name)) {
                    System.out.println("Festival Not Found");
                    continue;
                }

                String[] p = map.get(name);

                String type = p[1];

                System.out.println("Festival Name: " + p[2]);
                System.out.println("Location: " + p[3]);
                System.out.println("Date: " + p[4]);

                if(type.equals("MUSIC")) {

                    System.out.println("Headliner: " + p[5]);
                    System.out.println("Music Genre: " + p[6]);
                    System.out.println("Ticket Price: " + p[7]);
                }

                else if(type.equals("FOOD")) {

                    System.out.println("Cuisine: " + p[5]);
                    System.out.println("Number of Stalls: " + p[6]);
                    System.out.println("Entry Fee: " + p[7]);
                }

                else if(type.equals("ART")) {

                    System.out.println("Art Type: " + p[5]);
                    System.out.println("Number of Artists: " + p[6]);
                    System.out.println("Exhibition Fee: " + p[7]);
                }
            }
        }
    }
}
