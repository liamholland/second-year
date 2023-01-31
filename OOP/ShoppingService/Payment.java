import java.time.YearMonth;
import java.util.Scanner;

public class Payment {
    private String cusName;
    private String type;
    private int cardNum;
    private YearMonth expiry;
    private Address billingAddress;

    public Payment(String name, int num, String type, int expYear, int expMonth, Address address){
        cardNum = num;
        cusName = name;
        this.type = type;
        expiry = YearMonth.of(expYear, expMonth);
        billingAddress = address;
    }
    
    //construct the payment
    public Payment(Customer customer){
        Scanner s = new Scanner(System.in);

        System.out.println("Enter your payment details:");
        if(customer.getUserConfirmation("Card Name Same as Customer?")){
            cusName = customer.getName();
        }
        else{
            System.out.print("Enter Cardholder name: ");
            cusName = s.nextLine();
        }
        
        System.out.print("Enter type: ");
        type = s.nextLine();
        System.out.print("Enter Card Number (8 Digits): ");
        cardNum = s.nextInt();
        System.out.print("Enter Expiry year (YYYY): ");
        int year = s.nextInt();
        System.out.print("Enter expiry month (MM): ");
        int month = s.nextInt();
        expiry = YearMonth.of(year, month);
        
        System.out.println("-Billing Address-");
        billingAddress = customer.getAddress();
        if(customer.getUserConfirmation("Save these Payment Details?")){
            customer.setPayment(this);
        }
    }

    public String getName(){
        return cusName;
    }
    
    public Address getAddress(){
        return billingAddress;
    }

    //check if the payment has valid details
    public boolean isValid(){
        return ((cardNum >= 10000000 && cardNum <= 99999999 ) && 
            (type.equals("MasterCard") || type.equals("Visa")) &&
            expiry.compareTo(YearMonth.now()) > 0);
    }
}