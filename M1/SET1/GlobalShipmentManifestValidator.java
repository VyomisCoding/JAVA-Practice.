package M1.SET1;

import java.time.LocalDate;
import java.util.Scanner;

public class GlobalShipmentManifestValidator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for(int i=0;i<n;i++){

            String line = sc.nextLine();
            String[] p = line.split("\\|");

            boolean valid = true;

            if(p.length!=5){
                System.out.println("NON-COMPLIANT RECORD");
                continue;
            }

            String code = p[0];
            String date = p[1];
            String mode = p[2];
            String weight = p[3];
            String status = p[4];

            // Shipment Code --------
            if(!code.matches("SHIP-[1-9]\\d{5}") || code.matches(".*(\\d)\\1{3,}.*"))
                valid = false;

            // Date --------
            try{
                LocalDate d = LocalDate.parse(date);
                int y = d.getYear();
                if(y < 2000 || y > 2099)
                    valid = false;
            }
            catch(Exception e){
                valid = false;
            }

            // Mode --------
            if(!(mode.equals("AIR") || mode.equals("SEA") || mode.equals("ROAD") ||
                 mode.equals("RAIL") || mode.equals("EXPRESS") || mode.equals("FREIGHT")))
                valid = false;

            // Weight --------
            if(!weight.matches("(0|[1-9]\\d{0,5})(\\.\\d{1,2})?"))
                valid = false;
            else{
                double w = Double.parseDouble(weight);
                if(w<=0 || w>999999.99)
                    valid = false;
            }

            // Status --------
            if(!(status.equals("DELIVERED") ||
                 status.equals("CANCELLED") ||
                 status.equals("IN_TRANSIT")))
                valid = false;

            if(valid)
                System.out.println("COMPLIANT RECORD");
            else
                System.out.println("NON-COMPLIANT RECORD");
        }
    }
}
