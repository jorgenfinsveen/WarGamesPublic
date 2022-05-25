package idatx2001.jorgfi.wargamesApp.tools;

import java.util.ArrayList;


/**
 * Collection of TableEntries. Will represent a Table containing
 * all entries in a table in the army configurator scene. A Table
 * can be compared to a shopping cart containing all units that the
 * user has ordered.
 * 
 * @author jorgfi
 */
public class Table {
    
    // Register of TableEntries
    private ArrayList<TableEntry> table = new ArrayList<>();



    /**
     * Returns the register
     * @return ArrayList of TableEntries
     */
    public ArrayList<TableEntry> getTable() {
        return this.table;
    }

    /**
     * Adds a TableEntry to the register
     * @param entry TableEntry to add
     */
    public void addEntry(TableEntry entry) {
        table.add(entry);
    }

    /**
     * Removes a TableEntry from the register
     * @param entry TableEntry to remove
     */
    public void removeEntry(TableEntry entry) {
        int i = 0;
        boolean found = false;

        while (i <= table.size() && !found) {
            if (table.get(i).equals(entry)) {
                table.remove(i);
                found = true;
            }
            i++;
        }
    }
}
