package Main;

import java.io.*;
import java.util.*;

public class Storage {
    private String filepath;

    public Storage(String filepath){
        this.filepath = filepath;
    }

    public void load() throws IOException {
        try {
            File file = new File(filepath);
            List<Task> tasksi = new ArrayList<>();
            Scanner sc = new Scanner(file);
            if(file.createNewFile()){
                System.out.println("File created and loaded.");
            } else {
                System.out.println("File loaded.");
            }
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] parts = line.trim().split("|");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

    }
}
