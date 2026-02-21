
import java.util.*;
// custom exception -------------------------------------------------------------------------------------

class InvalidVehicleException extends Exception{
    public InvalidVehicleException(String msg){
        super(msg);
    }
}

// polymorpishm - Base Class---------------------------------------------------------------------------------------------------

abstract class Violation{
    protected String type;
    protected double baseFine;
    protected String date;

    public Violation(String type,double baseFine, String date){
        this.type = type;
        this.baseFine = baseFine;
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    // polymorphic fine calculation
    public abstract double calculateFine(int repeatCount);

    @Override
    public String toString(){
        return type + "| Fine: " + baseFine + "| Date: " + date;
    }
}

// Child Classes - Different fine rules
class SpeedViolation extends Violation{
    public SpeedViolation(String date){
        super("Speed violation",800,date);
    }

    @Override
    public double calculateFine(int repeatCount){
        return baseFine + (repeatCount * 200);
    }
}

class SignalJumpViolation extends Violation{
    public SignalJumpViolation(String date){
        super("Signal Jump",1200,date);
    }

    @Override
    public double calculateFine(int repeatCount){
        return baseFine + (repeatCount * 300);
    }
}

class WrongParkingViolation extends Violation{
    public WrongParkingViolation(String date){
        super("Wrong Parking",500,date);
    }

    @Override
    public double calculateFine(int repeatCount){
        return baseFine+(repeatCount * 100);
    }
}

// Service class ---------------------------------------------------------------------------------------------------------------------------------------------

class TrafficSystem{
    // vehicle -> list of violations
    private Map<String, List<Violation>> violationMap = new HashMap<>();

    // Add violation
    public void recordViolation(String vehicle, Violation violation) throws InvalidVehicleException{
        if(!vehicle.matches("[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}")){
            throw new InvalidVehicleException("Invalid vehicle number format");
        }

        violationMap.putIfAbsent(vehicle, new ArrayList<>());
        List<Violation> list = violationMap.get(vehicle);
        int repeatCount = list.size();    // repeat offenders

        double fine = violation.calculateFine(repeatCount);
        list.add(violation);
        System.out.println("Violation Recorded for " + vehicle + " | Fine = ₹" + fine);
    }

    // Monthly Report
    public void generateMonthlyReport(String month){
        System.out.println("Monthly report for"+month);
        violationMap.forEach((vehicle, violations) -> {
            double total = violations.stream().filter(v -> v.getDate().equals(month)).mapToDouble(v -> v.calculateFine(0)).sum();

            if(total > 0){
                System.out.println(vehicle + " | Total Fine: ₹" + total);
            }
        });
    }

    // view all recorde
    public void viewAll(){
        violationMap.forEach((vehicle,list) -> {
            System.out.println("\nVehicle:"+vehicle);
            list.forEach(System.out::println);
        });
    }
}

// MAIN CLASS --------------------------------------------------------------------------------------------------------------------------------------------------

public class SmartTrafficSystemApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrafficSystem system = new TrafficSystem();

        while (true) {
            System.out.println("\n===== Smart Traffic Fine System =====");
            System.out.println("1. Record Violation");
            System.out.println("2. View All Violations");
            System.out.println("3. Monthly Report");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch(choice){
                    case 1:
                        System.out.print("Enter Vehicle Number (e.g. UP25AB1234): ");
                        String vehicle = sc.nextLine();

                        System.out.println("Violation Type:");
                        System.out.println("1. Speed");
                        System.out.println("2. Signal Jump");
                        System.out.println("3. Wrong Parking");
                        int v = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Month (e.g. 2026-02): ");
                        String month = sc.nextLine();

                        Violation violation;

                        switch(v){
                            case 1: violation = new SpeedViolation(month); break;
                            case 2: violation = new SignalJumpViolation(month); break;
                            case 3: violation = new WrongParkingViolation(month); break;
                            default: 
                                System.out.println("Invalid Type");
                                continue;
                        }
                        system.recordViolation(vehicle, violation);
                        break;

                    case 2:
                        system.viewAll();
                        break;

                    case 3:
                        System.out.print("Enter Month (YYYY-MM): ");
                        String m = sc.nextLine();
                        system.generateMonthlyReport(m);
                        break;

                    case 4:
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InvalidVehicleException e) {
                System.out.println("The ERROR: " + e.getMessage());
            }
        }
    }
}
