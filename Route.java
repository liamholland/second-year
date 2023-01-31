import java.util.ArrayList;
import java.util.Random;

public class Route {
    private int routeCode;

    private ArrayList<Stop> stops = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    private double runInterval; // number of hours between trips
    private double timeBetweenStops;
    private double fare;    //fare is per stop
    private int numRuns; // number of trips in a day
    private boolean bookable;

    public Route(Stop[] stopSet, double initTime, double duration, double runsEveryHours, int numRuns, boolean bookable, double farePerStop) {
        Random r = new Random();
        routeCode = r.nextInt(100, 999);    //sets a route code
        
        timeBetweenStops = duration / (stopSet.length - 1); //sets the time between each stop ( -1 to not count the first stop)

        /*
         * adds each stop to the stops list
         * sets the time on each one first by entering the route code and
         * finding the time the stop will be reached based on the initial time, the time between stops and the stop number
         */
        for (int i = 0; i < stopSet.length; i++) {
            stopSet[i].setTime(routeCode, initTime + (timeBetweenStops * i));
            stops.add(stopSet[i]);
        }

        runInterval = runsEveryHours;
        this.numRuns = numRuns;
        this.bookable = bookable;
        fare = farePerStop;
    }

    //add the booking
    public void addBooking(Booking b) {
        bookings.add(b);
    }

    public double timeToStop(Stop from, Stop to) {
        return timeBetweenStops * (stops.indexOf(to) - stops.indexOf(from));
    }

    // add stop in between two specific stops
    public void addStop(Stop s, Stop after) {
        for (int i = 0; i < stops.size(); i++) {
            if (stops.get(i).equals(after)) {
                stops.add(i + 1, s);
                break;
            }
        }
    }

    // remove stops
    public void removeStop(Stop s) {
        stops.remove(s);
    }
    
    public double fareToStop(Stop from, Stop to){
        return fare * (stops.indexOf(to) - stops.indexOf(from));
    }

    public double getRunInterval(){
        return runInterval;
    }

    public Stop[] getStops() {
        return stops.toArray(new Stop[0]);
    }

    public int getNumRuns() {
        return numRuns;
    }

    public int getCode(){
        return routeCode;
    }

    public boolean isBookable(){
        return bookable;
    }


    public Double[] getTimes(Stop s){
        ArrayList<Double> times = new ArrayList<>();
        for(int i = 0; i < numRuns; i++){
            times.add(s.validateTime(s.getTime(routeCode) + (runInterval * i)));
        }
        return times.toArray(new Double[0]);
    }

    //returns the number of seats available at the time you are getting on at
    public int getAvailableSeats(double time, int vehicleSeats) {
        int sold = 0;
        for (Booking booking : bookings) {
            //if the time youre trying to book for is in the range of another booking
            if(time >= booking.getOnTime() && time <= booking.getOffTime()){
                sold += booking.getNumSeats();
            }
        }
        return vehicleSeats - sold;
    }

    @Override
    public String toString() {
        return String.format("From %s To %s", stops.get(0), stops.get(stops.size() - 1));
    }
}
