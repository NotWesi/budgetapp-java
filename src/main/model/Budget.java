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

    // REQUIRES: entry
    // MODIFIES: entries
    // EFFECTS: adds a new entry
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    // REQUIRES: name and amount for entry
    // MODIFIES: entries
    // EFFECTS: remove an entry
    public void removeEntry(String name, double amount) {
        // loops through the list based on its size
        for (int i = 0; i < entries.size(); i++) {
            if ((entries.get(i).getName().equals(name) && (entries.get(i).getAmount() == amount))) {
                entries.remove(i); // removes the entry if it is present in the list
            }
        }
    }

    // REQUIRES: name and amount
    // EFFECTS: gets a specific entry that matches the given description and amount
    public Entry getEntry(String name, double amount) {
        // loops through the list based on its size
        for (int i = 0; i < entries.size(); i++) {
            if ((entries.get(i).getName().equals(name) && (entries.get(i).getAmount() == amount))) {
                return entries.get(i); // returns the entry that matches the description and amount
            }
        }
        return null; // returns null if entry not found
    }

    // EFFECTS: returns list of entries
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    // REQUIRES: name and amount
    // EFFECTS: checks if desired entry is in list of entries
    public boolean checkEntry(String name, double amount) {
        // loops through the list of entries to see if description and amount match
        for (Entry e : entries) {
            if ((e.getName().equals(name) && (e.getAmount() == amount))) {
                return true; // returns true if entry present
            }
        }
        return false; // returns false if entry not present
    }

    // EFFECTS: gets the total amount for Budget
    public double getTotalAmount() {
        double monthlyAmount = 0.0;
        // loops through the month and adds the amount to total amount
        for (Entry entry : entries) {
            monthlyAmount += entry.getAmount();
        }

        // returns the monthly amount
        return monthlyAmount;
    }

    // REQUIRES: int index and double newAmount
    // MODIFIES: entry
    // EFFECTS: modifies amount of entry in budget to new amount
    public void modifyEntryAmount(int index, double newAmount) {
        Entry entry = entries.get(index);
        entry.setAmount(newAmount);
    }

    // REQUIRES: int index and string newName
    // MODIFIES: entry
    // EFFECTS: modifies description of entry in expenses to new amount
    public void modifyEntryDescription(int index, String newName) {
        Entry entry = entries.get(index);
        entry.setName(newName);
    }

    // EFFECTS: returns length of the list of yearly objects
    public int getSize() {
        return entries.size();
    }

    // REQUIRES: int i
    // EFFECTS: return the entry at an index
    public Entry getSpecificEntry(int i) {
        return entries.get(i);
    }

    // EFFECTS: returns the index of an entry if present, -1 otherwise
    public int getEntryIndex(String name, double amount) {
        for (int i = 0; i < entries.size(); i++) {
            if ((entries.get(i).getName().equals(name) && (entries.get(i).getAmount() == amount))) {
                return i; // returns the entry index that matches the description and amount
            }
        }
        return -1; // entry is not found
    }
}

