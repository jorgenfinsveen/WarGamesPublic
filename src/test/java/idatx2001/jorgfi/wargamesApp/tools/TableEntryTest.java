package idatx2001.jorgfi.wargamesApp.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Tests the TableEntry class
 * 
 * @author jorgfi
 */
public class TableEntryTest {
    

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
     * Tests all accessor-methods for the class
     */
    @Test
    public void testGetters() {
        TableEntry entry = createNewTableEntryCustom();

        assertEquals("Commander", entry.getType());
        assertEquals("test1", entry.getName());
        assertEquals(3, entry.getAmount());
        assertEquals(100, entry.getHealth());
        assertEquals(20, entry.getAttack());
        assertEquals(30, entry.getArmor());
        assertEquals(true, entry.isCustom());
    }


    /**
     * Tests all mutator-methods for the class
     */
    @Test
    public void testSetters() {
        TableEntry entry = createNewTableEntryCustom();

        entry.setAmount(4);
        entry.setHealth(200);
        entry.setAttack(50);
        entry.setArmor(10);
        entry.setIsCustom(false);
        entry.setName("test3");
        entry.setType("Cavalry");

        assertEquals("Cavalry", entry.getType());
        assertEquals("test3", entry.getName());
        assertEquals(4, entry.getAmount());
        assertEquals(200, entry.getHealth());
        assertEquals(50, entry.getAttack());
        assertEquals(10, entry.getArmor());
    }
}
