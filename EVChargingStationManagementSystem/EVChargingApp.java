

import java.util.*;

// custom exception
class NoChargingSlotAvailableException extends Exception{
    public NoChargingSlotAvailableException(String msg){
        super(msg);
    }
}

// Pricing strategy (interface)-----------------------------------------------------------------
interface PricingStrategy{
    double calculatePrice(double units);
}

// Normal pricing strategy
class NormalPricing implements PricingStrategy{
    @Override
    public double calculatePrice(double units){
        return units * 10; // ₹10 per unit
    }
}

// Peak hour pricing (polymorphism)
class PeakHourPricing implements PricingStrategy{
    @Override
    public double calculatePrice(double units){
        return units * 15; // ₹15 per unit
    }
}

// Vehicle class -------------------------------------------------------------------------------
class Vehicle {
    private String vehicleNumber;
    private double unitsRequired;

    public Vehicle(String vehicleNumber, double unitsRequired){
        this.vehicleNumber = vehicleNumber;
        this.unitsRequired = unitsRequired;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public double getUnitsRequired() { return unitsRequired; }

    @Override
    public String toString() {
        return vehicleNumber + " | Units: " + unitsRequired;
    }
}

// Charging slot class ---------------------------------------------------------------------------------

class ChargingSlot {
    private int slotId;
    private boolean occupied;

    public ChargingSlot(int slotId) {
        this.slotId = slotId;
        this.occupied = false;
    }

    public int getSlotId() { return slotId; }
    public boolean isOccupied() { return occupied; }

    public void occupy() { occupied = true; }
    public void free() { occupied = false; }

    @Override
    public String toString() {
        return "Slot " + slotId + " | Occupied: " + occupied;
    }
}

// SERVICE CLASS --------------------------------------------------------------------------------------------------
class ChargingStationSystem{
    private Map<Integer, ChargingSlot> slotMap = new HashMap<>();
    private Queue<Vehicle> waitingQueue = new LinkedList<>();

    // Add a new slot
    public void addChargingSlot(int slotId){
        slotMap.put(slotId, new ChargingSlot(slotId));
    }

    // Add vehicle to queue
    public void addVehicleToQueue(Vehicle v){
        waitingQueue.add(v);
    }

    // Allocate slot and calculate bill
    public void allocateSlot(PricingStrategy strategy)
            throws NoChargingSlotAvailableException {

        if (waitingQueue.isEmpty()) {
            System.out.println("No vehicles in queue.");
            return;
        }

        Vehicle vehicle = waitingQueue.poll(); // Get next vehicle

        ChargingSlot freeSlot = null;

        // Find nearest free slot
        for (ChargingSlot slot : slotMap.values()) {
            if (!slot.isOccupied()) {
                freeSlot = slot;
                break;
            }
        }

        // If no slot found
        if (freeSlot == null) {
            throw new NoChargingSlotAvailableException("No free charging slots available!");
        }

        freeSlot.occupy();

        // Calculate bill using polymorphism
        double bill = strategy.calculatePrice(vehicle.getUnitsRequired());

        System.out.println("\nVehicle " + vehicle.getVehicleNumber() + " assigned to Slot " + freeSlot.getSlotId());
        System.out.println("Bill Amount: ₹" + bill);
    }

    // Free a slot
    public void freeSlot(int slotId) {
        ChargingSlot slot = slotMap.get(slotId);
        if (slot != null && slot.isOccupied()) {
            slot.free();
            System.out.println("Slot " + slotId + " is now free.");
        } else {
            System.out.println("Invalid or already free slot.");
        }
    }

    // Show all slots
    public void showSlots() {
        slotMap.values().forEach(System.out::println);
    }

    // Show waiting queue
    public void showWaitingQueue() {
        System.out.println(waitingQueue);
    }
}

// MAIN CLASS -------------------------------------------------------------------------------------------------------------
public class EVChargingApp {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ChargingStationSystem system = new ChargingStationSystem();

        while (true) {

            System.out.println("\n===== EV CHARGING STATION SYSTEM =====");
            System.out.println("1. Add Charging Slot");
            System.out.println("2. Add Vehicle to Queue");
            System.out.println("3. Allocate Slot (Normal Pricing)");
            System.out.println("4. Allocate Slot (Peak Hour Pricing)");
            System.out.println("5. Free a Slot");
            System.out.println("6. Show Slots");
            System.out.println("7. Show Waiting Queue");
            System.out.println("8. Exit");
            System.out.print("Choose Option: ");

            // Parsing string → int using parseInt()
            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter Slot ID: ");
                        int sid = Integer.parseInt(sc.nextLine());
                        system.addChargingSlot(sid);
                        System.out.println("Slot added.");
                        break;

                    case 2:
                        System.out.print("Enter Vehicle Number: ");
                        String vNum = sc.nextLine();

                        System.out.print("Enter Units Required: ");
                        // parseDouble for units
                        double units = Double.parseDouble(sc.nextLine());

                        system.addVehicleToQueue(new Vehicle(vNum, units));
                        System.out.println("Vehicle added to queue.");
                        break;

                    case 3:
                        // NORMAL PRICING
                        system.allocateSlot(new NormalPricing());
                        break;

                    case 4:
                        // PEAK HOUR PRICING
                        system.allocateSlot(new PeakHourPricing());
                        break;

                    case 5:
                        System.out.print("Enter Slot ID to free: ");
                        int freeId = Integer.parseInt(sc.nextLine());
                        system.freeSlot(freeId);
                        break;

                    case 6:
                        system.showSlots();
                        break;

                    case 7:
                        system.showWaitingQueue();
                        break;

                    case 8:
                        System.out.println("EXITING...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (NoChargingSlotAvailableException e) {
                System.out.println("the ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input!");
            }
        }
    }
}
