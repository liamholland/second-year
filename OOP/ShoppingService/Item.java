import java.util.Random;

public class Item {
    private String name;
    private int itemID;
    private float price;
    public int quantity;    //modifiable property is public

    public Item(String n, float p){
        name = n;
        itemID = assignID();
        price = p;
        quantity = 1;
    }

    //generate a 3 digit item ID
    private int assignID(){
        Random r = new Random();
        return r.nextInt(999 - 100) + 100;
    }

    public float getPrice(){
        return price;
    }

    public String toString(){
        return String.format("%s (%d)", name, itemID);
    }
}
