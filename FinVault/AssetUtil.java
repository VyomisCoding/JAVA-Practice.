import java.util.*;
import java.util.function.*;
import java.util.stream.*;


public class AssetUtil {
    public static Asset parseAsset(String input) throws InvalidAssetException{
        try {
            String[] parts = input.split(":");

            if(parts.length < 8) throw new InvalidAssetException("Invalid format: " + input);

            List<String> tags = new ArrayList<>();
            if(parts.length == 9){
                tags = Arrays.stream(parts[8].split(",")).map(String::trim).distinct().collect(Collectors.toList());
            }

            return new Asset(parts[0],parts[1],parts[2],parts[3],Double.parseDouble(parts[4]),Double.parseDouble(parts[5]),Integer.parseInt(parts[6]),Double.parseDouble(parts[7]),tags);

        }catch(Exception e){
            throw new InvalidAssetException("Invalid format:"+input);
        }
    }

    public static Predicate<Asset> investedBefore(int year){
        return a -> a.getInvestmentYear() < year;
    }

    public static Function<String,Double> sectorRiskAdjustment(){
        return s -> {switch(s.toUpperCase()){
            case "TECH": return 1.2;
            case "ENERGY": return 0.8;
            case "FINANCE": return 0.5;
            case "HEALTH": return 0.3;
            default: return 0.2;
        }};
    }

    public static BiFunction<Asset, Double, Double> computeRiskAdjustedReturn(){
        return (asset,baseRisk) -> { 

            double returnPercent = ((asset.getCurrentValue() - asset.getInvestedAmount()) / asset.getInvestedAmount()) * 100;
            double sectorAdj = sectorRiskAdjustment().apply(asset.getSector());
            double penalty = (asset.getRiskScore() + sectorAdj) * baseRisk / 100;

            return returnPercent - penalty;
        };
    }

    public static Consumer<Asset> applyRiskAdjustedReturn(double value){
        return a->a.setRiskAdjustedReturn(value);
    }

    public static BinaryOperator<Asset> mergeDuplicateAssets(){
        return(a1,a2) -> {
            a1.setInvestedAmount(a1.getInvestedAmount() + a2.getInvestedAmount());
            a1.setCurrentValue(a1.getCurrentValue() + a2.getCurrentValue());
            a1.setInvestmentYear(Math.min(a1.getInvestmentYear(),a2.getInvestmentYear()));
            a1.setRiskScore(Math.max(a1.getRiskScore(), a2.getRiskScore()));

            Set<String> tagSet = new HashSet<>(a1.getTags());
            tagSet.addAll(a2.getTags());
            a1.setTags(new ArrayList<>(tagSet));

            return a1;
        };
    }

    public static UnaryOperator<Asset> normalizeRisk(){
        return a->{
            if(a.getRiskScore() > 10)
                a.setRiskScore(10);

            return a;
        };
    }
    
}
