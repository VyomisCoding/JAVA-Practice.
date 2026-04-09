import java.util.*;

// Base Class
abstract class Festival {
    String name;
    String location;
    String date;

    Festival(String name, String location, String date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    abstract void display();
}

// MUSIC Festival
class MusicFestival extends Festival {
    String headliner, genre;
    int ticketPrice;

    MusicFestival(String name, String location, String date,
                  String headliner, String genre, int ticketPrice) {
        super(name, location, date);
        this.headliner = headliner;
        this.genre = genre;
        this.ticketPrice = ticketPrice;
    }

    void display() {
        System.out.println("Festival Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Headliner: " + headliner);
        System.out.println("Music Genre: " + genre);
        System.out.println("Ticket Price: " + ticketPrice);
    }
}

// FOOD Festival
class FoodFestival extends Festival {
    String cuisine;
    int numStalls, entryFee;

    FoodFestival(String name, String location, String date,
                 String cuisine, int numStalls, int entryFee) {
        super(name, location, date);
        this.cuisine = cuisine;
        this.numStalls = numStalls;
        this.entryFee = entryFee;
    }

    void display() {
        System.out.println("Festival Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Cuisine: " + cuisine);
        System.out.println("Number of Stalls: " + numStalls);
        System.out.println("Entry Fee: " + entryFee);
    }
}

// ART Festival
class ArtFestival extends Festival {
    String artType;
    int numArtists, exhibitionFee;

    ArtFestival(String name, String location, String date,
                String artType, int numArtists, int exhibitionFee) {
        super(name, location, date);
        this.artType = artType;
        this.numArtists = numArtists;
        this.exhibitionFee = exhibitionFee;
    }

    void display() {
        System.out.println("Festival Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Art Type: " + artType);
        System.out.println("Number of Artists: " + numArtists);
        System.out.println("Exhibition Fee: " + exhibitionFee);
    }
}

// Manager Class
class FestivalManager {
    List<Festival> list = new ArrayList<>();

    // Separate methods (clean)
    void addMusic(String name, String location, String date,
                  String headliner, String genre, int price) {

        list.add(new MusicFestival(name, location, date, headliner, genre, price));
    }

    void addFood(String name, String location, String date,
                 String cuisine, int stalls, int fee) {

        list.add(new FoodFestival(name, location, date, cuisine, stalls, fee));
    }

    void addArt(String name, String location, String date,
                String artType, int artists, int fee) {

        list.add(new ArtFestival(name, location, date, artType, artists, fee));
    }

    void displayFestival(String name) {
        for (Festival f : list) {
            if (f.name.equals(name)) {
                f.display();
                return;
            }
        }
    }
}

// Main (ONLY input/output)
public class eventplanner{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FestivalManager manager = new FestivalManager();

        while (true) {
            String line = sc.nextLine();
            if (line.equals("EXIT")) break;

            String[] input = line.split(" ");

            if (input[0].equals("ADD_FESTIVAL")) {

                String type = input[1];

                if (type.equals("MUSIC")) {
                    manager.addMusic(
                            input[2], input[3], input[4],
                            input[5], input[6], Integer.parseInt(input[7])
                    );
                }

                else if (type.equals("FOOD")) {
                    manager.addFood(
                            input[2], input[3], input[4],
                            input[5],
                            Integer.parseInt(input[6]),
                            Integer.parseInt(input[7])
                    );
                }

                else if (type.equals("ART")) {
                    manager.addArt(
                            input[2], input[3], input[4],
                            input[5],
                            Integer.parseInt(input[6]),
                            Integer.parseInt(input[7])
                    );
                }
            }

            else if (input[0].equals("DISPLAY_DETAILS")) {
                manager.displayFestival(input[1]);
            }
        }
    }
}