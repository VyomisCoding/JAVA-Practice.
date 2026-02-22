// Find Which Dates Fall on Weekend

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class Q7 {
    public static void main(String[] args){

        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2024, 2, 20),
                LocalDate.of(2024, 2, 24),
                LocalDate.of(2024, 2, 25)
        );

        List<LocalDate> weekends = dates.stream()
                .filter(d -> d.getDayOfWeek() == DayOfWeek.SATURDAY ||
                             d.getDayOfWeek() == DayOfWeek.SUNDAY)
                .collect(Collectors.toList());

        System.out.println("Weekend Dates = " + weekends);
    }
}
