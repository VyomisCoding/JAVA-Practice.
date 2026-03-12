import java.util.*;

public class StudentRecordSystem {

    private static Map<String, Map<String, Integer>> records = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) { 
            String line = sc.nextLine().trim();
            if(line.equals("EXIT")) break;

            String[] parts = line.split(" ");

            String command = parts[0];

            switch(command){
                case "ADD":
                    if(parts.length != 4){
                        System.out.println("INVALID REQUEST");
                        break;
                    }
                    addRecord(parts[1], parts[2], Integer.parseInt(parts[3]));
                    break;
                
                case "REMOVE":
                    if (parts.length != 3) {
                        System.out.println("INVALID REQUEST");
                        break;
                    }
                    removeSubject(parts[1], parts[2]);
                    break;

                case "TOP":
                    if (parts.length != 2) {
                        System.out.println("INVALID REQUEST");
                        break;
                    }
                    topSubject(parts[1]);
                    break;

                case "STUDENT_TOP":
                    if (parts.length != 2) {
                        System.out.println("INVALID REQUEST");
                        break;
                    }
                    studentTop(parts[1]);
                    break;

                case "AVERAGE":
                    if (parts.length != 2) {
                        System.out.println("INVALID REQUEST");
                        break;
                    }
                    averageSubject(parts[1]);
                    break;

                case "DISPLAY":
                    displayRecords();
                    break;
                
                default:
                    System.out.println("INVALID REQUEST");

            }
        }
    }

        // ADD Student SUBJECT MARKS
        private static void addRecord(String student, String subject, int marks){
            if (!records.containsKey(student)){
                records.put(student, new HashMap<>());
            }
            Map<String, Integer> subjects = records.get(student);
            
            if (!subjects.containsKey(subject)) {
                subjects.put(subject, marks);
            } else {
                int oldMarks = subjects.get(subject);
                if (marks > oldMarks) subjects.put(subject, marks);
            }
        }

        // REMOVE Student Subject
    private static void removeSubject(String student, String subject) {

        if (!records.containsKey(student) || !records.get(student).containsKey(subject)) {
            System.out.println("INVALID REQUEST");
            return;
        }

        records.get(student).remove(subject);

        // If no subjects left → remove student
        if (records.get(student).isEmpty()) {
            records.remove(student);
        }
    }

    // TOP Subject → highest marks in subject across all students
    private static void topSubject(String subject) {
        int highest = -1;

        for (String student : records.keySet()) {
            Map<String, Integer> subjects = records.get(student);

            if (subjects.containsKey(subject)) {
                highest = Math.max(highest, subjects.get(subject));
            }
        }

        if (highest == -1)
            System.out.println("SUBJECT NOT FOUND");
        else
            System.out.println(highest);
    }

    // STUDENT_TOP Student → highest scoring subject of the student
    private static void studentTop(String student) {

        if (!records.containsKey(student)) {
            System.out.println("STUDENT NOT FOUND");
            return;
        }

        Map<String, Integer> subjects = records.get(student);

        String topSub = "";
        int maxMarks = -1;

        for (String sub : subjects.keySet()) {
            int marks = subjects.get(sub);
            if (marks > maxMarks) {
                maxMarks = marks;
                topSub = sub;
            }
        }

        System.out.println(topSub + " " + maxMarks);
    }

    // AVERAGE subject
    private static void averageSubject(String subject) {
        int sum = 0;
        int count = 0;

        for (String student : records.keySet()) {
            Map<String, Integer> subjects = records.get(student);

            if (subjects.containsKey(subject)) {
                sum += subjects.get(subject);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("SUBJECT NOT FOUND");
        } else {
            System.out.println(sum / count); // rounded down automatically
        }
    }

    // DISPLAY entire data
    private static void displayRecords() {
        for (String student : records.keySet()) {
            System.out.println(student + " → " + records.get(student));
        }
    }
}

