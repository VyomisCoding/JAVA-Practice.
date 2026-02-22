import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant{

    private Map<String, Double> menu = new HashMap<>();
    private List<Integer> ratings = new ArrayList<>();

     // Add Item
    public void addItem(String item, double price){
        menu.put(item, price);
        System.out.println(item + " added with price: " + price);
    }

    // remove item
    public void removeItem(String item){
        menu.remove(item);
        System.out.println(item + " removed from menu");
    }

    // add rating
    public void addRating(int rating) {
        ratings.add(rating);
    }

    // calculate avg rating
    public double averageRating(){
        if (ratings.isEmpty()) return 0;

        int sum = 0;
        for (int r : ratings) sum += r;
        return sum / (double) ratings.size();
    }

    // main
    public static void main(String[] args){
        Restaurant r = new Restaurant();

        r.addItem("Pizza", 250);
        r.addItem("Burger", 120);
        r.removeItem("Burger");

        r.addRating(5);
        r.addRating(4);
        r.addRating(3);

        System.out.println("Average Rating = " + r.averageRating());
    }
}
