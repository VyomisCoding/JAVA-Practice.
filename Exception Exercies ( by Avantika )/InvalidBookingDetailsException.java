 

// custom exception class
public class InvalidBookingDetailsException extends Exception{
    
    // construtor to pass custom message
    public InvalidBookingDetailsException(String message){
        super(message);
    }
}
