
public class StreamFilterExample {

    public static void main(String[] args){
        
        // List<Integer> numbers = Arrays.asList(10, 15, 20, 25);

        // numbers.stream()
        //        .filter(n -> n > 15)
        //        .forEach(System.out::println);

        // List<String> names = Arrays.asList("ram","shyam");
        // names.stream().map(String::toUpperCase).forEach(System.out::println);

        // List<Integer> list = Arrays.asList(5,2,8,1);
        // list.stream().sorted().forEach(System.out::println);

        // convert stream back to list
        // List<Integer> even = list.stream().filter(n -> n%2 == 0).collect(Collectors.toList());

        // used to combine all elementes in single value
        // List<Integer> list = Arrays.asList(1,2,3,4);
        // int sum = list.stream().reduce(0,(a,b) -> a+b);
        // System.out.println(sum);

        // salary greater than 50000
        // List<Employee> employees = Arrays.asList(
        //     new Employee("Ram",40000),
        //     new Employee("Ram",60000),
        //     new Employee("Ram",70000)
        // );

        //employees.stream().filter(e -> e.getSalary() > 50000).map(Employee::getName).forEach(System.out::println);


        // String[] names = {"Ram","Shyam","Amit"};
        // Arrays.stream(names).forEach(System.out::println);

        // Arrays.stream(names).filter(name -> name.length() > 3).map(String::toUpperCase).forEach(System.out::println);

        // int[] numbers = {10,20,30};
        // int sum = Arrays.stream(numbers).sum();
        // System.out.println(sum);

        // int[] numbers = {10,20,30,40,50};
        // Arrays.Stream(numbers,1,4).forEach(System.out.println);


        // now collectors-------------------------------------------------------------------------------------------------------------------------------------------------------

        // toList()

        // List<Integer> list = Arrays.asList(1,2,3,4);
        // List<Integer> even = list.stream().filter(n -> n%2 == 0).collect(Collectors.toList());
        // System.out.println(even);

        // toSet() -- remove duplicates
        // List<Integer> list = Arrays.asList(1,2,2,3,3);
        // Set<Integer> set = list.stream().collect(Collectors.toSet());
        // System.out.println(set);

        // toMap() -- convert steam to map
        // List<Student> students = Arrays.asList(
        //     new Student(1,"Ram"),
        //     new Student(2,"Shyam")
        // );
        // Map<Integer,String> map = students.stream().collect(Collectors.toMap(Student::getId,Student::getName));
        // System.out.println(map);


        // group students by name length
        // Map<Integer,List<String>> grouped = names.stream().collect(Collectors.groupingBy(String::length));
        // System.out.println(grouped);


        // group employee by salary

        // List<Employee> employees = Arrays.asList(
        //     new Employee("A",50000),
        //     new Employee("A",60000),
        //     new Employee("A",50000)
        // );
        // Map<Integer,List<Employee>> group = employees.stream().collect(Collectors.groupingBy(Employee::getSalary));
        // System.out.println(group);


        // mapping() --Downstream Collector -- groups by salary but collects only names
        
        // Map<Integer,List<String>> result = employees.stream().collect(Collectors.groupingBy(Employee::getSalary,Collectors.mapping(Employee::getName,Collectors.toList()))); 

        
        
        // collectingAndThen() -- used to modify final result

        // List<Integer> unmodifiable = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(),Collections::unmodifiableList));


        // find highest paid employee per salary group
        // Map<Integer,Optional<Employee>> highest = employees.stream().collect(Collectors.groupingBy(Employee::getSalary,Collectors.maxby(Comparator.comparing(Employee::getName))));



        // Group employees by salary

        // List<Employee> employees = Arrays.asList(
        //     new Employee("A",50000),
        //     new Employee("B",60000),
        //     new Employee("C",50000)
        // );

        // Map<Integer,List<Employee>> group = employees.stream().collect(Collectors.groupingBy(Employee::getSalary));
        // System.out.println(group);
    }
}
