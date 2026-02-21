import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        try {
            System.out.println("Enter number of medicines:");
            int n = Integer.parseInt(sc.nextLine());

            // Parse all medicines
            for (int i = 0; i < n; i++) {
                try {
                    Medicine m = MedicineUtil.parseMedicine(sc.nextLine());
                    manager.addMedicine(m);
                } catch (InvalidMedicineException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Remove duplicates
            manager.resolveDuplicates();

            System.out.println("Enter expiry filter year:");
            int expYear = Integer.parseInt(sc.nextLine());

            System.out.println("Enter base discount:");
            double baseDisc = Double.parseDouble(sc.nextLine());

            System.out.println("Enter demand threshold:");
            double demand = Double.parseDouble(sc.nextLine());

            System.out.println("Enter category and K (e.g. TABLET 2):");
            String[] s = sc.nextLine().split(" ");
            String category = s[0];
            int k = Integer.parseInt(s[1]);

            // Extra filter based on demand score
            Predicate<Medicine> extra = m -> m.getDemandScore() >= demand;

            // Get eligible
            try {
                System.out.println("\nEligible Medicines:");
                manager.getEligible(expYear, baseDisc, extra).forEach(System.out::println);
            } catch (NoEligibleMedicineException e) {
                System.out.println(e.getMessage());
            }

            // Sorted
            System.out.println("\nSorted Medicines:");
            manager.getSorted().forEach(System.out::println);

            // Minimum priced
            System.out.println("\nMin Priced Medicines:");
            manager.getMinPriced().forEach(System.out::println);

            // Category stats
            System.out.println("\nCategory Summary:");
            manager.getCategoryStats().forEach((key, val) -> System.out.println(key + " -> " + val));

            // Partition
            System.out.println("\nPartition by Demand:");
            Map<Boolean, List<Medicine>> part = manager.partitionHighDemand(demand);

            System.out.println("High Demand: " + part.get(true).stream().map(Medicine::getMedicineId).collect(Collectors.toList()));

            System.out.println("Low Demand: " + part.get(false).stream().map(Medicine::getMedicineId).collect(Collectors.toList()));

            // Top K
            System.out.println("\nTop " + k + " in " + category);
            manager.topKByCategory(category, k).forEach(System.out::println);

            // Weighted Profit
            System.out.println("\nTotal Weighted Profit: " + manager.getTotalWeightedProfit());

            // Tags
            System.out.println("\nAll Unique Tags: " + manager.getAllTags());

        }catch(Exception e){
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}