public class Email {
    String content; //holds the content of the email
    
    //constructor literally constructs the content of the email
    public Email(Customer customer, Order order, Payment payment){
        content = String.format("NEW EMAIL!!\nto: %s\nfrom: company@company.com\n\nHi %s (ID: %d),\n",
            customer.getEmail(), customer.getName().substring(0, customer.getName().indexOf(" ")), customer.getID());
    
        if(order.isComplete()){     //check if the order was successful or not
            content += "Thank you for your purchase!\nYou ordered:\n";
            for(Item item : order.getItems()){
                content += String.format("%s x%d - %.2f\n", item.toString(), item.quantity, item.getPrice());
            }
            content += String.format("--------------\nTotal = $%.2f\n\nWill be delivered to:\n%s,\n%s", 
                order.getTotal(), customer.getName(), order.getAddress().toString());
            content += String.format("Will be billed to:\n%s,\n%s", payment.getName(), payment.getAddress().toString());
        }
        else{
            content += "Unfortunately the order was not completed due to an invalid payment.\nSincerely,\nCompany\n\n";
        }
    }

    public void send(){
        System.out.print(content);
    }
}