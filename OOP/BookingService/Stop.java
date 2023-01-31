import java.util.Hashtable;
import java.util.Random;

public class Stop {
    private String name;
    private int id;
    
    //a stop can store multiple times for different routes
    private Hashtable<Integer, Double> times = new Hashtable<>();

    public Stop(String name){
        this.name = name;
        Random r = new Random();
        id = r.nextInt(100, 999);   //stops have a randoom id
    }
    
    public int getID(){
        return id;
    }
    
    public double getTime(int code){
        return times.get(code);
    }
    
    //sets the time
    public void setTime(int code, double t){
        times.put(code, t);
    }
    
    //just makes it valid, doesn't do anything with the leftovers
    public double validateTime(double t){
        if(t - Math.floor(t) >= 0.6){
            t = Math.ceil(t);
        }
        return t;
    }
    
    @Override
    public boolean equals(Object obj){
        return obj instanceof Stop && ((Stop)obj).getID() == this.getID();
    }
    
    @Override
    public String toString(){
        return String.format("%s (%d)", name, id);
    }
}
