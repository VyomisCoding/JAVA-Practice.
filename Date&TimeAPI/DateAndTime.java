import java.util.*;

public class DateAndTime{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // QUESTION 1 -------------------------------------------------------------------------------------------------------------------------
        
        // LocalDate today = LocalDate.now();
        // System.out.println("Today's date: " + today);
        
        // DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");     // iss format mein likhne kee liyee
        // System.out.println(today.format(df));

        // QUESTION 2 --------------------------------------------------------------------------------------------------------------

        // System.out.println("Enter dob (yyyy-mm-dd):");
        // String dobInput = sc.next();
        // LocalDate dob = LocalDate.parse(dobInput);
        // LocalDate today = LocalDate.now();
        // Period age = Period.between(dob, today);
        // System.out.println("Age = " + age.getYears() + " years");

        // QUESTION 3 --------------------------------------------------------------------------------------------------------------
        
        // LocalTime t = LocalTime.now();
        // DateTimeFormatter tf= DateTimeFormatter.ofPattern("HH:mm:ss");
        // System.out.println(t.format(tf));

        // QUESTION 4 --------------------------------------------------------------------------------------------------------------

        // LocalDate today = LocalDate.now();
        // System.out.println(today.plusDays(30));

        // Question 5 ------------------------------------------------------------------------------------------------------------------------

        // LocalTime t = LocalTime.now();
        // System.out.println(t.minusHours(2));

        // Question 6 ------------------------------------------------------------------------------------------------------------------------

        // LocalDate d = LocalDate.of(2026,2,20);
        // System.out.println(d.getDayOfWeek());

        // Question 7 ------------------------------------------------------------------------------------------------------------------------

        // LocalDate d1 = LocalDate.parse("2025-12-25");   // string mein leke localdate mein parse krr rhe hain
        // System.out.println(d1);

        // Question 8 ---------------------------------------------------------------------------------------------------------------------------------

        // LocalDate today = LocalDate.now();
        // LocalDate newYear = LocalDate.of(today.getYear()+1,1,1);
        // long days = ChronoUnit.DAYS.between(today,newYear);
        // System.out.println("Days left " + days);

        // Question 9 ----------------------------------------------------------------------------------------------------------------------------------

        // System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));
        // System.out.println(ZonedDateTime.now(ZoneId.of("Europe/London")));
        // System.out.println(ZonedDateTime.now(ZoneId.of("America/New_York")));

        // Question 10 ----------------------------------------------------------------------------------------------------------------------------------

        // LocalDateTime dt = LocalDateTime.now();
        // ZonedDateTime z = dt.atZone(ZoneId.of("Asia/Kolkata"));
        // System.out.println(z);

        // Question 11 ----------------------------------------------------------------------------------------------------------------------------------

        // LocalTime t1 = LocalTime.of(9,0);
        // LocalTime t2 = LocalTime.of(12,30);
        // Duration d = Duration.between(t1,t2);
        // System.out.println("Duration: " + d.toHours() + " hours and " + d.toMinutesPart() + " minutes");
        // System.out.println("Minutes = " + d.toMinutes());

        // Question 12 ----------------------------------------------------------------------------------------------------------------------------------

        // LocalDate d1 = LocalDate.of(2003,5,30);
        // LocalDate d2 = LocalDate.of(2026,2,20);

        // long days = ChronoUnit.DAYS.between(d1,d2);
        // System.out.println("Total Days = " + days);

        // Question 13 ---------------------------------------------------------------------------------------------------------------------------------

        // LocalDate d = LocalDate.of(2003, 5, 30);
        // DateTimeFormatter df = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        // System.out.println(d.format(df));

        // Question 14 ---------------------------------------------------------------------------------------------------------------------------------

        // LocalTime t = LocalTime.now();
        // int h = t.getHour();

        // if(h < 12) System.out.println("Good Morning");
        // else if(h < 17) System.out.println("Good Afternoon");
        // else System.out.println("Good Evening");

        // Question 15 ---------------------------------------------------------------------------------------------------------------------------------

        // LocalDate today = LocalDate.now();
        // LocalDate birthday = LocalDate.of(today.getYear(), 5, 30);

        // if(birthday.isBefore(today)) {
        //     birthday = birthday.plusYears(1);
        // }

        // long days = ChronoUnit.DAYS.between(today, birthday);
        // System.out.println("Days left for birthday: " + days);

    }
}
