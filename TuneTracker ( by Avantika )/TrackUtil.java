
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TrackUtil{
    public static Track parseTrack(String input) throws InvalidTrackException {
        try {
            String[] parts = input.split(":");
            if(parts.length < 7) throw new InvalidTrackException("Input '" + input + "' is not in the required format.");

            String trackId = parts[0];
            String artist = parts[1];
            String title = parts[2];
            String genre = parts[3];
            double earnings = Double.parseDouble(parts[4]);
            int releaseYear = Integer.parseInt(parts[5]);
            double rating = Double.parseDouble(parts[6]);

            List<String> tags = new ArrayList<>();
            if(parts.length == 0){
                tags = Arrays.stream(parts[7].split(",")).map(String::trim).distinct().collect(Collectors.toList());
            }

            return new Track(trackId, artist, title, genre, earnings, releaseYear, rating, tags);

        } catch (Exception e) {
            throw new InvalidTrackException("Input '" + input + "' is not the required format.");
        }
    }

    public static Predicate<Track> isReleasedBefore(int year){
        return track -> track.getReleaseYear() < year;
    }

    public static Function<String, Double> genreAdjustment(){
        return genre -> {
            switch (genre.toUpperCase()){
                case "Classical": return 1.5;
                case "ROCk" : return 0.5;
                case "POP" : return 0.0;
                case "ELECTRONIC": return -0.5;
                case "INDIE": return 0.75;
                default: return 0.25;
            }
        };
    }

    public static BiFunction<Track, Double, Double> computeAdjustedEarnings(int filterYear, double baseCommission){
        return (track, base) -> {
            double genreAdj = genreAdjustment().apply(track.getGenre());
            double ageAdj = (filterYear - track.getReleaseYear()) >= 5 ? 1.0 : 0.0;
            double commission = baseCommission + genreAdj + ageAdj;
            return track.getEarnings() - (track.getEarnings() * commission/100);
        };
    }

    public static Consumer<Track> applyAdjustedEarnings(double adjusted){
        return track -> track.setEarnings(adjusted);
    }

    public static Comparator<Track> byAdjustedEarningsThenTitleThenArtist(){
        return Comparator.comparing(Track::getEarnings).thenComparing(Track::getTitle).thenComparing(Track::getArtist);
    }

    public static BinaryOperator<Track> mergeDuplicates(){
        return (t1,t2) -> {
            t1.setEarnings(t1.getEarnings() + t2.getEarnings());
            t1.setReleaseYear(Math.min(t1.getReleaseYear(), t2.getReleaseYear()));
            t1.setRating(Math.max(t1.getRating(), t2.getRating()));

            Set<String> tagSet = new HashSet<>(t1.getTags());
            tagSet.addAll(t2.getTags());
            t1.setTags(new ArrayList<>(tagSet));

            return t1;
        };
    }


}
