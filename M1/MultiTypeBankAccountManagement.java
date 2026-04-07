package M1;

import java.util.*;

class Account{
    String accNo, name, type;
    double balance;

    Account(String a, String n, String t, double b){
        accNo = a;
        name = n;
        type = t;
        balance = b;
    }

    void deposit(double amt){
        balance +=amt;
        System.out.println("Deposited Successfully");
    }

    void withdraw(double amt){
        double charge = 0;
        double minBalance = 0;

        if(type.equals("SavingsAccount")){
            charge = 2;
            minBalance = 0;
        }
        else if(type.equals("CurrentAccount")){
            charge = 5;
            minBalance = -10000;
        }

        else if(type.equals("BusinessAccount")){
            charge = amt * 0.01;
            minBalance = -50000;
        }

        double total = amt + charge;

        if(balance - total < minBalance){
            System.out.println("Insufficient Funds");
        }else{
            balance -= total;
            System.out.println("Withdrawal Successful");
        }
    }
}

public class MultiTypeBankAccountManagement{
    
    static List<Account> list = new ArrayList<>();

    static Account find(String id){
        for(Account a : list)
            if(a.accNo.equals(id)) return a;

        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt();
        sc.nextLine();

        for(int i=0;i<n;i++){
            String[] p = sc.nextLine().split(" ");

            if(p[0].equals("Create")){
                list.add(new Account(p[1], p[2], p[3], Double.parseDouble(p[4])));
                System.out.println("Account Created: " + p[1]);
            }
            else if(p[0].equals("Deposit")){
                Account a = find(p[1]);
                if(a==null) System.out.println("Account Not Found");
                else a.deposit(Double.parseDouble(p[2]));
            }
            else if (p[0].equals("Withdraw")) {
                Account a = find(p[1]);
                if (a == null) System.out.println("Account Not Found");
                else a.withdraw(Double.parseDouble(p[2]));
            }
        }
    }


}
