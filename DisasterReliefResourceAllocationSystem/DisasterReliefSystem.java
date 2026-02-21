
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class DisasterReliefSystem{
    private static Map<String, ReliefCenter> centers = new HashMap<>();
    private static Queue<AreaRequest> requestQueue = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);


    // Add Relief Center
    private static void addReliefCenter(){
        System.out.println("Enter Center ID: ");
        String id = sc.nextLine();

        centers.put(id,new ReliefCenter(id));
        System.out.println("Relief Center Added");
    }

    // Add Resources
    private static  void addResourceToCenter(){
        System.out.print("Enter Center Id");
        String id = sc.nextLine();
        
        ReliefCenter center = centers.get(id);

        if(center == null){
            System.out.println("Center not found");
            return;
        }

        System.out.println("Enter item (Food/Water/Medicalkit): ");
        String item = sc.nextLine();

        System.out.print("EnterQuantity");
        int quantity = sc.nextInt();
        sc.nextLine();

        center.addResource(item,quantity);
        System.out.println("Resource added Successfully");
    }

    // Add Area Request
    private static void addAreaRequest(){
        System.out.print("Enter Area Name: ");
        String area = sc.nextLine();

        System.out.print("Enter Item Needed: ");
        String item = sc.nextLine();

        System.out.print("Enter Quantity Needed: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        requestQueue.offer(new AreaRequest(area, item, quantity));
        System.out.println("Request Added to Queue!");
    }

    // Process Requests FIFO
    private static void processRequests(){
        if(requestQueue.isEmpty()){
            System.out.println("No requests in queue");
            return;
        }

        while(!requestQueue.isEmpty()){
            AreaRequest request = requestQueue.poll();

            boolean allocated = false;

            for(ReliefCenter center : centers.values()){
                try {
                    center.allocateResource(request.getItem(),request.getQuantity());
                    System.out.println("Allocated" + request.getQuantity() + " " + request.getItem() + " to " + request.getAreaName() + " from Center " + center.getCenterId());
                    allocated = true;
                    break;
                } catch (InsufficientResourceException  e){
                    // try next center
                }
            }
            if(!allocated){
                System.out.println("Failed to allocate " + request.getItem() + " to " + request.getAreaName() + " (Insufficient Stock)");
            }
        }
    }
    
    // Generate Report
    private static void generateReport(){
        System.out.println("\n--Resource Status Report--");
        for (ReliefCenter center : centers.values()){
            System.out.println("Center ID: " + center.getCenterId());
            for(Map.Entry<String, Integer> entry : center.getResources().entrySet()){
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    
    public static void main(String[] args){
        while (true){ 
            System.out.println("\n--Disaster Relief Resource Allocation System--");
            System.out.println("1. Add Relief Center");
            System.out.println("2. Add Resource to Center");
            System.out.println("3. Add Area Request");
            System.out.println("4. Process Requests (FIFO)");
            System.out.println("5. Generate Resource Report");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    addReliefCenter();
                    break;
                case 2:
                    addResourceToCenter();
                    break;
                case 3:
                    addAreaRequest();
                    break;
                case 4:
                    processRequests();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            } 
        }
    }
}
