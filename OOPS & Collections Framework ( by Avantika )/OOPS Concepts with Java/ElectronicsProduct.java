// Q4: ElectronicsProduct & WashingMachine program

class ElectronicsProduct{

    protected int productId;
    protected String name;
    protected double price;

    // Constructor
    public ElectronicsProduct(int productId, String name, double price){
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Apply Discount
    public void applyDiscount(double percent){
        price = price - (price * percent / 100);
        System.out.println("Discount Applied! New Price: " + price);
    }

    public void display(){
        System.out.println("ID: " + productId + " Name: " + name + " Price: " + price);
    }
}

// Subclass
class WashingMachine extends ElectronicsProduct{
    
    private int warrantyYears;

    public WashingMachine(int productId, String name, double price, int warrantyYears){
        super(productId, name, price);
        this.warrantyYears = warrantyYears;
    }

    // Extend Warranty
    public void extendWarranty(int years) {
        warrantyYears += years;
        System.out.println("Warranty extended! Total Warranty: " + warrantyYears + " years");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Warranty: " + warrantyYears + " years");
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(1, "LG Washer", 25000, 2);

        wm.display();
        wm.applyDiscount(10);
        wm.extendWarranty(1);
        wm.display();
    }
}