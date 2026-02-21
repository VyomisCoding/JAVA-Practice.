
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class HotelBooking{

    // valid hotel list
    private static final String[] hotels = { "Hilton", "Hyatt", "Marriott","Taj"};

    // Validate user details
    public static void validateUserDetails(int age, String email, String creditCard) throws InvalidBookingDetailsException{
        
        //age check
        if(age<18 || age>100){
            throw new InvalidBookingDetailsException("Invalid age");
        } 

        // Email validation 
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            throw new InvalidBookingDetailsException("Invalid Email");
        }

        // credit card length
        if(!creditCard.matches("\\d{16}")){
            throw new InvalidBookingDetailsException("Invalid Credit Card Number");
        }
    }

    // Validate Booking details
    public static void validateBookingDetails(String hotelName, String roomType, String checkInStr, String checkOutStr) throws InvalidBookingDetailsException{

        // validate hotel name using simple search
        boolean found = false;
        for(String h : hotels){
            if(h.equals(hotelName)) found = true;
        }

        if(!found){
            throw new InvalidBookingDetailsException("Invalid Hotel Name");
        }

        // Room types allowed

        if(!roomType.equals("Standard") && !roomType.equals("Deluxe") && !roomType.equals("Suite")){
            throw new InvalidBookingDetailsException("Invalid Room type");
        }

        LocalDate checkIn;
        LocalDate checkOut;

        try {
            // Converting String to LocalDate
            checkIn = LocalDate.parse(checkInStr);
            checkOut = LocalDate.parse(checkOutStr);
        } catch (DateTimeParseException e) {
            throw new InvalidBookingDetailsException("Invalid Date Format");
        }

        // check-in future mein hona chiye
        if(!checkIn.isAfter(LocalDate.now())){
            throw new InvalidBookingDetailsException("Invalid check-in date");
        }

        // check-out date must be after check-in
        if(!checkOut.isAfter(checkIn)){
            throw new InvalidBookingDetailsException("Invalid check-out date");
        }
    }


    // cost calculation 
    public static double calculateBookingCost(String roomType, int numNights, boolean breakfastIncluded){
        int basePrice = 0;
        switch(roomType){
            case "Standard" : basePrice = 1000; break;
            case "Deluxe": basePrice = 2000; break;
            case "Suite": basePrice = 3000; break;
        }
        int breakfastCost = breakfastIncluded ? 100:0;

        return (basePrice * numNights) + (breakfastCost * numNights);
    }
}
