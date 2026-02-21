import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class PortfolioManager{

    private List<Asset> assetList = new ArrayList<>();

    public void addAsset(Asset asset){
        assetList.add(asset);
    } 

    public void resolveDuplicatesById(){
        assetList = new ArrayList<>(
            assetList.stream().collect(Collectors.toMap(Asset::getAssetId,Function.identity(),AssetUtil.mergeDuplicateAssets())).values()
        );
    }

    public List<Asset> getEligibleAssets(int year, double baseRisk, Predicate<Asset> extraFilter) throws NoEligibleAssetException{
        List<Asset> result = assetList.stream().filter(AssetUtil.investedBefore(year)).filter(extraFilter).map(a->{
            double val = AssetUtil.computeRiskAdjustedReturn().apply(a,baseRisk);
            AssetUtil.applyRiskAdjustedReturn(val).accept(a);
            return AssetUtil.normalizeRisk().apply(a);
        }).collect(Collectors.toList());

        if(result.isEmpty()) throw new NoEligibleAssetException("No eligible assets found");

        return result;
    }

    public List<Asset> getSortedAssets(){
        return assetList.stream().sorted(Comparator.comparing(Asset::getRiskAdjustedReturn).thenComparing(Asset::getInvestorName).thenComparing(Asset::getSector)).collect(Collectors.toList());
    }

    public Stream<Asset> getMinReturnAssets(){
        OptionalDouble min = assetList.stream().mapToDouble(Asset::getRiskAdjustedReturn).min();
        if (!min.isPresent()) return Stream.empty();

        return assetList.stream().filter(a -> a.getRiskAdjustedReturn() == min.getAsDouble());
    }

    public Map<String, DoubleSummaryStatistics> getSectorWiseStats(){
        return assetList.stream().collect(Collectors.groupingBy(Asset::getSector, Collectors.summarizingDouble(Asset::getRiskAdjustedReturn)));
    }

    public Map<Boolean, List<Asset>> partitionHighRisk(double threshold){
        return assetList.stream().collect(Collectors.partitioningBy( a -> a.getRiskScore() >= threshold));
    }

    public List<Asset> getTopKBySector(String sector, int k){
        return assetList.stream().filter(a -> a.getSector().equalsIgnoreCase(sector)).sorted(Comparator.comparing(Asset::getRiskAdjustedReturn).reversed()).limit(k).collect(Collectors.toList());
    }

    public double getTotalWeightedReturn(){
        return assetList.stream().map(a -> a.getRiskAdjustedReturn() * a.getInvestedAmount()).reduce(0.0, Double::sum);
    }

    public List<String> getDistinctTagsAcrossAssets(){
        return assetList.stream().flatMap(a -> a.getTags().stream()).distinct().collect(Collectors.toList());
    }

    public List<Asset> getAllAssets(){
        return assetList;
    }
    
}
