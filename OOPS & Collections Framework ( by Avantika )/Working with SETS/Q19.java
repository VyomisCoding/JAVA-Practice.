// Find Employee with Highest Salary from Set

import java.util.*;

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

public class Q19 {
    public static void main(String[] args) {

        Set<Employee> emps = new HashSet<>();
        emps.add(new Employee("Amit", 50000));
        emps.add(new Employee("Rohit", 65000));
        emps.add(new Employee("Sneha", 70000));

        // max() using Comparator
        Employee highest = emps.stream()
                               .max(Comparator.comparing(e -> e.salary))
                               .get();

        System.out.println("Highest Paid = " + highest.name + " (" + highest.salary + ")");
    }
}