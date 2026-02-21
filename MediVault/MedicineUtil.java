
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class MedicineUtil{

    public static Medicine parseMedicine(String input) throws InvalidMedicineException{
        try {
            String[] p = input.split(":");
            if(p.length < 8) throw new InvalidMedicineException("Invalid format: "+ input);

            List<String> tags = new ArrayList<>();

            if(p.length == 9){
                tags = Arrays.stream(p[8].split(",")).map(String::trim).distinct().collect(Collectors.toList());
            }

            return new Medicine(
                p[0], p[1], p[2],
                Double.parseDouble(p[3]),
                Double.parseDouble(p[4]),
                Integer.parseInt(p[5]),
                Integer.parseInt(p[6]),
                Double.parseDouble(p[7]),
                tags
                );
        } catch (Exception e) {
            throw new InvalidMedicineException("Invalid format: " + input);
        }
    }

    // Predicate -> filter medicines expiring before given year
    public static Predicate<Medicine> isExpiredIn(int year){
        return m -> m.getExpiryYear() <= year;
    }

    // function -> category based discount
    public static Function<String, Double> categoryDiscount(){
        return cat -> {
            switch(cat.toUpperCase()){
                case "TABLET": return 5.0;
                case "SYRUP": return 7.0;
                case "INJECTION": return 3.0;
                default: return 2.0;
            }
        };
    }

    // BiFunction -> Calculate adjusted selling price
    public static BiFunction<Medicine, Double, Double> computeAdjustedPrice() {
        return (m, base) -> {
            double discount = base + categoryDiscount().apply(m.getCategory());
            int age = 2026 - m.getManufactureYear();                 // Age-based discount
            if (age > 3) discount += 2.0;
            
            return m.getMrp() - (m.getMrp() * discount / 100.0);     // final price after discount
        };
    }

    // Consumer -> Apply adjusted price to Medicine object
    public static Consumer<Medicine> applyAdjustedPrice(double price) {
        return m -> m.setAdjustedPrice(price);
    }

    // Normalize demandScore if > 10
    public static UnaryOperator<Medicine> normalizeDemand() {
        return m -> {
            if (m.getDemandScore() > 10) m.setDemandScore(10);
            return m;
        };
    }

    // Merge duplicates 
    public static BinaryOperator<Medicine> mergeDuplicates(){
        return(m1,m2) -> {
            m1.setMrp(m1.getMrp() + m2.getMrp());
            m1.setCostPrice(m1.getCostPrice() + m2.getCostPrice());
            m1.setDemandScore(Math.max(m1.getDemandScore(), m2.getDemandScore()));

            Set<String> merged = new HashSet<>(m1.getTags());
            merged.addAll(m2.getTags());
            m1.setTags(new ArrayList<>(merged));

            return m1;
        };
    }

    // Comparator → Sort by Adjusted Price → Name → Category
    public static Comparator<Medicine> sortByAdjustedPrice() {
        return Comparator.comparing(Medicine::getAdjustedPrice).thenComparing(Medicine::getName).thenComparing(Medicine::getCategory);
    }
}
