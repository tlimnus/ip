import java.util.Scanner;

public class Atlas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo =
                "   ___  _______    ___    _____ \n"
                        + "  / _ |/_  __/ /   / _ |  / ___/ \n"
                        + " / __ | / / / /__ / __ |  \\__ \\  \n"
                        + "/_/ |_|/_/ /____//_/ |_| /____/  \n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        while(true){
            String input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }

        sc.close();
    }
}
