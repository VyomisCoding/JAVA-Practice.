

import java.util.*;

// custom exception---------------------------------------------------
class SensorFailureException extends Exception{
    public SensorFailureException(String message){
        super(message);
    }
}

// temperature log class=-==============-------------------------------------------------
class TemperatureLog{
    private List<Double> dailyReadings;
    private static final double MIN_SAFE_TEMP = 2.0;
    private static final double MAX_SAFE_TEMP = 8.0;

    public TemperatureLog(){
        dailyReadings = new ArrayList<>();
    }

    // Add Reading----
    public void addReading(double temperature) throws SensorFailureException{
        if(temperature < -50 || temperature > 100){
            throw new SensorFailureException("Sensor Failure : Invalid temperature reading");
        }

        dailyReadings.add(temperature);

        if(temperature < MIN_SAFE_TEMP || temperature > MAX_SAFE_TEMP){
            System.out.println("Alert : Temperature out of state range");
        }
    }

    //calculate average
    public double calculateDailyAverage(){
        if(dailyReadings.isEmpty()){
            return 0;
        }

        double sum=0;
        for(double temp:dailyReadings){
            sum+=temp;
        }
        return sum/dailyReadings.size();
    }

    public List<Double> getDailyReadings(){
        return dailyReadings;
    }
}

// Service class-------------------------------------------------------
class WarehouseMonitor{
    private Map<String, TemperatureLog> roomMap;

    public WarehouseMonitor(){
        roomMap = new HashMap<>(); 
    }

    public void addRoom(String roomId){
        roomMap.putIfAbsent(roomId, new TemperatureLog());
    }

    public void addTemperature(String roomId, double temperature) throws SensorFailureException{
        TemperatureLog log = roomMap.get(roomId);

        if(log==null){
            System.out.println("Room not found");
            return;
        }

        log.addReading(temperature);
        System.out.println("Temperature recorded successfully");
    }

    public void showRoomAverage(String roomId){
        TemperatureLog log = roomMap.get(roomId);

        if(log == null){
            System.out.println("Room not found");
            return;
        }

        double avg = log.calculateDailyAverage();
        System.out.println("Daily Average Temperature " + avg);
    }

    public void viewAllRooms(){
        for(String roomId:roomMap.keySet()){
            System.out.println("Room: " + roomId + " | Readings: " + roomMap.get(roomId).getDailyReadings());
        }
    }
}

// MAIN CLASS ----------------------------------------------------------------------------------------------------------

public class WareHouseApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WarehouseMonitor monitor = new WarehouseMonitor();

        while (true){
            System.out.println("\n--Warehouse Temperature Monitor--");
            System.out.println("1. Add Room");
            System.out.println("2. Add Temperature Reading");
            System.out.println("3. View Room Average");
            System.out.println("4. View All Rooms");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter Room ID: ");
                    String roomId = sc.next();
                    monitor.addRoom(roomId);
                    System.out.println("Room added successfully.");
                    break;
                case 2:
                    try{
                        System.out.print("Enter Room ID: ");
                        String rId = sc.next();
                        System.out.print("Enter Temperature: ");
                        double temp = sc.nextDouble();
                        monitor.addTemperature(rId, temp);

                    }catch(SensorFailureException e){
                        System.out.println("No" + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter Room ID: ");
                    String id = sc.next();
                    monitor.showRoomAverage(id);
                    break;
                case 4:
                    monitor.viewAllRooms();
                    break;
                case 5:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
