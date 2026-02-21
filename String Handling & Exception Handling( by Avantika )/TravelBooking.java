import java.util.*;

public class TravelBooking{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter booking details (name:package:days:season)");
        String input = sc.nextLine();

        try{
            String[] arr = input.split(":");

            if(arr.length != 4){
                throw new Exception("Invalid Booking format");
            }

            String name = arr[0];
            String packageName = arr[1];
            int days = Integer.parseInt(arr[2]);
            String season = arr[3];

            Map<String, Integer> basePrices = new HashMap<>();
            basePrices.put("P123", 5000);
            basePrices.put("P223", 10000);
            basePrices.put("P345", 12000);

            if(!basePrices.containsKey(packageName)){
                throw new Exception("Invalid Package Name!");
            }

            // Validate days
            if(days <= 0){
                throw new Exception("Invalid number of days!");
            }

            // Validate seasons
            if(!(season.equals("season") || season.equals("peakseason") || season.equals("offseason"))){
                throw new Exception("Invalid season type!");
            }

            int basePrice = basePrices.get(packageName);

            // Apply discount if days >= 7
            double discountedPrice = basePrice;
            if(days >= 7){
                discountedPrice = basePrice - (basePrice * 0.10);
            }

            // Seasonal Charges
            double seasonCharge = 0;
            switch(season){
                case "season": seasonCharge = basePrice * 0.15; break;
                case "peakseason": seasonCharge = basePrice * 0.25; break;
                case "offseason": seasonCharge = basePrice * 0.10; break;
            }

            double finalPrice = discountedPrice + seasonCharge;

            System.out.println("Customer: " + name);
            System.out.println("Total Booking Price: ₹" + finalPrice);


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
