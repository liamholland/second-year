import java.util.ArrayList;

public class Order {
    private Address deliveryAddress;
    private ArrayList<Item> orderedItems;
    private boolean complete;
    private Customer customer;
    private ShoppingCart cart;
    private Payment payment;
    private Email email;
    private float orderTotal;
    
    //an order object is created which also initialises the potential end of a shopping period
    public Order(Customer customer, ShoppingCart cart, Payment payment){
        this.customer = customer;
        this.cart = cart;
        cart.setOpen(false); //lock cart as soon as possible
        this.payment = payment;

        complete = false;
        
        //items are COPIED from the cart so that the opposite does not have to be done if the order is cancelled
        orderedItems = new ArrayList<>();
        for (Item item : cart.getItems()) {
            orderedItems.add(item);
        }
        
        orderTotal = cart.getTotal();
    }
    
    //finalises the order details
    public void confirm(){
        System.out.println("-Delivery Address-");
        deliveryAddress = customer.getAddress();
        
        System.out.println("-Payment Details-");
        payment = customer.getPayment();
        
        if(payment.isValid()){
            complete = true;
            cart.clear();
        }
        else{
            System.out.println("Invalid Payment!");
            complete = false;
            cancel();
        }
        //send an email to the customer with the result
        email = new Email(customer, this, payment);
        email.send();
    }

    public void cancel(){
        System.out.println("Cancelling...");
        cart.setOpen(true);
    }

    //getter methods

    public boolean isComplete(){
        return complete;
    }

    public Address getAddress(){
        return deliveryAddress;
    }

    public Item[] getItems(){
        return orderedItems.toArray(new Item[0]);
    }

    public float getTotal(){
        return orderTotal;
    }
}
