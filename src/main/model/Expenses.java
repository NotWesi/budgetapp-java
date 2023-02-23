package model;

import java.util.ArrayList;

// Represents expenses with a list of entry
// each having a description and the corresponding amount
public class Expenses {
    private ArrayList<Entry> entries;

    // EFFECTS: budget is a list of entries
    public Expenses() {
        entries = new ArrayList<>();
    }

    // getter and setter methods for entry

    // EFFECTS: adds a new entry
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    // EFFECTS: returns a specific entry
    public Entry getEntry(int index) {
        return entries.get(index);
    }

    // EFFECTS: returns list of entries
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    // MODIFIES: entry
    // EFFECTS: modifies amount of entry in expenses to new amount
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

