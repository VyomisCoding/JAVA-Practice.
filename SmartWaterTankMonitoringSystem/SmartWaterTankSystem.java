
import java.util.*;

public class SmartWaterTankSystem{
    private static List<WaterTank> tanks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        while (true){
            System.out.println("\n--- Smart Water Tank Monitoring System ---");
            System.out.println("1. Add Water Tank");
            System.out.println("2. Display All Tanks");
            System.out.println("3. Sort Tanks by Lowest Water Level");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    addTank();
                    break;
                case 2:
                    displayTanks();
                    break;
                case 3:
                    sortTanksByLowestLevel();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Add tank
    private static void addTank(){
        try{
            System.out.print("Enter Tank ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Capacity: ");
            double capacity = sc.nextDouble();
            System.out.print("Enter Current Level: ");
            double level = sc.nextDouble();
            sc.nextLine();
            WaterTank tank = new WaterTank(id, capacity, level);
            tanks.add(tank);
            System.out.println("Tank added successfully!");
        }catch(InvalidWaterLevelException e){
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

    // Display tanks + Alert check
    private static void displayTanks(){
        if(tanks.isEmpty()){
            System.out.println("No tanks available.");
            return;
        }
        for(WaterTank tank : tanks){
            System.out.println(tank);
            if(tank.isBelowThreshold()){
                System.out.println("Alert: Tank " + tank.getTankId() + " below 20%!");
            }
        }
    }

    // Sort tanks by lowest current level
    private static void sortTanksByLowestLevel(){
        if(tanks.isEmpty()){ 
            System.out.println("No tanks to sort.");
            return;
        }
        Collections.sort(tanks, Comparator.comparing(WaterTank::getCurrentLevel));
        System.out.println("Tanks sorted by lowest water level:");
        displayTanks();
    }
}
