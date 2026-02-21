
public class ExperimentInfo implements Experiment{

    private int experimentId;
    private String experimentTitle;
    private String leadResearcher;
    private double fundingAmount;
    private String complexityLevel;
    private int initiationMonth;
    private int initiationYear;

    public ExperimentInfo(int experimentId, String experimentTitle, String leadResearcher, double fundingAmount, String complexityLevel, int initiationMonth, int initiationYear){
        this.experimentId = experimentId;
        this.experimentTitle = experimentTitle;
        this.leadResearcher = leadResearcher;
        this.fundingAmount = fundingAmount;
        this.complexityLevel = complexityLevel;
        this.initiationMonth = initiationMonth;
        this.initiationYear = initiationYear;
    }


    // GETTERS
    public String getExperimentTitle() { return experimentTitle; }
    public String getLeadResearcher() { return leadResearcher; }
    public double getFundingAmount() { return fundingAmount; }
    public String getComplexityLevel() { return complexityLevel; }
    public int getInitiationMonth() { return initiationMonth; }
    public int getInitiationYear() { return initiationYear; }


    // Innovation Score Logic 
    @Override
    public double calculateInnovationScore(int monthsActive) {
        double multiplier = 0;
        switch(complexityLevel){
            case "QubitOpt":      multiplier = 1.5; break;
            case "Entanglement":  multiplier = 2.5; break;
            case "Cryptography":  multiplier = 3.5; break;
        }
        return (multiplier * monthsActive * fundingAmount) / 1000;
    }

    // Resource consumption logic
    @Override
    public double calculateResourceConsumption(int monthsActive) {
        double rate = 0;
        switch(complexityLevel){
            case "QubitOpt":      rate = 0.8; break;
            case "Entanglement":  rate = 1.2; break;
            case "Cryptography":  rate = 1.8; break;
        }
        double result = (monthsActive * rate * fundingAmount) / 100;
        return Math.round(result * 10) / 10.0;       // Round to 1 decimal
    }
    
}
