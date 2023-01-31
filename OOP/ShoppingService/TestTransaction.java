public class TestTransaction {
    //items for tests
    private static Item item1 = new Item("Item 1", 20.3F);
    private static Item item2 = new Item("Item 2", 19.99F);
    private static Item item3 = new Item("Item 3", 10.2F);
    private static Item item4 = new Item("Item 4", 4F);

    public static void main(String[] args) {
        System.out.println("--Scenario 1--");
        scenario1();
        System.out.println("--Scenario 2--");
        scenario2();
    }

    //a returning customer
    public static void scenario1(){
        Address a = new Address("a", "b", "c", "d", "e");   //saved address
        Payment p = new Payment("Liam Holland",12345678, "Visa", 2024, 06, a);     //saved payment details
        Customer cus1 = new Customer("Liam Holland", "liam.holland5@nuigalway.ie", a, p);  //customer created

        cus1.startShopping();   //start shopping
        cus1.addItem(item1);    //add an item
        cus1.addItem(item2);    //add a different item
        cus1.addItem(item2);    //add a replicated item
        cus1.checkout();        //checkout
    }

    //a new customer
    public static void scenario2(){
        Customer cus2 = new Customer("Liam Holland");   //customer created

        cus2.startShopping();   //start shopping
        cus2.addItem(item4);    //add an item
        cus2.addItem(item3);    //add a different item
        cus2.addItem(item3);    //add a replicated item
        cus2.addItem(item1);    //add another different item
        cus2.removeItem(item2); //remove an item that wasnt added
        cus2.viewCart();        //view the shopping cart
        cus2.removeItem(item3); //remove a replicated item
        cus2.checkout();        //checkout
    }
}
