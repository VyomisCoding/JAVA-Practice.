import java.util.*;

public class UserInterface{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        try {
            // Input Experiment 1
            System.out.println("Enter Experiment 1 details:");
            int id1 = Integer.parseInt(sc.nextLine());
            String title1 = sc.nextLine();
            String researcher1 = sc.nextLine();
            double funding1 = Double.parseDouble(sc.nextLine());
            String level1 = sc.nextLine();  // case-sensitive
            int month1 = Integer.parseInt(sc.nextLine());
            int year1 = Integer.parseInt(sc.nextLine());

            ExperimentInfo exp1 = new ExperimentInfo(id1, title1, researcher1, funding1, level1, month1, year1);
            
            // INPUT FOR EXPERIMENT 2
            System.out.println("Enter Experiment 2 details:");
            int id2 = Integer.parseInt(sc.nextLine());
            String title2 = sc.nextLine();
            String researcher2 = sc.nextLine();
            double funding2 = Double.parseDouble(sc.nextLine());
            String level2 = sc.nextLine();  // case-sensitive
            int month2 = Integer.parseInt(sc.nextLine());
            int year2 = Integer.parseInt(sc.nextLine());

            ExperimentInfo exp2 = new ExperimentInfo(id2, title2, researcher2, funding2, level2, month2, year2);

            // CALCULATING MONTHS ACTIVE
            int months1 = Experiment.getMonthsActive(month1, year1);
            int months2 = Experiment.getMonthsActive(month2, year2);

            // CALCULATE INNOVATION SCORE + RESOURCE CONSUMPTION
            double innov1 = exp1.calculateInnovationScore(months1);
            double innov2 = exp2.calculateInnovationScore(months2);

            double resource1 = exp1.calculateResourceConsumption(months1);
            double resource2 = exp2.calculateResourceConsumption(months2);

            // FIND HIGHER IMPACT (BY FUNDING)
            Experiment higher = exp1.getHigherImpactExperiment(exp1, exp2);
            ExperimentInfo high = (ExperimentInfo) higher;

            // FINAL OUTPUT
            System.out.println("Experiment Summary:");
            System.out.println(exp1.getExperimentTitle() + " by " + exp1.getLeadResearcher());
            System.out.println("Months Active: " + months1);
            System.out.println("Innovation Score: " + innov1);
            System.out.println("Resource Consumption: " + resource1);
            System.out.println(exp2.getExperimentTitle() + " by " + exp2.getLeadResearcher());
            System.out.println("Months Active: " + months2);
            System.out.println("Innovation Score: " + innov2);
            System.out.println("Resource Consumption: " + resource2);
            System.out.println("Higher Impact Experiment: " + high.getExperimentTitle());


        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
