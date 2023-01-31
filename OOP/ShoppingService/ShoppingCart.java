import java.util.ArrayList;

class ShoppingCart{
    private ArrayList<Item> items;
    private boolean locked;

    public ShoppingCart(){
        items = new ArrayList<>();
        locked = false; //unlocked to start
    }

    //sets the lock to the desired setting
    public void setOpen(boolean open){
        locked = open;
    }

    //adds or removes an item from the cart
    //mode can be positive to add or negative to remove
    public void modifyCart(Item item, int mode){
        if(!locked){    //if it is unlocked
            if(items.contains(item)){   //and the cart contains the item
                Item itemRef = items.get(items.indexOf(item));  //get a reference to the item in the list
                if(mode > 0){   //check if the mode is add
                    itemRef.quantity++; //increment the quantity
                    System.out.print("Added: ");
                }
                else if(mode < 0){  //or if it is remove
                    itemRef.quantity--; //decrement the quantity
                    if(itemRef.quantity <= 0){  //if that was all of them
                        items.remove(itemRef);  //remove the item entirely from the list
                    }
                    System.out.print("Removed: ");
                }
            }
            else if(mode > 0){  //or the cart doesnt contain the item and you try to add
                items.add(item);    //add the item
                System.out.print("Added: ");
            }
            else{   //or it doesnt contain it and you try to remove
                System.out.println("Error: No such item in shopping cart");
                return;
            }
            //print the info about the modified item
            System.out.printf("%s for $%.2f\nNew total is $%.2f\n", item.toString(), item.getPrice(), getTotal());
        }
        else{ //otherwise its locked and cant be modified
            System.out.println("The cart is currently locked\n");
        }
    }

    //return the items in the list in an array - simpler data structure
    public Item[] getItems(){
        return items.toArray(new Item[0]);
    }

    //return the total of the currently held items
    public float getTotal(){
        float total = 0F;
        for (Item item : items) {
            total += item.getPrice() * item.quantity;   //price times quantity
        }
        return total;
    }

    //remove all the items from the cart and reopen it
    public void clear(){
        for(int i = 0; i < items.size(); i++){
            items.remove(i);
        }
        setOpen(true);
    }

    //print out the contents of the cart
    public void printList(){
        System.out.println("\nShopping List:");
        for(Item item : items){
            System.out.printf("%s x%d - $%.2f\n", item.toString(), item.quantity, item.getPrice() * item.quantity);
        }
        System.out.printf("Total = $%.2f\n\n", getTotal());
    }
}