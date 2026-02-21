
import java.util.*;

public class Track{
    private String trackId;
    private String artist;
    private String title;
    private String genre;
    private double earnings;
    private int releaseYear;
    private double rating;
    private List<String> tags;

    public Track(String trackId, String artist, String title, String genre, double earnings, int releaseYear, double rating, List<String> tags){
        this.trackId = trackId;
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.earnings = earnings;
        this.releaseYear = releaseYear;
        this.tags = tags != null ? tags:new ArrayList<>();
    }

    public String getTrackId() { return trackId;}
    public void settrackId(String trackId) { this.trackId = trackId; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getTitle() { return title; }
    public void setTitle( String title ) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getEarnings() { return earnings; }
    public void setEarnings(double earnings) {this.earnings = earnings ;}

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) {this.tags = tags;}

    @Override
    public String toString(){
        return "trackId= " + trackId + " | artist= " + artist + " | title=" + title + " | genre=" + genre + " | earnings=" + earnings + " | releaseYear=" + releaseYear + " | rating=" + rating + " | tags=" + tags; 
    }

    
}
