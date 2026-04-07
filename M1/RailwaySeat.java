package M1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Booking{
    String passengerId;
    int seats;
    int fare;

    Booking(String p, int s, int f){
        passengerId = p;
        seats = s;
        fare = f;
    }
}

class Train{
    String id, src, dest;
    int totalSeats, availableSeats, baseFare;
    List<Booking> bookings = new ArrayList<>();

    Train(String id, String s, String d, int t, int f){
        this.id = id;
        src = s;
        dest = d;
        totalSeats = t;
        availableSeats = t;
        baseFare = f;
    }
}


public class RailwaySeat{

    static List<Train> trains = new ArrayList<>();

    // ADDTRAIN---------------------------------------------------------------
    static void addTrain(String id, String s, String d, int seats, int fare){
        for(Train t : trains)
            if(t.id.equals(id)) return;
        trains.add(new Train(id, s, d, seats, fare));
    }

    // BOOK--------------------------------------------------------------------
    static void book(String id, String pid, int seats){
        for(Train t : trains){
            if(t.id.equals(id)){
                if(seats <= t.availableSeats){
                    int fare = seats * (t.baseFare + 25);
                    t.availableSeats -= seats;
                    t.bookings.add(new Booking(pid, seats, fare));
                    System.out.println("BOOKED " + id + " " + pid + " " + fare);
                }else{
                    System.out.println("Booking failed");
                }
                return;
            }
        }
        System.out.println("Booking failed");
    }

    // CANCEL-------------------------------------------------------------------
    static void cancel(String id, String pid){
        for(Train t : trains){
            if(t.id.equals(id)){
                for(Booking b : t.bookings){
                    if(b.passengerId.equals(pid)){
                        t.availableSeats += b.seats;
                        t.bookings.remove(b);
                        System.out.println("CANCELLED " + id + " " + pid);
                        return;
                    }
                }
                System.out.println("Cancellation failed");
                return;
            }
        }
        System.out.println("Cancellation failed");
    }

    // ROUTE--------------------------------------------------------------------
    static void route(String s, String d){
        boolean found = false;
        for(Train t : trains){
            if(t.src.equals(s) && t.dest.equals(d)){
                System.out.println(t.id + " " + t.availableSeats);
                found = true;
            }
        }
        if (!found) System.out.println("No trains available");
    }

    // SUMMARY------------------------------------------------------------------
    static void summary(){
        boolean any = false;
        for(Train t : trains){
            int total = 0;
            for(Booking b : t.bookings)
                total += b.fare;

            if(total > 0) any = true;
            System.out.println(t.id + " " + total);
        }
        if (!any) System.out.println("No revenue generated");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++){
            String[] p = sc.nextLine().split(" ");
            if (p[0].equals("ADDTRAIN"))
                addTrain(p[1], p[2], p[3], Integer.parseInt(p[4]), Integer.parseInt(p[5]));

            else if (p[0].equals("BOOK"))
                book(p[1], p[2], Integer.parseInt(p[3]));

            else if (p[0].equals("CANCEL"))
                cancel(p[1], p[2]);

            else if (p[0].equals("ROUTE"))
                route(p[1], p[2]);

            else if (p[0].equals("SUMMARY"))
                summary();
        }
    }
}
