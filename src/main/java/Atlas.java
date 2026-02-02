import java.util.Scanner;
import java.util.*;

public class Atlas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> li = new ArrayList<Task>();

        String logo =
                "   ___  _______    ___    _____ \n"
                        + "  / _ |/_  __/ /   / _ |  / ___/ \n"
                        + " / __ | / / / /__ / __ |  \\__ \\  \n"
                        + "/_/ |_|/_/ /____//_/ |_| /____/  \n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        while(true){
            String input = sc.nextLine();

            // |Echo+List| _______________________________________________________

            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")){
                for (Task text : li){
                    System.out.println("[" + text.getStatusIcon() + "] " + text.description);
                }
                continue;
            }

            // |Mark or Unmark| ____________________________________________________________

            if (input.startsWith("mark ") || input.startsWith("unmark ")){
                String[] parts = input.trim().split("\\s+");
                String num = parts[1];
                int index = Integer.parseInt(num) - 1;
                li.get(index).markAsDone();
                if (input.startsWith("u")){ // Assumes user doesn't mark something that is already marked and vice versa
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                else{
                    System.out.println("Nice! I've marked this task as Done:");
                }
                System.out.println("[" + li.get(index).getStatusIcon() + "] " + li.get(index).description);
                continue;
            }
            li.add(new Task(input));
            System.out.println("added: " + input);

        }

        sc.close();
    }
}
