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
        // logs event that entry has been added
        EventLog.getInstance().logEvent(new Event("Entry has been added"));
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
        // logs the event of entry being removed
        EventLog.getInstance().logEvent(new Event("Entry has been removed."));
    }

    // REQUIRES: name and amount
    // EFFECTS: gets a specific entry that matches the given description and amount
    public Entry getEntry(String name, double amount) {
        // loops through the list based on its size
        for (int i = 0; i < entries.size(); i++) {
            if ((entries.get(i).getName().equals(name) && (entries.get(i).getAmount() == amount))) {
                Entry e = entries.get(i);
                // logs the event that entry has been returned
                EventLog.getInstance().logEvent(new Event("Entry returned."));
                return e; // returns the entry that matches the description and amount
            }
        }
        // logs the events that entry does not exist
        EventLog.getInstance().logEvent(new Event("Entry does not exist."));
        return null; // returns null if entry not found
    }

    // EFFECTS: returns list of entries
    public ArrayList<Entry> getEntries() {
        // logs the event that entries have been returned
        EventLog.getInstance().logEvent(new Event("The specific list of entries have been returned."));
        return entries;
    }

    // REQUIRES: name and amount
    // EFFECTS: checks if desired entry is in list of entries
    public boolean checkEntry(String name, double amount) {
        // loops through the list of entries to see if description and amount match
        for (int i = 0; i < entries.size(); i++) {
            if ((entries.get(i).getName().equals(name) && (entries.get(i).getAmount() == amount))) {
                EventLog.getInstance().logEvent(new Event("Entry exists."));
                return true; // returns true if entry present
            }
        }
        // logs the event that entry does not exist
        EventLog.getInstance().logEvent(new Event("Entry does not exist."));
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
        EventLog.getInstance().logEvent(new Event("Total amount of budget has been calculated and returned."));
        return monthlyAmount;
    }

    // REQUIRES: int index and double newAmount
    // MODIFIES: entry
    // EFFECTS: modifies amount of entry in budget to new amount
    public void modifyEntryAmount(int index, double newAmount) {
        Entry entry = entries.get(index);
        entry.setAmount(newAmount);
        // logs the event that amount has been modified or not
        if (entry == null) {
            EventLog.getInstance().logEvent(new Event("Entry amount has not been modified as it doesn't exist."));
        } else {
            EventLog.getInstance().logEvent(new Event("Entry amount has been modified."));
        }

    }

    // REQUIRES: int index and string newName
    // MODIFIES: entry
    // EFFECTS: modifies description of entry in expenses to new amount
    public void modifyEntryDescription(int index, String newName) {
        Entry entry = entries.get(index);
        entry.setName(newName);
        // logs the event that description has been modified or not
        if (entry == null) {
            EventLog.getInstance().logEvent(new Event("Entry description has not been modified as it doesn't exist."));
        } else {
            EventLog.getInstance().logEvent(new Event("Entry description has been modified."));
        }
    }

    // EFFECTS: returns length of the list of yearly objects
    public int getSize() {
        return entries.size();
    }

    // REQUIRES: int i
    // EFFECTS: return the entry at an index
    public Entry getSpecificEntry(int i) {
        // logs the event that entry has been returned
        EventLog.getInstance().logEvent(new Event("Entry has been found using index."));
        return entries.get(i);
    }

    // EFFECTS: returns the index of an entry if present, -1 otherwise
    public int getEntryIndex(String name, double amount) {
        for (int i = 0; i < entries.size(); i++) {
            if ((entries.get(i).getName().equals(name) && (entries.get(i).getAmount() == amount))) {
                // logs event that entry exists and index has been returned
                EventLog.getInstance().logEvent(new Event("Entry exists and index returned."));
                return i; // returns the entry index that matches the description and amount
            }
        }
        // logs event that entry exists and index has not been returned
        EventLog.getInstance().logEvent(new Event("Entry does not exist and no index returned."));
        return -1; // entry is not found
    }
}

