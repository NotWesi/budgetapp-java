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
        return name;
    }

    // EFFECTS: sets description
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns amount
    public double getAmount() {
        return amount;
    }

    // EFFECTS: sets amount
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
