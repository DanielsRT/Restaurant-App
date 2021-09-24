public class MenuItem {
    private String description;
    private Double price;
    
    public MenuItem() {
        description = "";
        price = 0.0;
    }
    
    public MenuItem(String data_line) {
        String[] parts = data_line.split(",");
        description = parts[0];
        price = Double.parseDouble(parts[1]);
    }
    
    public String toString() {
        String retStr = String.format("%-30s  $%-2.2f",description,price);
        return retStr;
    }
    
    public String getDescription(){return description;}
    public Double getPrice(){return price;}
}