import java.util.*;

class Transaction{
    String type;
    int amount;
    int  remainingLimit;

    Transaction(String type, int amount, int remainingLimit){
        this.type = type;
        this.amount = amount;
        this.remainingLimit = remainingLimit;
    }
}

class  CreditCard{
    String cardNumber;
    String holderName;
    int creditLimit;
    int availableLimit;
    List<Transaction> transactions;

    CreditCard(String cardNumber, String holderName, int creditLimit){
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.creditLimit = creditLimit;
        this.availableLimit = creditLimit;
        this.transactions = new ArrayList<>();
    }
}


public class AccountManagement{

    static HashMap<String, CreditCard> map = new HashMap<>();

    public static void issueCard(String cardNumber, String holderName, int limit){
        if(!map.containsKey(cardNumber)){
            map.put(cardNumber, new CreditCard(cardNumber, holderName, limit));
        }
    }

    public static void spend (String cardNumber, int amount){
        if(!map.containsKey(cardNumber)){
            System.out.println("Transactions declined");
            return;
        }

        CreditCard card = map.get(cardNumber);
        if(amount <= card.availableLimit){
            card.availableLimit -= amount;
            card.transactions.add(new Transaction("SPEND", amount, card.availableLimit));
            System.out.println("SPENT" + cardNumber + " " + card.availableLimit);
        }else{
            System.out.println("Transaction declined");
        }
    }

    public static void payment(String cardNumber, int amount){
        if(!map.containsKey(cardNumber)){
            System.out.println("Card not found");
            return;
        }
        CreditCard card = map.get(cardNumber);
        card.availableLimit += amount;

        if(card.availableLimit > card.creditLimit){
            card.availableLimit = card.creditLimit;
        }
        card.transactions.add(new Transaction("PAYMENT",amount, card.availableLimit));
        System.out.println("PAYMENT DONE" + cardNumber + " " + card.availableLimit);
    }

    public static void holder(String name){
        List<CreditCard> list = new ArrayList<>();

        for(CreditCard c : map.values()){
            if(c.holderName.equals(name)){
                list.add(c);
            }
        }

        if(list.isEmpty()){
            System.out.println("No Cards found");
            return;
        }

        Collections.sort(list,(a,b) -> a.cardNumber.compareTo(b.cardNumber));

        for(CreditCard c : list){
            System.out.println(c.cardNumber + " " + c.availableLimit);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String cmd = parts[0];

            if (cmd.equals("ISSUE")) {
                String cardNumber = parts[1];
                String holderName = parts[2];
                int limit = Integer.parseInt(parts[3]);
                issueCard(cardNumber, holderName, limit);
            } 
            else if (cmd.equals("SPEND")) {
                spend(parts[1], Integer.parseInt(parts[2]));
            } 
            else if (cmd.equals("PAYMENT")) {
                payment(parts[1], Integer.parseInt(parts[2]));
            } 
            else if (cmd.equals("HOLDER")) {
                holder(parts[1]);
            }
        }
    }
}
