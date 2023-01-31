import java.util.Random;
import java.util.Scanner;
import java.util.regex.*;

public class Customer {
    private ShoppingCart cart;
    private Order order;
    private Address address;
    private Payment payDetails;
    private String name;
    private String email;
    private final long customerID;
    private String pattern = "^(.+)@(.+)$";

    //Returning Customer
    //create the customer with the necessary details
    public Customer(String name, String email, Address address, Payment payment){
        this.name = name;
        this.email = Pattern.compile(pattern).matcher(email).matches() ? email : setEmail();
        this.address = address;
        this.payDetails = payment;
        customerID = assignID();
    }

    //New Customer
    //or create a customer with the bare minimum details i.e. a name
    public Customer(String name){
        this(name, "", null, null);
    }

    //assign an ID to the customer
    private long assignID() {
        Random r = new Random();
        return r.nextInt(99999999 - 1000000) + 10000000;
    }

    //create a new shopping cart object
    public void startShopping(){
        System.out.printf("Welcome %s! Happy Shopping!\n\n", name.substring(0, name.indexOf(" ")));
        cart = new ShoppingCart();
    }

    //when the customer adds an item to the cart
    public void addItem(Item i){
        cart.modifyCart(i, 1);
    }

    //when a customer removes an item from the cart
    public void removeItem(Item i){
        cart.modifyCart(i, -1);
    }

    //view the current cart
    public void viewCart(){
        cart.printList();
    }

    //to checkout, the customer object creates a new order from the shopping cart and confirms it
    public void checkout(){
        System.out.println("\nChecking Out...");
        cart.printList();
        
        if(getUserConfirmation("Complete Order?")){
            order = new Order(this, cart, payDetails);
            order.confirm();
        }
        else{
            System.out.println("Cancelling Order...");
        }
        order = null; //delete the order
    }

    //getter and setter methods
    //Used for input to determine a yes or no answer throughout the program
    public boolean getUserConfirmation(String message){
        Scanner s = new Scanner(System.in);
        System.out.print("\n" + message + " (Y/N) -> ");
        String input = s.nextLine().toUpperCase();
        while(!(input.equals("Y") || input.equals("N"))){
            System.out.print("Invalid Input. Enter Y for Yes or N for No -> ");
            input = s.nextLine().toUpperCase();
        }
        return input.equals("Y");
    }
    
    //new address or a saved one
    public Address getAddress(){
        return (address != null && getUserConfirmation("Use Saved Address?")) ? address : new Address(this);
    }

    public void setAddress(Address a){
        address = a;
    }

    //new payment or a saved one 
    public Payment getPayment(){
        return (payDetails != null && getUserConfirmation("Use Saved Payment Details?")) ? payDetails : new Payment(this);
    }

    public void setPayment(Payment p){
        payDetails = p;
    }

    public String getName(){
        return name;
    }

    public long getID(){
        return customerID;
    }

    public String getEmail(){
        return email;
    }

    //set a VALID email
    private String setEmail(){
        Scanner s = new Scanner(System.in);
        String e = "";
        while(!Pattern.compile(pattern).matcher(e).matches()){
            System.out.println("Enter a valid email:");
            e = s.nextLine();
        }
        return e;
    }
}