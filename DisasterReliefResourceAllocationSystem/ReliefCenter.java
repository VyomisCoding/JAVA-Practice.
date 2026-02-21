
import java.util.*;

public class ReliefCenter{
    private String centerId;
    private Map<String,Integer> resources;

    public ReliefCenter(String centerId){
        this.centerId = centerId;
        this.resources = new HashMap<>();
    }

    public String getCenterId(){
        return centerId;
    }

    public Map<String,Integer> getResources(){
        return resources;
    }

    // add resources
    public void addResource(String item,int quantity){
        resources.put(item, resources.getOrDefault(item,0)+quantity);
    }

    // allocate resources
    public void allocateResource(String item, int quantity) throws InsufficientResourceException{
        int available = resources.getOrDefault(item, 0);

        if(available < quantity){
            throw new InsufficientResourceException("Insufficient " + item + " in center " + centerId);
        }

        resources.put(item,available - quantity);
    }

}
