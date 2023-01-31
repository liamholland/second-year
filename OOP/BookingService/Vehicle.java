public class Vehicle {
    private String name;
    private int numSeats;
    
    public Vehicle(String name, int numSeats){
        this.name = name;
        this.numSeats = numSeats;
    }

    //get the number of seats
    public int getSeats(){
        return numSeats;
    }

    @Override
    public String toString(){
        return name;
    }
}
