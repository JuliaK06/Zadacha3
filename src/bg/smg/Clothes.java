package bg.smg;

public class Clothes implements Comparable<Clothes>{
    private String  name;
    private String type;

    private double price;

    public Clothes() {
    }

    public Clothes(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  name + " " + " " + type + " " + price ;
    }

    @Override
    public int compareTo(Clothes o) {
        if(getPrice()>o.getPrice()){
            return 1;
        }
        if(getPrice()==o.getPrice()){
            return 0;
        }else
            return -1;
    }
}
