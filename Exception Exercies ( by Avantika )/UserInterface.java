import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserInterface{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // User Details Input
            System.out.println("Enter Name:");
            String name = sc.nextLine();
            System.out.println("Enter Age:");
            int age = Integer.parseInt(sc.nextLine());     // parseInt usage
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Credit Card Number:");
            String creditCard = sc.nextLine();

            // Validate user details-----------------------------------------------------
            HotelBooking.validateUserDetails(age, email, creditCard);


            //  Booking Details
            System.out.println("Enter Hotel Name:");
            String hotel = sc.nextLine();
            System.out.println("Select Room Type (Standard/Deluxe/Suite):");
            String room = sc.nextLine();
            System.out.println("Enter Check-in Date (yyyy-MM-dd):");
            String checkInStr = sc.nextLine();
            System.out.println("Enter Check-out Date (yyyy-MM-dd):");
            String checkOutStr = sc.nextLine();

            // validate of booking details --------------------------------------------------------
            HotelBooking.validateBookingDetails(hotel, room, checkInStr, checkOutStr);

            // Convert to LocalDate
            LocalDate checkIn = LocalDate.parse(checkInStr);
            LocalDate checkOut = LocalDate.parse(checkOutStr);

            // Nights calculation using ChronoUnit
            int nights = (int) ChronoUnit.DAYS.between (checkIn, checkOut);

            // EXTRA OPTION
            System.out.println("Breakfast Included? (yes/no):");
            String breakfastInput = sc.nextLine();
            boolean breakfastIncluded = breakfastInput.equalsIgnoreCase("yes");

            // COST CALCULATION
            double totalCost = HotelBooking.calculateBookingCost(room, nights, breakfastIncluded);

            System.out.println("Total Booking Cost: " + totalCost);

        } catch (InvalidBookingDetailsException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
