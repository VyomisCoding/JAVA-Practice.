package M1.SET4;

import java.util.*;

public class superMarketStoreInventorySystem{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<String[]> inventory = new ArrayList<>();

        for(int i=0;i<n;i++){
            String line = sc.nextLine();
            String[] parts = line.split(", ");
            inventory.add(parts);
            System.out.println("Product added to inventory: " + parts[1]);
        }

        System.out.println("Inventory:");

        double totalValue = 0;

        for(String[] p : inventory){
            String type = p[0];
            String name = p[1];
            double price = Double.parseDouble(p[2]);
            int quantity = Integer.parseInt(p[3]);

            totalValue += price * quantity;

            if(type.equals("Electronics")){
                String warranty = p[4];
                System.out.println(name + " - Price: " + price + ", Quantity: " + quantity + ", Warranty: " + warranty + " months");
            }

            else if(type.equals("Clothing")){
                String size = p[4];
                System.out.println(name + " - Price: " + price + ", Quantity: " + quantity + ", Size: " + size);
            }
        }

        System.out.printf("Total value of the inventory: %.2f\n", totalValue);
    }
}
