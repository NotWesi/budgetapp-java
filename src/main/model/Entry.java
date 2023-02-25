package model;

import java.util.Objects;

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

    // EFFECTS: checks if entry's name and age are equal
    // SOURCE: this snippet is taken from: INSERT URL HERE
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return amount == entry.amount && Objects.equals(name, entry.name);
    }

}
