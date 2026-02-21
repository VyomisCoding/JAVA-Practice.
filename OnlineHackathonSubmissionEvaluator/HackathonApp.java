
import java.util.*;

class LateSubmissionException extends Exception{
    public LateSubmissionException(String message){
        super(message);
    }
}

// participants class --------------------------------------------------

class Participant{
    private int participantId;
    private String name;
    private Map<Integer, Boolean> testCaseResults;      // QuestionID → passed/failed
    private int score;

    public Participant(int participantId, String name){
        this.participantId = participantId;
        this.name = name;
        this.testCaseResults = new HashMap<>();
        this.score = 0;
    }

    //Add test case result
    public void addTestResult(int qid, boolean passed){
        testCaseResults.put(qid,passed);
    }

    // calculate total score
    public void calculateScore(){
        score = 0;
        for(boolean result : testCaseResults.values()){
            if(result) score += 10;
        }
    }

    public int getScore() { return score; }
    public String getName() { return name; }
    public int getParticipantId() { return participantId; }

    @Override
    public String toString(){
        return "ID: " + participantId + ", Name: " + name + ", Score: " + score;
    }
}

// Service class ------------------------------------------------------------------------------------------------------------------

class HackathonSystem{
    private List<Participant> participants = new ArrayList<>();

    public List<Participant> getParticipants() {
    return participants;
}

    //Add participant
    public void addParticipant(Participant p, boolean isLate) throws LateSubmissionException{
        // Throw exception if submission is late
        if(isLate){
            throw new LateSubmissionException("Late submission is not allowed");
        }

        participants.add(p);
        System.out.println("Participant added successfully");
    }

    // Generate leaderboard using Comparator
    public void showLeaderBoard(){
        // sort list based on score(descending)
        participants.sort((a,b) -> Integer.compare(b.getScore(),a.getScore()));
        System.out.println("Leaderboard");
        for(Participant p : participants){
            System.out.println(p);
        }
    }

    // Display all participants
    public void showAllParticipants(){
        if(participants.isEmpty()){
            System.out.println("No participants found");
            return;
        }

        for(Participant p : participants){
            System.out.println(p);
        }
    }
}

// MAIN CLASS -------------------------------------------------------------------------------------------------------------------------------------

public class HackathonApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HackathonSystem system = new HackathonSystem();

        while(true){
            System.out.println("\n===== ONLINE HACKATHON SYSTEM =====");
            System.out.println("1. Add Participant");
            System.out.println("2. Add Test Case Results");
            System.out.println("3. Calculate Scores");
            System.out.println("4. Show Leaderboard");
            System.out.println("5. Show All Participants");
            System.out.println("6. Exit");
            System.out.print("Choose Option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch(choice){
                case 1:
                    try {
                        System.out.print("Enter Participant ID: ");
                        int pid = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Participant Name: ");
                        String name = sc.nextLine();
                        System.out.print("Is Submission Late? (true/false): ");
                        // parseBoolean() for boolean conversion
                        boolean isLate = Boolean.parseBoolean(sc.nextLine());

                        Participant p = new Participant(pid, name);
                        system.addParticipant(p, isLate);
                    } catch (LateSubmissionException e) {
                        System.out.println("The ERROR: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Participant ID for test case: ");
                    int partID = Integer.parseInt(sc.nextLine());

                    Participant found = null;

                    // Find participant
                    for (Participant pp : system.getParticipants()) {
                        if (pp.getParticipantId() == partID) {
                            found = pp;
                            break;
                        }
                    }

                    if (found == null) {
                        System.out.println("Participant not found!");
                        break;
                    }

                    System.out.print("Enter Question ID: ");
                    int qid = Integer.parseInt(sc.nextLine());

                    System.out.print("Test Case Passed? (true/false): ");
                    boolean passed = Boolean.parseBoolean(sc.nextLine());

                    found.addTestResult(qid, passed);
                    System.out.println("Test case added.");
                    break;

                case 3:
                    // Recalculate scores for all participants
                    for (Participant pp : system.getParticipants()) {
                        pp.calculateScore();
                    }
                    System.out.println("Scores updated successfully.");
                    break;

                case 4:
                    system.showLeaderBoard();
                    break;

                case 5:
                    system.showAllParticipants();
                    break;

                case 6:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid Option.");
            }
        }
    }
}