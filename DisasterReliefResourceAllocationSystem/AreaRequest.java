

public class AreaRequest{

    private String areaName;
    private String item;
    private int quantity;

    public AreaRequest(String areaName, String item, int quantity){
        this.areaName = areaName;
        this.item = item;
        this.quantity = quantity;
    }

    public String getAreaName(){
        return areaName;
    }

    public String getItem(){
        return item;
    }

    public int getQuantity(){
        return quantity;
    }
    
}
