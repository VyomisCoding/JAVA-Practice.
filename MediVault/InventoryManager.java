import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;


public class InventoryManager{

    private List<Medicine> list = new ArrayList<>();

    public void addMedicine(Medicine m) { list.add(m); }

    // Resolve duplicates using toMap + BinaryOperator merge
    public void resolveDuplicates() {
        list = new ArrayList<>(list.stream().collect(Collectors.toMap(Medicine::getMedicineId,m -> m, MedicineUtil.mergeDuplicates())).values());
    }

    // Get Eligible medicines -> filter + compute prices + apply consumer + normalize demand
    public List<Medicine> getEligible(int expiryYear, double baseDiscount, Predicate<Medicine> extraFilter) throws NoEligibleMedicineException{
        List<Medicine> result = list.stream().filter(MedicineUtil.isExpiredIn(expiryYear)).filter(extraFilter).map(m -> { double price = MedicineUtil.computeAdjustedPrice().apply(m, baseDiscount);
            MedicineUtil.applyAdjustedPrice(price).accept(m);
            return MedicineUtil.normalizeDemand().apply(m);
        }).collect(Collectors.toList());

        if (result.isEmpty()) throw new NoEligibleMedicineException("No eligible medicines found!");

        return result; 
    }

    // Sort medicines
    public List<Medicine> getSorted(){
        return list.stream().sorted(MedicineUtil.sortByAdjustedPrice()).collect(Collectors.toList());
    }

    // Find minimum priced medicines
    public Stream<Medicine> getMinPriced(){
        OptionalDouble min = list.stream().mapToDouble(Medicine::getAdjustedPrice).min();

        if (!min.isPresent()) return Stream.empty();

        return list.stream().filter(m -> m.getAdjustedPrice() == min.getAsDouble());
    }

    // Category-wise stats
    public Map<String, DoubleSummaryStatistics> getCategoryStats(){
        return list.stream().collect(Collectors.groupingBy(Medicine::getCategory,Collectors.summarizingDouble(Medicine::getAdjustedPrice)));
    }

    // Partition by demand score
    public Map<Boolean, List<Medicine>> partitionHighDemand(double threshold) {
        return list.stream().collect(Collectors.partitioningBy(m -> m.getDemandScore() >= threshold));
    }

    // Top K expensive medicines
    public List<Medicine> topKByCategory(String cat, int k){
        return list.stream().filter(m -> m.getCategory().equalsIgnoreCase(cat)).sorted(Comparator.comparing(Medicine::getAdjustedPrice).reversed()).limit(k).collect(Collectors.toList());
    }

    // Collect unique tags
    public List<String> getAllTags(){
        return list.stream().flatMap(m -> m.getTags().stream()).distinct().collect(Collectors.toList());
    }

    // Weighted profit = Σ((adjustedPrice - costPrice) * demandScore)
    public double getTotalWeightedProfit(){
        return list.stream().map(m -> (m.getAdjustedPrice() - m.getCostPrice()) * m.getDemandScore()).reduce(0.0, Double::sum);
    }
}
