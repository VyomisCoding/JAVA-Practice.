
import java.util.*;

//custom exception
class PlatformUnavailableException extends Exception{
    public PlatformUnavailableException(String msg){
        super(msg);
    }
}

//  Train class + Comparable(sort by arrival time)
class Train implements Comparable<Train>{
    private int trainId;
    private String name;
    private int arrivalTime;

    public Train(int trainId, String name, int arrivalTime){
        this.trainId = trainId;
        this.name = name;
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public int getTrainId(){
        return trainId;
    }

    @Override
    public int compareTo(Train other){
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }

    @Override
    public String toString(){
        return "Train ID: " + trainId + ", Name: " + name +", Arrival: " + arrivalTime; 
    }
}

// PLATFORM CLASS------------------------------------------------------------------------------------

class Platform{
    private int platformId;
    private boolean isOccupied;

    public Platform(int platformId){
        this.platformId = platformId;
        this.isOccupied = false;
    }

    public int getPlatformId(){
        return platformId;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void occupy(){
        isOccupied = true;
    }

    public void free(){
        isOccupied = false;
    }

    @Override
    public String toString(){
        return "Platform " + platformId + " | Occupied: " + isOccupied;
    }
}

// SERVICE CLASS-----------------------------------------------------------------------------------------------------------------

class PlatformAllocator{
    private Map<Integer, Platform> platformMap = new HashMap<>();
    private PriorityQueue<Train> incomingTrains = new PriorityQueue<>();

    public void addPlatform(int id){
        platformMap.put(id, new Platform(id));
    }

    public void addIncomingTrain(Train train){
        incomingTrains.add(train);
    }

    // Allocate nearest available platform
    public void allocatePlatform() throws PlatformUnavailableException{
        if(incomingTrains.isEmpty()){
            System.out.println("No incoming trains");
            return;
        }

        Train train = incomingTrains.poll();   // earliest arrival

        Platform assigned = null;

        // Find nearest free platform (lowest number)
        for(int pid : platformMap.keySet()){
            Platform p = platformMap.get(pid);

            if (!p.isOccupied()) {
                assigned = p;
                break;
            }
        }

        if(assigned == null){
            throw new PlatformUnavailableException("No free platforms available!");
        }

        assigned.occupy();

        System.out.println("🚆 Train allocated successfully:");
        System.out.println(train);
        System.out.println("Assigned Platform: " + assigned.getPlatformId());
    }

    public void freePlatform(int platformId){
        Platform p = platformMap.get(platformId);
        if(p != null){
            p.free();
            System.out.println("Platform " + platformId + " is now free.");
        }else{
            System.out.println("Invalid platform.");
        }
    }

    public void viewPlatforms(){
        for(Platform p : platformMap.values()){
            System.out.println(p);
        }
    }

    public void viewIncomingTrains(){
        if(incomingTrains.isEmpty()){
            System.out.println("No incoming trains.");
        }else{
            System.out.println(incomingTrains);
        }
    }
}

public class RailwayApp{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PlatformAllocator allocator = new PlatformAllocator();
        while(true){
            System.out.println("\n--Railway Platform Allocation--");
            System.out.println("1. Add Platform");
            System.out.println("2. Add Incoming Train");
            System.out.println("3. Allocate Platform");
            System.out.println("4. Free Platform");
            System.out.println("5. View Platforms");
            System.out.println("6. View Incoming Trains");
            System.out.println("7. Exit");

            System.out.print("Enter option: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter Platform ID: ");
                    allocator.addPlatform(sc.nextInt());
                    System.out.println("Platform added.");
                    break;
                case 2:
                    System.out.print("Enter Train ID: ");
                    int tid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Train Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Arrival Time (HHMM): ");
                    int time = sc.nextInt();
                    allocator.addIncomingTrain(new Train(tid, name, time));
                    System.out.println("Train added.");
                    break;
                case 3:
                    try{
                        allocator.allocatePlatform();
                    }catch(PlatformUnavailableException e){
                        System.out.println("NO " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter Platform ID to free: ");
                    allocator.freePlatform(sc.nextInt());
                    break;
                case 5:
                    allocator.viewPlatforms();
                    break;
                case 6:
                    allocator.viewIncomingTrains();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
