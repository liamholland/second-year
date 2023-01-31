public class Test {
    // test vehicles
    public static Vehicle coach = new Vehicle("Coach", 53);
    public static Vehicle train = new Vehicle("Train", 150);
    public static Vehicle car = new Vehicle("Car", 4);
    public static Vehicle cityBus = new Vehicle("City Bus", 70);

    public static Vendor v1 = new Vendor("Bus Eireann", coach);
    public static Vendor v2 = new Vendor("City Link", coach);
    public static Vendor v3 = new Vendor("Iarnroid Eireann", train);
    public static Vendor v4 = new Vendor("Galway Bus", cityBus);

    // test stops
    public static Stop s1 = new Stop("Galway");
    public static Stop s2 = new Stop("Dublin");
    public static Stop s3 = new Stop("Athlone");
    public static Stop s4 = new Stop("Shannon Airport");
    public static Stop s5 = new Stop("Cork");
    public static Stop s6 = new Stop("Ardrahan");
    public static Stop s7 = new Stop("Limerick");
    public static Stop s8 = new Stop("Belfast");
    public static Stop s9 = new Stop("Eyre Square");
    public static Stop s10 = new Stop("Salthill");
    public static Stop s11 = new Stop("Newcastle");
    public static Stop s12 = new Stop("Oranmore");

    // test routes
    public static Route r1 = new Route(new Stop[] { s9, s1, s12, s6, s5 }, 11.20, 2, 1, 8, true, 5);
    public static Route r2 = new Route(new Stop[] { s1, s3, s2 }, 9.00, 2.5, 3.5, 3, true, 6);
    public static Route r3 = new Route(new Stop[] { s9, s10, s11, s12 }, 7.30, 1, 0.33, 10, false, 1);

    public static void main(String[] args) {
        // add some routes to some vendors
        v1.addRoute(r1, true);
        v2.addRoute(r2, true);
        v3.addRoute(r2, true);
        v4.addRoute(r3, true);

        // initialise the service - with the vendors
        TravelIreland t = new TravelIreland(new Vendor[] { v1, v2, v3, v4 });
        t.run(); // run the service
    }
}
