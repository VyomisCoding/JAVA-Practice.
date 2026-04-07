package M1;

import java.util.*;

class ProjectTeam{
    String teamId, section, domain, projectName;
    int projectScore;

    public ProjectTeam(String teamId, String section, String domain, String projectName, int score){
        this.teamId = teamId;
        this.section = section;
        this.domain = domain;
        this.projectName = projectName;
        this.projectScore = score;
    } 
}

public class ProjectManager {
    
    static List<ProjectTeam> list = new ArrayList<>();

    // REGISTER----------------------------------------------------------------------
    static void register(String id, String sec, String dom, String name, int score){
        for(ProjectTeam t : list){
            if(t.teamId.equals(id)) return;
        }
        list.add(new ProjectTeam(id, sec, dom, name, score));
    }

    // REVISE------------------------------------------------------------------------
    static void revise(String id, int score){
        for(ProjectTeam t : list){
            if(t.teamId.equals(id)){
                t.projectScore = score;
                System.out.println("REVISED" + id + " " + score);
                return;
            }
        }
        System.out.println("team is not available");
    }

    // FILTERDOMAIN-----------------------------------------------------------------
    static void filter(String domain){
        boolean found = false;
        for(ProjectTeam t : list){
            if(t.domain.equals(domain)){
                System.out.println(t.teamId + " " + t.section + " " + t.domain + " " + t.projectName + " " + t.projectScore );
                found = true;
            }
        }
        if(!found){
            System.out.println("Team is not available for the domain: " + domain);
        }
    }

    // QUALIFY ---------------------------------------------------------------------
    static void qualify(int cutoff){
        boolean found = false;

        for (ProjectTeam t : list){
            if(t.projectScore >= cutoff){
                System.out.println(t.teamId + " " + t.section + " " + t.domain + " " + t.projectName + " " + t.projectScore);
                found = true;
            }
        }
        if(!found){
            System.out.println("No team qualified");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++){
            String line = sc.nextLine();
            String[] p = line.split(" ");

            if(p[0].equals("REGISTER")){
                register(p[1], p[2], p[3], p[4], Integer.parseInt(p[5]));
            } 
            else if(p[0].equals("REVISE")){
                revise(p[1], Integer.parseInt(p[2]));
            } 
            else if(p[0].equals("FILTERDOMAIN")){
                filter(p[1]);
            } 
            else if(p[0].equals("QUALIFY")){
                qualify(Integer.parseInt(p[1]));
            }
        }
    }
}
