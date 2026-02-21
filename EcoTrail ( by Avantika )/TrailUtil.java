
import java.util.*;

public class TrailUtil {
    private List<Trail> trailList = new ArrayList<>();
    
    // getters & Setters
    public List<Trail> getTrailList() { return trailList; }
    public void setTrailList(List<Trail> trailList) { this.trailList = trailList; }

    // 1 Add Trail 
    public void addTrailRecord(Trail trail){
        trailList.add(trail);
    }

    // 2. Search by Trail ID
    public Trail getTrailById(String trailId){
        for(Trail t : trailList){
            if(t.getTrailId().equals(trailId)){
                return t;
            }
        }
        return null;
    }

    // 3. Most Hiked Trails---------------------------------------------------------------------------
    public Set<Trail> getMostHikedTrails(){
        Set<Trail> result = new LinkedHashSet<>();
        int max = 0;

        // find max hike count
        for(Trail t : trailList){
            if(t.getHikeCount() > max){
                max = t.getHikeCount();
            }
        }
        // Add all trailshaving max hike count
        for(Trail t : trailList){
            if(t.getHikeCount() == max){
                result.add(t);
            }
        }
        return result;
    }

    // 4. region wise hike count-----------------------------------------------------------------------
    public Map<String, Integer> getHikeCountByRegion(){
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Trail t : trailList){
            map.put(t.getRegion(),map.getOrDefault(t.getRegion(), 0) + t.getHikeCount());
        }
        return map;
    }

    // 5. Group by difficulty---------------------------------------------------------------------------
    public Map<String, List<Trail>> groupTrailsByDifficulty(){
        Map<String, List<Trail>> map = new LinkedHashMap<>();
        for(Trail t : trailList){
            map.putIfAbsent(t.getDifficulty(), new ArrayList<>());
            map.get(t.getDifficulty()).add(t);
        }
        return map;
    }

    //6. update hike count------------------------------------------------------------------------------
    public boolean updateHikeCount(String trailId, int additionalHikes){
        for(Trail t : trailList){
            if(t.getTrailId().equals(trailId)){
                t.setHikeCount(t.getHikeCount() + additionalHikes);
                return true;
            }
        }
        return false;
    }

    // 7. Filter region + difficulty---------------------------------------------------------------------
    public List<Trail> filterTrails(String region, String difficulty){
        List<Trail> result = new ArrayList<>();
        for(Trail t: trailList){
            if(t.getRegion().equals(region) && t.getDifficulty().equals(difficulty)){
                result.add(t);
            }
        }
        return result;
    }

    // 8. Top-N trails by region--------------------------------------------------------------------------
    public Map<String, List<Trail>> getTopTrailsByRegion(int n){
        Map<String, List<Trail>> regionMap = new LinkedHashMap<>();
        // Group by region first
        for(Trail t : trailList){
            regionMap.putIfAbsent(t.getRegion(), new ArrayList<>());
            regionMap.get(t.getRegion()).add(t);
        }
        // Sort each region and pick top N
        Map<String, List<Trail>> topMap = new LinkedHashMap<>();
        for (String region : regionMap.keySet()){
            List<Trail> regionalTrails = regionMap.get(region);
            // Sort by hikeCount desc
            regionalTrails.sort((a, b) -> b.getHikeCount() - a.getHikeCount());
            List<Trail> topN = new ArrayList<>();
            for(int i = 0; i < Math.min(n, regionalTrails.size()); i++){
                topN.add(regionalTrails.get(i));
            }
            topMap.put(region, topN);
        }
        return topMap;
    }


    // 9. Difficulty stats--------------------------------------------------------------------------------
    public Map<String, String> getDifficultyStats(){

        Map<String, Integer> count = new LinkedHashMap<>();
        Map<String, Integer> total = new LinkedHashMap<>();
        Map<String, String> result = new LinkedHashMap<>();
        
        for(Trail t : trailList){
            String d = t.getDifficulty();
            count.put(d, count.getOrDefault(d, 0) + 1);
            total.put(d, total.getOrDefault(d, 0) + t.getHikeCount());
        }
        
        for(String diff : count.keySet()){
            int c = count.get(diff);
            int t = total.get(diff);
            int avg = t / c;
            result.put(diff,"count=" + c + ", totalHikes=" + t + ", averageHikes=" + avg);
        }
        
        return result;
    }
}
