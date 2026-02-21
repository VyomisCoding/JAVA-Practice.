
public class SmartTracker {
    // Avg Temp --------------------------------------------------------------
    public static double getAverageTemperature(int[] temperatures){
        int sum = 0 ;
        for(int t : temperatures){
            sum +=t;
        }
        return (double) sum/temperatures.length;
    }    

    // Hottest day-------------------------------------------------------------------------
    public static int getHottestDay(int[] temperatures){
        int maxTemp = temperatures[0];
        int day = 1;
        for(int i=1;i<temperatures.length;i++){
            if(temperatures[i] > maxTemp){
                maxTemp = temperatures[i];
                day = i+1 ;
            }
        }
        return day;
    }

    // Coldest day ---------------------------------------------------------------------------------
    public static int getColdestDay(int[] temperatures){
        int minTemp = temperatures[0];
        int day = 1;
        for(int i=1;i<temperatures.length;i++){
            if(temperatures[i] < minTemp){
                minTemp = temperatures[i];
                day = i+1;
            }
        }
        return day;
    }

    // Count Hot Day ( )>30 C)
    public static int countHotDays(int[] temperatures){
        int count = 0;
        for(int t : temperatures){
            if (t > 30) count++;
        }
        return count;
    }

    // Total Sales
    public static int getTotalSales(int[] sales){
        int total = 0;
        for (int s : sales){
            total += s;
        }
        return total;
    }

    // Average Sales
    public static double getAverageSales(int[] sales){
        return (double) getTotalSales(sales) / sales.length;
    }

    // highest Sales
    public static int getHighestSale(int[] sales){
        int max = sales[0];
        for(int s : sales){
            if (s > max) max = s;
        }
        return max;
    }

    // Lowest sales
    public static int getLowestSale(int[] sales){
        int min = sales[0];
        for(int s : sales){
            if (s < min) min = s;
        }
        return min;
    }

    // Count High-Sales Days (>100)
    public static int countHighSalesDays(int[] sales){
        int count = 0;
        for(int s : sales){ 
            if (s > 100) count++;
        }
        return count;
    }


    // Search Sale Value
    public static int searchSale(int[] sales, int target){
        for(int i=0;i<sales.length;i++){
            if(sales[i] == target){
                return i + 1; // Day number (1-based)
            }
        }
        return -1;            // Not found
    }

    // Update Sales
    public static void updateSale(int[] sales, int day, int newValue){
        sales[day - 1] = newValue;       // Update specific day (1-based index)
    }

    // Main Method ------------------------------------------------------------------------------------------------
    public static void main(String[] args){
        int[] sales = {120, 80, 150, 90, 200, 75, 110};
        int[] temperatures = {32, 28, 31, 29, 35, 27, 30};

        System.out.println("Average Temperature: " + getAverageTemperature(temperatures));

        int hottest = getHottestDay(temperatures);
        int coldest = getColdestDay(temperatures);

        System.out.println("Highest Temperature: " + temperatures[hottest - 1] + "°C");
        System.out.println("Lowest Temperature: " + temperatures[coldest - 1] + "°C");

        System.out.println("Hot days (>30°C): " + countHotDays(temperatures));

        System.out.println("Total Sales: " + getTotalSales(sales));
        System.out.println("Average Sales: " + getAverageSales(sales));

        System.out.println("Highest Sale: " + getHighestSale(sales));
        System.out.println("Lowest Sale: " + getLowestSale(sales));

        System.out.println("High Sales Days (>100): " + countHighSalesDays(sales));

        int targetSale = 150;
        int foundDay = searchSale(sales, targetSale);
        if (foundDay != -1)
            System.out.println("Sale " + targetSale + " found on Day " + foundDay);

        updateSale(sales, 2, 95);
        System.out.println("Updated Sales for Day 2: " + sales[1]);
 
        
    }
}
