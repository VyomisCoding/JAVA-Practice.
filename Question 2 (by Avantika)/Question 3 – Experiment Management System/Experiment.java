

import java.time.LocalDate;

public interface Experiment{
    // Abstract method 1 -> innovation score
    public double calculateInnovationScore(int monthsActive);
    
    // Abstract method 2 -> Resource Consumption
    public double calculateResourceConsumption(int monthsActive);

    // Default method -> compare funding amount
    public default Experiment getHigherImpactExperiment(Experiment e1, Experiment e2){
        // Casting because we need fundingAmount
        ExperimentInfo exp1 = (ExperimentInfo) e1;
        ExperimentInfo exp2 = (ExperimentInfo) e2;

        return (exp1.getFundingAmount() >= exp2.getFundingAmount()) ? exp1 : exp2;
    }

    // static method -> Months Active
    public static int getMonthsActive(int initiationMonth, int initiationYear){
        LocalDate now = LocalDate.now();

        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        return ((currentYear - initiationYear)*12) + (currentMonth - initiationMonth);
    }
    
}
