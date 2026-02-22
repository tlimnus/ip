package Main;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        String[] parts = by.trim().split("\\s+", 2);
        this.by = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
