import java.util.Scanner;

public class Address {
    //address lines
    private String[] addressInfo = {"Street: ", "Town: ", "County: ", "Country: ", "Post Code: "};

    //hardcoded address
    public Address(String street, String town, String county, String country, String code){
        addressInfo[0] = street;
        addressInfo[1] = town;
        addressInfo[2] = county;
        addressInfo[3] = country;
        addressInfo[4] = code;
    }

    //getting an address at runtime
    public Address(Customer customer){
        System.out.println("Enter your address");
        Scanner s = new Scanner(System.in);
        //prints out the contents of the array and then replaces it with the actual information
        for(int i = 0; i < addressInfo.length; i++){
            System.out.printf("%s", addressInfo[i]);
            addressInfo[i] = s.nextLine();
        }

        //customer is always asked if they would like to save the entered address as their default
        if(customer.getUserConfirmation("Save this as your Address?")){
            customer.setAddress(this);
        }
    }

    public String toString(){
        return String.format("%s,\n%s,\n%s,\n%s,\n%s\n\n", addressInfo[0], addressInfo[1], addressInfo[2], addressInfo[3], addressInfo[4]);
    }
}    