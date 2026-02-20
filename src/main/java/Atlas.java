import java.util.Scanner;
import java.util.*;
import java.io.*;


public class Atlas {

    public static void loadData(File file, List<Task> li) {
        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                if (type.equals("T")) {
                    Todo t = new Todo(parts[2]);
                    if (isDone) t.markAsDone();
                    li.add(t);
                }
                else if (type.equals("D")) {
                    Deadline d = new Deadline(parts[2], parts[3]);
                    if (isDone) d.markAsDone();
                    li.add(d);
                }
                else if (type.equals("E")) {
                    Events e = new Events(parts[2], parts[3], parts[4]);
                    if (isDone) e.markAsDone();
                    li.add(e);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found.");
        }
    }

    public static void saveData(File file, List<Task> li) {
        try {
            FileWriter fw = new FileWriter(file);

            for (Task t : li) {
                fw.write(t.toFileFormat() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static void main(String[] args) throws AtlasException {
        Scanner sc = new Scanner(System.in);
        List<Task> li = new ArrayList<Task>();
        File dFile = new File("data/datafile.txt");
        dFile.getParentFile().mkdirs();
        try{
            if (dFile.createNewFile()){
                System.out.println("...Data Storage Created...");
            } else {
                System.out.println("...Data Storage Loaded...");
            }
        } catch (IOException e) {
            System.out.println("Error Loading!");
            e.printStackTrace();
        }

        loadData(dFile, li);

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

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (Task text : li) {
                        System.out.println(text.toString());
                    }
                    continue;
                }

                // |Mark or Unmark| ____________________________________________________________

                if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                    String[] parts = input.trim().split("\\s+");
                    String num = parts[1];
                    int index = Integer.parseInt(num) - 1;
                    li.get(index).markAsDone();
                    saveData(dFile, li);
                    if (input.startsWith("u")) { // Assumes user doesn't mark something that is already marked and vice versa
                        System.out.println("OK, I've marked this task as not done yet:");
                    } else {
                        System.out.println("Nice! I've marked this task as Done:");
                    }
                    System.out.println("[" + li.get(index).getStatusIcon() + "] " + li.get(index).description);
                    continue;
                }

                // |Week 4 Functions| _____________________________________________________________
                String[] parts = input.trim().split("\\s+", 2);
                try {
                    if (input.startsWith("todo")) {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new AtlasException("HEYHEYHEY, YOU DIDNT WRITE ANYTHING SIA");
                        }
                        li.add(new Todo(parts[1]));
                        System.out.println("Todo Added: " + li.get(li.size() - 1).toString());
                        System.out.println("You now have " + li.size() + " tasks.");
                        saveData(dFile, li);
                        continue;
                    }
                } catch(AtlasException e){
                    System.out.println("OI CANT BE EMPTY");
                    continue;
                }
                if (input.startsWith("deadline")) {
                    String[] deadlineparts = parts[1].split("/");
                    li.add(new Deadline(deadlineparts[0].trim(), deadlineparts[1]));
                    System.out.println("Deadline Added: " + li.get(li.size() - 1).toString());
                    System.out.println("You now have " + li.size() + " tasks.");
                    saveData(dFile, li);
                    continue;
                }
                if (input.startsWith("event")) {
                    String[] eventparts = parts[1].split("/");
                    li.add(new Events(eventparts[0].trim(), eventparts[1], eventparts[2]));
                    System.out.println("Todo Added: " + li.get(li.size() - 1).toString());
                    System.out.println("You now have " + li.size() + " tasks.");
                    saveData(dFile, li);
                    continue;
                }

                System.out.println("OI! I DONT UNDERSTAND! USE A PROPER COMMAND");


        }

        sc.close();
    }
}
