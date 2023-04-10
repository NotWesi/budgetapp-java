package model;

import java.util.ArrayList;

// Represents expenses with a list of entry
// each having a description and the corresponding amount
public class Expenses extends Budget {
    private ArrayList<Entry> entries;

    // EFFECTS: budget is a list of entries
    public Expenses() {
        entries = new ArrayList<>();
    }
}

