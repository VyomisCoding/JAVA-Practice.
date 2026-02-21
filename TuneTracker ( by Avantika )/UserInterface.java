import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserInterface{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TrackManager manager = new TrackManager();
        try{
            System.out.println("Enter the number of tracks");
            int n = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the details for each track");
            for(int i=0;i<n;i++){
                try{
                    String line = sc.nextLine();
                    Track track = TrackUtil.parseTrack(line);
                    manager.addTrack(track);
                }catch(InvalidTrackException e){
                    System.out.println("InvalidTrackException: " + e.getMessage());
                }
            }
            manager.resolveDuplicatesById();
            System.out.println("Enter the release year to filter tracks released before that year");
            int year = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the base commission percentage");
            double baseCommission = Double.parseDouble(sc.nextLine());
            System.out.println("Enter the rating threshold");
            double threshold = Double.parseDouble(sc.nextLine());
            System.out.println("Enter genre for Top-K and the value of K");
            String[] topKInput = sc.nextLine().split(" ");
            String genre = topKInput[0];
            int k = Integer.parseInt(topKInput[1]);
            Predicate<Track> extraFilter = t -> t.getRating() >= threshold;
            try{
                List<Track> eligible = manager.getEligibleTracks(year, baseCommission, extraFilter);
                System.out.println("Eligible tracks:");
                eligible.forEach(System.out::println);
            }catch(NoEligibleTrackException e){
                System.out.println(e.getMessage());
            }
            System.out.println("\nAll tracks sorted:");
            manager.getTracksSortedByAdjustedEarningsTitleArtist().forEach(System.out::println);
            System.out.println("\nTracks with Minimum Earnings:");
            manager.getMinEarningTracks().forEach(System.out::println);

            System.out.println("\nGenre-wise Summary:");
            manager.getGenreWiseStats().forEach((g, stats) ->
                    System.out.println(g + " → count=" + stats.getCount() + " | min=" + stats.getMin() + " | max=" + stats.getMax() + " | sum=" + stats.getSum() + " | avg=" + stats.getAverage())
            );

            System.out.println("\nPartition by rating:");

            Map<Boolean, List<Track>> partition = manager.partitionByRating(threshold);

            System.out.println("High-rated (>= threshold): " + 
                partition.get(true).stream()
                    .map(Track::getTrackId)
                    .collect(Collectors.toList()));

            System.out.println("Others: " + 
            partition.get(false).stream()
            .map(Track::getTrackId)
            .collect(Collectors.toList()));

            System.out.println("\nTop-" + k + " in " + genre + " by earnings:");
            manager.getTopKByGenre(genre, k).forEach(System.out::println);

        }catch(Exception e){
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}
