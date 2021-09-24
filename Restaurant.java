import java.util.*;

public class Restaurant {
    private String name;
    private String location;
    private List<MenuItem> menu;
    
    public Restaurant() {
        name = "";
        location = "";
        menu = new ArrayList<>();
    }
    
    public Restaurant(String name, String location, List<MenuItem> menu) {
        this.name = name;
        this.location = location;
        this.menu = menu;
    }
      
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s, located at %s\n",name,location));
        int ndx = 0;
        for (MenuItem item : menu) {
            sb.append(String.format("%2d)  %s\n",ndx,item));
            ndx++;
        }
        return sb.toString();
    }
    
    public String getName(){return name;}
    public String getLocation(){return location;}
    public List<MenuItem> getMenu(){return menu;}
 
    
}