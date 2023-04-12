package model;

// represents the information that will be stored in the
// budget and expenses array lists, composed of two parts
// name and amount
public class Entry {
    private String name;
    private double amount;

    // EFFECTS: constructs an entry object with a description and associated amount
    public Entry(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    // getter and setter methods for name and amount

    // EFFECTS: returns description
    public String getName() {
        // logs the event that entry description has been returned
        EventLog.getInstance().logEvent(new Event("Entry description has been returned: " + this.name));
        return name;
    }

    // EFFECTS: sets description
    public void setName(String name) {
        this.name = name;
        // logs the event that entry description has been set
        EventLog.getInstance().logEvent(new Event("Entry description has been set: " + name));
    }

    // EFFECTS: returns amount
    public double getAmount() {
        // logs the event that entry amount has been returned
        EventLog.getInstance().logEvent(new Event("Entry amount has been returned: " + this.amount));
        return amount;
    }

    // EFFECTS: sets amount
    public void setAmount(double amount) {
        this.amount = amount;
        // logs the event that entry amount has been set
        EventLog.getInstance().logEvent(new Event("Entry amount has been set: " + amount));
    }

}
