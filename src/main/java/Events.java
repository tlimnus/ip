public class Events extends Task{
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        String[] part1 = from.trim().split("\\s+", 2);
        String[] part2 = to.trim().split("\\s+", 2);
        this.from = part1[1];
        this.to = part2[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
