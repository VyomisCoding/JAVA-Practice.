import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserInterface{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PortfolioManager manager = new PortfolioManager();

        try {
            System.out.println("Enter number of assets:");
            int n = Integer.parseInt(sc.nextLine());
            for(int i=0;i<n;i++){
                try{
                    Asset a = AssetUtil.parseAsset(sc.nextLine());
                    manager.addAsset(a);
                }catch(InvalidAssetException e){
                    System.out.println(e.getMessage());
                }
            }

            manager.resolveDuplicatesById();

            System.out.println("Enter year filter:");
            int year = Integer.parseInt(sc.nextLine());

            System.out.println("Enter base risk factor:");
            double baseRisk = Double.parseDouble(sc.nextLine());

            System.out.println("Enter risk threshold:");
            double threshold = Double.parseDouble(sc.nextLine());

            System.out.println("Enter sector and K:");
            String[] topInput = sc.nextLine().split(" ");

            String sector = topInput[0];

            int k = Integer.parseInt(topInput[1]);

            Predicate<Asset> extraFilter = a -> a.getRiskScore() >= threshold;

            try{
                List<Asset> eligible = manager.getEligibleAssets(year, baseRisk, extraFilter);
                System.out.println("Eligible Assets:");
                eligible.forEach(System.out::println);
            }catch(NoEligibleAssetException e){
                System.out.println(e.getMessage());
            } 

            System.out.println("\nSorted Assets:");
            manager.getSortedAssets().forEach(System.out::println);

            System.out.println("\nMin Return Assets:");
            manager.getMinReturnAssets().forEach(System.out::println);

            System.out.println("\nSector Stats:");
            manager.getSectorWiseStats().forEach((k1, v) -> System.out.println(k1 + " -> " + v));

            System.out.println("\nPartition High Risk:");
            Map<Boolean, List<Asset>> part = manager.partitionHighRisk(threshold);

            System.out.println("High Risk: " + part.get(true).stream().map(Asset::getAssetId).collect(Collectors.toList()));

            System.out.println("Low Risk: " + part.get(false).stream().map(Asset::getAssetId).collect(Collectors.toList()));

            System.out.println("\nTop " + k + " in " + sector);
            manager.getTopKBySector(sector, k).forEach(System.out::println);

            System.out.println("\nTotal Weighted Return: " + manager.getTotalWeightedReturn());

            System.out.println("\nDistinct Tags: " + manager.getDistinctTagsAcrossAssets());

        }catch(Exception e){
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}
