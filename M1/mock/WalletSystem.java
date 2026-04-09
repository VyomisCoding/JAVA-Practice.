//package mock;

import java.util.*;

class Transaction{
    String type;
    String currency;
    double amount;
    double resultingbalance;

    Transaction(String type, String currency, double amount, double resultingBalance){
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.resultingbalance = resultingBalance;
    }
}

class Wallet{
    String walletId;
    String holderName;
    Map<String, Double> balances = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();
    boolean isFrozen = false;

    Wallet(String walletId, String holderName){
        this.walletId = walletId;
        this.holderName = holderName;
    }
}

class WalletSystem {
    Map<String,Wallet> walletMap = new HashMap<>();

    // create
    public void createWallet(String id, String name){
        if(!walletMap.containsKey(id)){
            walletMap.put(id, new Wallet(id, name));
        }
    }

    // deposit
    public void deposit(String id, String currency, double amount){
        Wallet w = walletMap.get(id);

        if(w == null){
            System.out.println("Wallet not found");
            return;
        }

        double newBalance = w.balances.getOrDefault(currency, 0.0) + amount;
        w.balances.put(currency, newBalance);
        w.transactions.add(new Transaction("DEPOSIT", currency, amount, newBalance));
        System.out.println("DEPOSITED " + id + " " + currency + " " + newBalance);
    }
    
    // withdraw
    public void withdraw(String id, String currency, double amount){
        Wallet w = walletMap.get(id);

        if (w == null) {
            System.out.println("Wallet not found");
            return;
        }

        if (w.isFrozen) {
            System.out.println("Wallet frozen");
            return;
        }

        double balance = w.balances.getOrDefault(currency, 0.0);

        if(balance < amount){
            System.out.println("Insufficient balance");
            return;
        }

        double newBalance = balance - amount;
        w.balances.put(currency, newBalance);
        w.transactions.add(new Transaction("WITHDRAW", currency, amount, newBalance));

    }

    // transfer
    public void transfer(String fromId, String toId, String currency, double amount){
        Wallet from = walletMap.get(fromId);
        Wallet to = walletMap.get(toId);

        if (from == null || to == null) {
            System.out.println("Wallet not found");
            return;
        }

        if (from.isFrozen) {
            System.out.println("From wallet frozen");
            return;
        }

        double balance = from.balances.getOrDefault(currency, 0.0);

        if(balance < amount){
            System.out.println("Insufficient balance");
            return;
        }

        // deduct from sender
        double fromNewBalance = balance - amount;
        from.balances.put(currency, fromNewBalance);
        from.transactions.add(new Transaction("TRANSFER_OUT", currency, amount, fromNewBalance));

        // add to receiver
        double newToBalance = to.balances.getOrDefault(currency, 0.0) + amount;
        to.balances.put(currency, newToBalance);
        to.transactions.add(new Transaction("TRANSFER_IN", currency, amount, newToBalance));

        System.out.println("TRANSFER SUCCESS");
    }

    // FREEZE
    public void freezeWallet(String id) {
        Wallet w = walletMap.get(id);

        if (w != null) {
            w.isFrozen = true;
            w.transactions.add(new Transaction("FREEZE", "", 0, 0));
        }
    }

    // HOLDER SUMMARY
    public void holderSummary(String name){
        List<Wallet> list = new ArrayList<>();
        for (Wallet w : walletMap.values()) {
            if (w.holderName.equals(name)) {
                list.add(w);
            }
        }
        list.sort(Comparator.comparing(w -> w.walletId));
        double total = 0;
        for (Wallet w : list) {
            System.out.println("Wallet: " + w.walletId + " Balances: " + w.balances);

            for (double bal : w.balances.values()) {
                total += bal;
            }
        }
        System.out.println("Total Balance: " + total);
    }
}
