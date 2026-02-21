import java.util.*;

public class Asset{
    private String assetId;
    private String investorName;
    private String assetType;
    private String sector;
    private double investedAmount;
    private double currentValue;
    private int investmentYear;
    private double riskScore;
    private double riskAdjustedReturn;
    private List<String> tags;

    public Asset(String assetId, String investorName, String assetType, String sector, double investedAmount, double currentValue, int investmentYear, double riskScore, List<String> tags){
        this.assetId = assetId;
        this.investorName = investorName;
        this.assetType = assetType;
        this.sector = sector;
        this.investedAmount = investedAmount;
        this.currentValue = currentValue;
        this.investmentYear = investmentYear;
        this.riskScore = riskScore;
        this.tags = tags != null ? tags : new ArrayList<>();
    }

    public String getAssetId() { return assetId; }
    public String getInvestorName() { return investorName; }
    public String getAssetType() { return assetType; }
    public String getSector() { return sector; }
    public double getInvestedAmount() { return investedAmount; }
    public double getCurrentValue() { return currentValue; }
    public int getInvestmentYear() { return investmentYear; }
    public double getRiskScore() { return riskScore; }
    public double getRiskAdjustedReturn() { return riskAdjustedReturn; }
    public List<String> getTags() { return tags; }

    public void setInvestedAmount(double investedAmount) { this.investedAmount = investedAmount; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
    public void setInvestmentYear(int investmentYear) { this.investmentYear = investmentYear; }
    public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
    public void setRiskAdjustedReturn(double riskAdjustedReturn) { this.riskAdjustedReturn = riskAdjustedReturn; }
    public void setTags(List<String> tags) { this.tags = tags; }

    @Override
    public String toString(){
        return assetId + "|" + investorName + "|" + assetType + "|" + sector + "| Invested = " + investedAmount + "| Current =" + currentValue + "| Year=" + investmentYear + "| Risk=" + riskScore + "| AdjRetur=" + riskAdjustedReturn + "| Tags=" + tags; 
    }
}