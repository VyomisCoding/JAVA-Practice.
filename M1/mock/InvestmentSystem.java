

import java.util.HashMap;
import java.util.Map;

class Portfolio {
    String id;
    String investorName;
    Map<String, Integer> stocks = new HashMap<>();
    Map<String, Double> prices = new HashMap<>();
    double cashBalance;

    Portfolio(String id, String name, double cash) {
        this.id = id;
        this.investorName = name;
        this.cashBalance = cash;
    }
}


public class InvestmentSystem {
    Map<String, Portfolio> map = new HashMap<>();

    // CREATE
    public void create(String id, String name, double cash) {
        map.putIfAbsent(id, new Portfolio(id, name, cash));
    }

    // BUY
    public void buy(String id, String stock, int qty, double price) {
        Portfolio p = map.get(id);

        double cost = qty * price;

        if (p.cashBalance < cost) {
            System.out.println("Insufficient funds");
            return;
        }

        p.cashBalance -= cost;
        p.stocks.put(stock, p.stocks.getOrDefault(stock, 0) + qty);
        p.prices.put(stock, price);
    }

    // SELL
    public void sell(String id, String stock, int qty, double price) {
        Portfolio p = map.get(id);

        if (!p.stocks.containsKey(stock)) {
            System.out.println("Stock not found");
            return;
        }

        int owned = p.stocks.get(stock);

        if (owned < qty) {
            System.out.println("Insufficient quantity");
            return;
        }

        p.stocks.put(stock, owned - qty);
        p.cashBalance += qty * price;
        p.prices.put(stock, price);
    }

    // PORTFOLIO VALUE
    public void value(String id) {
        Portfolio p = map.get(id);

        double total = p.cashBalance;

        for (String stock : p.stocks.keySet()) {
            int qty = p.stocks.get(stock);
            double price = p.prices.get(stock);
            total += qty * price;
        }

        System.out.println(total);
    }

    // TOP INVESTOR
    public void topInvestor() {
        double max = 0;
        String name = "";

        for (Portfolio p : map.values()) {
            double total = p.cashBalance;

            for (String stock : p.stocks.keySet()) {
                total += p.stocks.get(stock) * p.prices.get(stock);
            }

            if (total > max) {
                max = total;
                name = p.investorName;
            }
        }

        System.out.println(name);
    }
}
