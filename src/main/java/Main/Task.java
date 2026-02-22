package Main;

public class Task {
    protected String description;
    protected Boolean isDone;

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone () {
        if (this.isDone){
            this.isDone = false;
            return;
        }
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "x" : " ");
    }

    public String toFileFormat() {
        return "";
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
