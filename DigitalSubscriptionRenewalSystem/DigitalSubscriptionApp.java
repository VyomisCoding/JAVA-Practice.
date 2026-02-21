
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;



// custom exception
class PaymentDeclinedException extends Exception{
    public PaymentDeclinedException(String msg){
        super(msg);
    }
}

// Strategy pattern (Discount strategy) -------------------------------------------------------------------------------------------------------------------------------
interface DiscountStrategy{
    double applyDiscount(double amount);
}

class NoDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double amount){
        return amount;
    }
}

class FestivalDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double amount){
        return amount * 0.90;    // 10% off
    }
}

class StudentDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double amount){
        return amount*0.80;      // 20% off
    }
}

// USER CLASS ---------------------------------------------------------------------------------------------------------------------------------------------------------

class User{

    private int userId;
    private String name;
    private LocalDate expiryDate;
    private boolean autoRenew;
    private DiscountStrategy discountStrategy;

    public User(int userId, String name, LocalDate expiryDate, boolean autoRenew, DiscountStrategy strategy){
        this.userId = userId;
        this.name = name;
        this.expiryDate = expiryDate;
        this.autoRenew = autoRenew;
        this.discountStrategy = strategy;
    }

    public int getUserId(){
        return userId;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    public boolean isExpired(){
        return expiryDate.isBefore(LocalDate.now());
    }

    public boolean isAutoRenew(){
        return autoRenew;
    }

    public void renewSubscription(double basePrice) throws PaymentDeclinedException {

        double finalAmount = discountStrategy.applyDiscount(basePrice);

        if (finalAmount <= 0){
            throw new PaymentDeclinedException("Invalid payment amount!");
        }

        // Mock: Payment randomly fails
        if (new Random().nextInt(10) == 0){
            throw new PaymentDeclinedException("Payment declined by bank!");
        }

        expiryDate = expiryDate.plusMonths(1);
        System.out.println("User " + name + " renewed. Paid: " + finalAmount);
    }

    @Override
    public String toString() {
        return "UserID: " + userId + ", Name: " + name + ", Expiry: " + expiryDate + ", AutoRenew: " + autoRenew;
    }
}


// SERVICE CLASS ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

class SubscriptionService{
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }

    // Auto renew using stream
    public void autoRenewSubscriptions(double basePrice){
        users.stream().filter(User::isAutoRenew).forEach(user -> {
            try{
                user.renewSubscription(basePrice);
            }catch(PaymentDeclinedException e){
                System.out.println("No User " + user.getUserId() + ": " + e.getMessage());
            }
        });
    }

    // Identify expired using stream
    public void showExpiredUsers(){
        List<User> expired = users.stream().filter(User::isExpired).collect(Collectors.toList());

        if(expired.isEmpty()){
            System.out.println("No expired users.");
        }else{
            expired.forEach(System.out::println);
        }
    }

    public void listAllUsers(){
        users.forEach(System.out::println);
    }
}

// MAIN CLASS------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class DigitalSubscriptionApp{

    public static void main(String[] args){
        SubscriptionService service = new SubscriptionService();

        // Add sample users
        service.addUser(new User(1, "Rahul", LocalDate.now().minusDays(2), true, new StudentDiscount()));

        service.addUser(new User(2, "Priya", LocalDate.now().plusDays(3), true, new NoDiscount()));

        service.addUser(new User(3, "Amit", LocalDate.now().minusDays(1), false, new FestivalDiscount()));

        System.out.println("\n--- All Users ---");
        service.listAllUsers();

        System.out.println("\n--- Expired Users ---");
        service.showExpiredUsers();

        System.out.println("\n--- Auto Renewal Process ---");
        service.autoRenewSubscriptions(200);

        System.out.println("\n--- After Renewal ---");
        service.listAllUsers();
    }  
}
