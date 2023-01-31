import java.util.ArrayList;

public class TravelIreland {
    //vendors are the only thing this class needs to store
    private ArrayList<Vendor> vendors = new ArrayList<>();

    //internal commands
    private String[] functions = {
        "Display Vendors",
        "Display Vendor Routes",
        "Display Route Times",
        "Book Trip",
        "Exit"
    };

    //the constructor add the vendors to the arraylist
    public TravelIreland(Vendor[] vendors){
        for(int i = 0; i < vendors.length; i++){
            this.vendors.add(vendors[i]);
        }
    }

    //the run function gets the initial use input
    public void run(){
        while(true){
            int input = IO.getIntegerInput("\nSelect function:", functions);
            switch(input){
                case 0:
                    displayVendors();
                    break;
                case 1:
                    vendorFunction(0);
                    break;
                case 2:
                    vendorFunction(1);
                    break;
                case 3:
                    vendorFunction(2);
                    break;
                case 4:     //case 4 exits the program
                    return;
                default:
                    break;
            }
        }
    }

    //query available vendors
    private void displayVendors(){
        for(Vendor v : vendors){
            System.out.println(v);
        }
    }

    /*
     * The vendor function is a refactoring of three nearly identical functions
     * It allows for the code to be simpler and not have repeated lines of code
     * First the user selects a vendor, and then the vendor called performs a function
     * Modes:
     *  0: display the vendor routes
     *  1: display the times from the vendor
     *  2: make a booking with the vendor
     */
    private void vendorFunction(int mode){
        Vendor v = vendors.get(IO.getIntegerInput("Select a Vendor", vendors.toArray()));
        switch(mode){
            case 0:
                v.displayRoutes();
                break;
            case 1:
                v.displayTimes();
                break;
            case 2:
                v.bookSeats();
                break;
            default:
                break;
        }
    }
}
