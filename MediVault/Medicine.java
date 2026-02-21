
import java.util.ArrayList;
import java.util.List;

public class Medicine{
    private String medicineId;
    private String name;
    private String category;
    private double mrp;
    private double costPrice;
    private int manufactureYear;
    private int expiryYear;
    private double demandScore;
    private List<String> tags;
    private double adjustedPrice;    // computed later
    
    public Medicine(String medicineId, String name, String category, double mrp, double costPrice, int manufactureYear, int expiryYear, double demandScore, List<String> tags){
        this.medicineId = medicineId;
        this.name = name;
        this.category = category;
        this.mrp = mrp;
        this.costPrice = costPrice;
        this.manufactureYear = manufactureYear;
        this.expiryYear = expiryYear;
        this.demandScore = demandScore;
        this.tags = tags == null ? new ArrayList<>() : tags;
    }

    // Getters & Setters
    public String getMedicineId() { return medicineId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getMrp() { return mrp; }
    public double getCostPrice() { return costPrice; }
    public int getManufactureYear() { return manufactureYear; }
    public int getExpiryYear() { return expiryYear; }
    public double getDemandScore() { return demandScore; }
    public double getAdjustedPrice() { return adjustedPrice; }
    public List<String> getTags() { return tags; }

    public void setMrp(double mrp) { this.mrp = mrp;}
    public void setCostPrice(double costPrice) { this.costPrice = costPrice;}
    public void setDemandScore(double demandScore) { this.demandScore = demandScore; }
    public void setAdjustedPrice(double adjustedPrice) { this.adjustedPrice = adjustedPrice; }
    public void setTags(List<String> tags) { this.tags = tags; }

    @Override
    public String toString() {
        return medicineId + " | " + name + " | " + category + " | MRP=" + mrp + " | Cost=" + costPrice + " | MYear=" + manufactureYear + " | EYear=" + expiryYear + " | Demand=" + demandScore + " | Adjusted Price=" + adjustedPrice + " | Tags=" + tags;
    }
}


