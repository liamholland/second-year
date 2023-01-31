import java.util.ArrayList;
import java.util.Arrays;

public class Vendor {
    private String name;
    private ArrayList<Route> routes = new ArrayList<>();    //stores the routes
    private Vehicle defaultVehicle;     //default vehicle is basically just used to get the number of seats at the moment

    public Vendor(String name, Vehicle vehicle){
        this.name = name;
        defaultVehicle = vehicle;
    }

    /*
     * This powerhouse of a function is what allows the use to select the specific time and route they want to book
     * It is large mainly because of the amount of information it needs to get
     */
    public void bookSeats(){
        int seats = defaultVehicle.getSeats();  //start by getting the number of seats for later

        //leave the function if there are no routes to display
        if(!(routes.size() > 0)){
            System.out.println("No routes to display");
            return;
        }

        //get a reference to the desired Route object
        Route route = routes.get(IO.getIntegerInput("Select a Route", routes.toArray()));

        //leave the function if this route cannot be booked
        if(!route.isBookable()){
            System.out.println("This route cannot be booked");
            return;
        }

        //get references to the stops
        Stop[] stops = route.getStops();    //array of stops
        int fSelIndex = IO.getIntegerInput("Select a stop to go from", stops); //index of "from" selection
        Stop fSel = stops[fSelIndex];   //reference to the "from" stop
        Stop[] remainingStops = Arrays.copyOfRange(stops, fSelIndex + 1, stops.length); //let the user choose from all the stops after the "from" stop
        Stop tSel = remainingStops[IO.getIntegerInput("Select a stop to go to", remainingStops)];  //get the reference to the "to" stop
        
        //getting the time that the user wants to travel at
        Double[] times = route.getTimes(fSel);  //gets an array of the "correct" times i.e., the times throughout the day at this stop on this route
        double time = times[IO.getIntegerInput(String.format("Select a time to depart %s", fSel), times)];

        //get the number of available seats and the number of seats the user wants to book
        System.out.printf("There are %d seats available at this time\n", route.getAvailableSeats(time, seats));
        int numSeats = IO.getIntegerInput("Enter number of seats to book", null);
        while(numSeats > route.getAvailableSeats(time, seats) || numSeats < 1){
            numSeats = IO.getIntegerInput(numSeats > 0 ? "Not enough seats available" : "Too few Seats", null);
        }

        //get the users name
        String bookedUnder = IO.getStringInput("Enter your name");

        //finalise the booking
        Booking b = new Booking(route, bookedUnder, numSeats, fSel, tSel, time - fSel.getTime(route.getCode()));
        System.out.printf("Booking Completed\n%s\n", b);
    }

    //query available times
    public void displayTimes(){
        if(!(routes.size() > 0)){
            System.out.println("No routes to display");
            return;
        }

        Route r = routes.get(IO.getIntegerInput("Select a Route", routes.toArray())); //get route reference

        System.out.printf("\nTimes for route %s:\n", r);
        for(Stop s : r.getStops()){
            System.out.printf("\n%s - ", s);
            for(int i = 0; i < r.getNumRuns(); i++){
                //this line formats the output while ensuring that the correct time is displayed
                System.out.printf("%.2f" + (i == r.getNumRuns() - 1 ? "" : ", "), s.validateTime(s.getTime(r.getCode()) + (r.getRunInterval() * i)));
            }
        }
    }

    //query available routes
    public void displayRoutes(){
        if(routes.size() > 0){
            for(int i = 0; i < routes.size(); i++){
                System.out.println(routes.get(i));
            }
        }
        else{
            System.out.println("No routes to display");
        }
    }

    //add routes
    public void addRoute(Route r, boolean runsBothWays){
        routes.add(r);
        if(runsBothWays){
            Stop[] oldStopSet = r.getStops();
            Stop[] newStopSet = new Stop[oldStopSet.length];
            for(int i = 0; i < oldStopSet.length; i++){
                newStopSet[i] = oldStopSet[oldStopSet.length - (1 + i)];
            }
            routes.add(new Route(
                newStopSet, 
                oldStopSet[oldStopSet.length - 1].getTime(r.getCode()), 
                r.timeToStop(oldStopSet[0], oldStopSet[oldStopSet.length - 1]), 
                r.getRunInterval(), 
                r.getNumRuns(), 
                r.isBookable(),
                r.fareToStop(oldStopSet[0], oldStopSet[1])));
        }
    }

    //remove trips
    public void removeRoute(Route r){
        routes.remove(r);
    }

    @Override
    public String toString(){
        return String.format("%s - %s", name, defaultVehicle);
    }
}
