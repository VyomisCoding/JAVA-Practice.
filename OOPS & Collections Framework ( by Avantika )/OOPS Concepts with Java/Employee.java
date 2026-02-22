class Employee{
    // attributes
    private String name;
    private String jobTitle;
    private double salary;

    // constructor
    public Employee(String name, String jobTitle, double salary){
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    // method to calculate salary after increment
    public double calculateSalary(double incrementPercent){
        return salary + (salary * incrementPercent) / 100;
    }

    // method to update salary
    public void updateSalary(double newSalary){
        this.salary = newSalary;
        System.out.println("Updated Salary = " + this.salary);
    }

    // display employee details
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Salary: " + salary);
    }

    // main
    public static void main(String[] args) {
        Employee emp = new Employee("Rahul", "Manager", 50000);
        emp.display();

        double incremented = emp.calculateSalary(10);
        System.out.println("Salary after 10% increment = " + incremented);

        emp.updateSalary(incremented);
    }
}