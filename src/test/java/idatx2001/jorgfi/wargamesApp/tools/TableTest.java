package idatx2001.jorgfi.wargamesApp.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;


/**
 * Tests the Table class
 * 
 * @author jorgfi
 */
public class TableTest {

    
    /**
     * Creates a sample entry with custom fields for attack and armor
     * @return TableEntry sample entry
     */
    public TableEntry createNewTableEntryCustom() {
        TableEntry entry = new TableEntry("Commander", "test1", 3, 100, 20, 30);
        return entry;
    }


    /**
     * Creates a sample entry without custom fields for attack and armor
     * @return TableEntry sample entry 
     */
    public TableEntry createNewTableEntrySimple() {
        TableEntry entry = new TableEntry("Commander", "test2", 3, 100);
        return entry;
    }


    /**
     * Tests the getTable method
     */
    @Test
    public void testGetTableMethod() {
        Table table = new Table();
        TableEntry entry1 = createNewTableEntryCustom();
        TableEntry entry2 = createNewTableEntrySimple();
        ArrayList<TableEntry> list = new ArrayList<TableEntry>();

        list.add(entry1);
        list.add(entry2);
        table.addEntry(entry1);
        table.addEntry(entry2);

        assertEquals(list, table.getTable());
    }


    /**
     * Tests the addEntry method
     */
    @Test
    public void testAddEntry() {
        Table table = new Table();
        TableEntry entry1 = createNewTableEntryCustom();

        table.addEntry(entry1);
        
        assertEquals(1, table.getTable().size());
        assertEquals(entry1, table.getTable().get(0));
    }


    /**
     * Tests the removeEntry method
     */
    @Test
    public void testRemoveEntry() {
        Table table = new Table();
        TableEntry entry1 = createNewTableEntryCustom();
        TableEntry entry2 = createNewTableEntrySimple();

        table.addEntry(entry1);
        table.addEntry(entry2);
        table.removeEntry(entry1);

        assertEquals(1, table.getTable().size());
        assertNotEquals(entry1, table.getTable().get(0));
    }
}
