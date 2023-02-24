package model;

import java.util.ArrayList;

// Represents a budget with a list of entry
// each having a description and the corresponding amount
public class Budget {
    private ArrayList<Entry> entries;

    // EFFECTS: budget is a list of entries
    public Budget() {
        entries = new ArrayList<>();
    }

    // getter and setter methods for entry

    // EFFECTS: adds a new entry
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    // EFFECTS: remove an entry
    public void removeEntry(Entry entry) {
        entries.remove(entry);
    }

    // EFFECTS: returns a specific entry
    public Entry getEntry(int index) {
        return entries.get(index);
    }

    // EFFECTS: returns list of entries
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    // EFFECTS: gets the total amount for Budget
    public double getTotalAmount() {
        double monthlyAmount = 0.0;

        for (Entry entry : entries) {
            monthlyAmount += entry.getAmount();
        }

        // returns the monthly amount
        return monthlyAmount;
    }

    // MODIFIES: entry
    // EFFECTS: modifies amount of entry in budget to new amount
    public void modifyEntryAmount(int index, double newAmount) {
        Entry entry = entries.get(index);
        entry.setAmount(newAmount);
    }

    // MODIFIES: entry
    // EFFECTS: modifies description of entry in expenses to new amount
    public void modifyEntryDescription(int index, String newName) {
        Entry entry = entries.get(index);
        entry.setName(newName);
    }
}

