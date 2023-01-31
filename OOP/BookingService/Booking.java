public class Booking {
    private String patronName;  //name of the person booking
    private int seatsBooked;
    private Stop from;
    private Stop to; 
    private double timeOffset;
    private Route route;

    public Booking(Route r, String name, int numSeats, Stop from, Stop to, double offSet){
        patronName = name;
        seatsBooked = numSeats;
        this.from = from;
        this.to = to;
        timeOffset = offSet;
        route = r;

        route.addBooking(this);
    }

    //the time this booking will get on the transport
    public double getOnTime(){
        return from.validateTime(from.getTime(route.getCode()) + timeOffset);
    }

    //the time this booking will get off the transport
    public double getOffTime(){
        return to.validateTime(to.getTime(route.getCode()) + timeOffset);
    }

    public int getNumSeats(){
        return seatsBooked;
    }

    @Override
    public String toString(){
        return String.format("Booked for %s\nFrom: %s @ %.2f\nTo: %s @ %.2f\nSeats booked: %d\nCost: %.2f\n", 
            patronName, from, getOnTime(), to, getOffTime(), seatsBooked, route.fareToStop(from, to) * seatsBooked);
    }
}
