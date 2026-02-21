import java.util.*;

public class UserInterface{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TrailUtil util = new TrailUtil();
        
        // 1: ADD TRAILS------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Enter the number of trails to be added");
        int n = Integer.parseInt(sc.nextLine());
        System.out.println("Enter trail details");
        for(int i=0;i<n;i++){
            String input = sc.nextLine();
            String[] arr = input.split(":");
            Trail t = new Trail(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
            util.addTrailRecord(t);
        }

        
        // 2 SEARCH TRAIL------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Enter the Trail Id to check hike status");
        String id = sc.nextLine();
        Trail found = util.getTrailById(id);
        if(found == null){
            System.out.println("Trail Id " + id + " not found");
        }else{
            System.out.println(found.getTrailId() + " | " + found.getName() + " | " + found.getRegion() + " | " + found.getDifficulty() + " | " + found.getHikeCount() + " hikes");
        }

        
        // 3 MOST HIKED-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Most hiked trails are");
        for (Trail t : util.getMostHikedTrails()) {
            System.out.println(t.getTrailId() + " | " + t.getName() + " | " + t.getRegion() + " | " + t.getDifficulty() + " | " + t.getHikeCount() + " hikes");
        }

        
        // 4 REGION WISE STATS --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Region-wise hike counts");
        Map<String, Integer> regionMap = util.getHikeCountByRegion();
        for(String region : regionMap.keySet()){
            System.out.println(region + ": " + regionMap.get(region) + " hikes");
        }

        // 5 GROUP BY DIFFICULTY--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Trails grouped by difficulty");
        Map<String, List<Trail>> diffMap = util.groupTrailsByDifficulty();
        for(String diff : diffMap.keySet()){
            System.out.println(diff);
            for(Trail t : diffMap.get(diff)){
                System.out.println(t.getTrailId() + " | " + t.getName() + " | " + t.getRegion() + " | " + t.getDifficulty() + " | " + t.getHikeCount() + " hikes");
            }
        }

        // 6 UPDATE HIKE COUNT--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Enter Trail Id to update hikes and additional hikes");
        String updateId = sc.next();
        int additional = sc.nextInt();
        sc.nextLine();

        if(util.updateHikeCount(updateId, additional)){
            System.out.println("Updated " + updateId + " by " + additional + " hikes");
            Trail t = util.getTrailById(updateId);
            System.out.println(t.getTrailId() + " | " + t.getName() + " | " + t.getRegion() + " | " + t.getDifficulty() + " | " + t.getHikeCount() + " hikes");
        }

        
        // 7 FILTER--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Filter trails by region and difficulty");
        String region = sc.next();
        String difficulty = sc.next();
        sc.nextLine();
        for(Trail t : util.filterTrails(region, difficulty)){
            System.out.println(t.getTrailId() + " | " + t.getName() + " | " + t.getRegion() + " | " + t.getDifficulty() + " | " + t.getHikeCount() + " hikes");
        }

        // 8 TOP-N--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Top 1 trails per region");
        Map<String, List<Trail>> topMap = util.getTopTrailsByRegion(1);
        for(String reg : topMap.keySet()){
            System.out.println(reg);
            for(Trail t : topMap.get(reg)){
                System.out.println(t.getTrailId() + " | " + t.getName() + " | " + t.getRegion() + " | " + t.getDifficulty() + " | " + t.getHikeCount() + " hikes");
            }
        }

        
        // 9 DIFFICULTY STATS--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Difficulty statistics");
        Map<String, String> stats = util.getDifficultyStats();
        for(String diff : stats.keySet()){
            System.out.println(diff + ": " + stats.get(diff));
        }

    }
}