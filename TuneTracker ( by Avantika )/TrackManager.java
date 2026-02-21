import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TrackManager{
    private List<Track> trackList = new ArrayList<>();
    
    public void addTrack(Track track){
        trackList.add(track);
    }

    public void resolveDuplicatesById(){
        trackList = new ArrayList<>(trackList.stream().collect(Collectors.toMap(Track::getTrackId,Function.identity(),TrackUtil.mergeDuplicates())).values());
    }

    public List<Track> getEligibleTracks(int year, double baseCommission, Predicate<Track> extraFilter) throws NoEligibleTrackException{
        List<Track> eligible = trackList.stream()
                .filter(TrackUtil.isReleasedBefore(year))
                .filter(extraFilter)
                .map(track -> {
                    double adjusted = TrackUtil
                            .computeAdjustedEarnings(year, baseCommission)
                            .apply(track, baseCommission);
                    TrackUtil.applyAdjustedEarnings(adjusted).accept(track);
                    return track;
                })
                .collect(Collectors.toList());

        if (eligible.isEmpty())
            throw new NoEligibleTrackException("No tracks released before " + year + " to apply commission");

        return eligible;
    }

    public List<Track> getTracksSortedByAdjustedEarningsTitleArtist(){
        return trackList.stream().sorted(TrackUtil.byAdjustedEarningsThenTitleThenArtist()).collect(Collectors.toList());
    }

    public Stream<Track> getMinEarningTracks(){
        double min = trackList.stream().mapToDouble(Track::getEarnings).min().orElse(0);
        return trackList.stream().filter(t -> t.getEarnings() == min);
    }

    public Map<String, DoubleSummaryStatistics> getGenreWiseStats(){
        return trackList.stream().collect(Collectors.groupingBy(Track::getGenre,Collectors.summarizingDouble(Track::getEarnings)));
    }

    public Map<Boolean, List<Track>> partitionByRating(double threshold){
        return trackList.stream().collect(Collectors.partitioningBy(t -> t.getRating() >= threshold));
    }

    public List<Track> getTopKByGenre(String genre, int k){
        return trackList.stream().filter(t -> t.getGenre().equalsIgnoreCase(genre)).sorted(Comparator.comparing(Track::getEarnings).reversed()).limit(k).collect(Collectors.toList());
    }

    public List<Track> getTrackList(){
        return trackList;
    }
    
}
