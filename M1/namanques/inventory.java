import java.util.*;

abstract class invent {
    String name;
    double price;
    int quantity;

    invent(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    abstract void display();
    double total(){
      return  price * quantity;
    }
}

class elect extends invent {
    int warranty;

    elect(String name, double price, int quantity, int warranty) {
        super(name, price, quantity);
        this.warranty = warranty;
    }

    void display() {
        System.out.println(name + " - Price: " + price +
                ", Quantity: " + quantity +
                ", Warranty: " + warranty + " months");
    }
}

class cloth extends invent {
    String size;

    cloth(String name, double price, int quantity, String size) {
        super(name, price, quantity);
        this.size = size;
    }

    void display() {
        System.out.println(name + " - Price: " + price +
                ", Quantity: " + quantity +
                ", Size: " + size);
    }
}

class management {
    List<invent> list = new ArrayList<>();

    void addelect(String name, double price, int quantity, int warranty) {
        list.add(new elect(name, price, quantity, warranty));
        System.out.println("Product added to inventory: " + name);
    }

    void addcloth(String name, double price, int quantity, String size) {
        list.add(new cloth(name, price, quantity, size));
        System.out.println("Product added to inventory: " + name);
    }

    void display() {
        System.out.println("Inventory:");
        for (invent i : list) {
            i.display();
        }
    }

    void get() {
        double sum =0;
        for(invent i : list){
            sum = sum + i.total();
        }
       System.out.printf("total valis is %.2f\n" , sum);
    }
}

public class inventory{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        management m = new management();

        int n = sc.nextInt();
        sc.nextLine();

        while (n-- > 0) {

            String s = sc.nextLine();
            String[] p = s.split(", ");

            if (p[0].equals("Electronics")) {
                m.addelect(p[1],
                        Double.parseDouble(p[2]),
                        Integer.parseInt(p[3]),
                        Integer.parseInt(p[4]));
            } else {
                m.addcloth(p[1],
                        Double.parseDouble(p[2]),
                        Integer.parseInt(p[3]),
                        p[4]);
            }
        }

        m.display();
        m.get();
    }
}