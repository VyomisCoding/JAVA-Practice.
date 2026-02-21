
public class WaterTank{

    private String tankId;
    private double capacity;
    private double currentLevel;

    public WaterTank(String tankId, double capacity, double currentLevel) throws InvalidWaterLevelException{
        if(currentLevel > capacity){
            throw new InvalidWaterLevelException("Current level cannot exceed tank capacity");
        }

        this.tankId = tankId;
        this.capacity = capacity;
        this.currentLevel = currentLevel;
    }

    public String getTankId(){
        return tankId;
    }

    public double getCapacity(){
        return capacity;
    }

    public double getCurrentLevel(){
        return currentLevel;
    }
    
    // calculate usage percentage
    public double getUsagePercentage(){
        return (currentLevel/capacity)*100;
    }

    // Alert if below 20%
    public boolean isBelowThreshold(){
        return getUsagePercentage() < 20;
    }

    @Override
    public String toString(){
        return "Tank ID: " + tankId + ", Capacity: " + capacity + ", Current Level: " + currentLevel + ", Usage %: " + String.format("%.2f", getUsagePercentage());
    }
}
