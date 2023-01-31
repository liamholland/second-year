import java.util.Scanner;

public class IO {
    /*
     * This function is designed only to take integer input
     * It is mainly focused on selecting objects from arrays, since this assignment deals with a lot of that
     * The array param can be left as null, however, which will simply allow for an integer input to be received
     */
    public static int getIntegerInput(String message, Object[] options){
        System.out.println(message);
        Scanner s = new Scanner(System.in);
        if(options != null){
            if(options.length > 0){
                int buffer = options.length / 2;    //the buffer is used for formatting the UI
                for(int i = 0; i < buffer; i++){
                    System.out.printf("[%d] %s\t\t[%d] %s\n", i + 1, options[i], i + 1 + buffer, options[i + buffer]);
                }
                //have to deal with this becuase of integer division
                if(buffer * 2 < options.length){
                    System.out.printf("[%d] %s\n", (buffer * 2) + 1, options[options.length - 1]);
                }
                System.out.print("> ");
                int out = s.nextInt();
                while(out <= 0 || out > options.length){    //makes sure it wont return an out of bounds error
                    System.out.printf("Enter a number of one of the options\n> ");
                    out = s.nextInt();
                }
                return out - 1; //automatically decrements input so that its a useable index right away
            }
            else{
                //error
                return -1;
            }
        }
        else{
            System.out.print(">");
            return s.nextInt(); //get integer input
        }
    }

    //another function for getting string input
    public static String getStringInput(String message){
        System.out.println(message);
        Scanner s = new Scanner(System.in);
        System.out.print(">");
        return s.nextLine();
    }
}
