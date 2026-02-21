
public class Trail{
    private String trailId;
    private String name;
    private String region;
    private String difficulty;
    private int hikeCount;

    public Trail() {}    // no-arg constructor
    
    // 5-argument constructor (VERY IMPORTANT)
    public Trail(String trailId, String name, String region, String difculty, int hikeCount){      
        this.trailId = trailId;
        this.name=name;
        this.region = region;
        this.difficulty = difficulty;
        this.hikeCount = hikeCount;
    }

    // getters
    public String getTrailId() { return trailId; }
    public String getName() { return name; }
    public String getRegion() { return region; }
    public String getDifficulty() { return difficulty; }
    public int getHikeCount() { return hikeCount; }
    
    // Setters (methods to update values)
    public void setHikeCount(int hikeCount){
        this.hikeCount = hikeCount;
    }
}
