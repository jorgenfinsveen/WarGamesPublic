package idatx2001.jorgfi.wargamesApp.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import idatx2001.jorgfi.wargamesApp.model.Army;
import idatx2001.jorgfi.wargamesApp.model.Unit;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;





/**
 * Tests the functionality of the UnitFactory-class
 * 
 * @author jorgfi
 */
public class UnitFactoryTest {

    /**
     * Tests the creation of single unit with valid and invalid parameters.
     */
    @Test
    public void testCreateNewUnitMethod() {
        // Positive test
        Unit posUnit = UnitFactory.createNewUnit("Ranger", "Archer", 100);
        assertEquals("Ranger", posUnit.getType());
        assertEquals("Archer", posUnit.getName());
        assertEquals(100, posUnit.getHealth());


        // Negative test
        Unit negUnit1 = UnitFactory.createNewUnit("Mage", "Dumbledore", 100);
        Unit negUnit2 = UnitFactory.createNewUnit("Ranger", "", 100);
        Unit negUnit3 = UnitFactory.createNewUnit("Ranger", "Archer", 0);
        assertNull(negUnit1);
        assertNull(negUnit2);
        assertNull(negUnit3);
    }


    /**
     * Tests the creation of multiple units with valid and invalid parameters.
     */
    @Test
    public void testCreateListOfUnits() {
        // Positive test 
        ArrayList<Unit> armyList = new ArrayList<Unit>();
        armyList.addAll(UnitFactory.createListOfUnits(5, "Cavalry", "Knight", 100));
        Army army = new Army("Norwegian army", armyList); 
        Unit randomUnit = army.getRandom();

        assertEquals("Cavalry", randomUnit.getType());
        assertEquals("Knight", randomUnit.getName());
        assertEquals(100, randomUnit.getHealth());
        assertEquals(5, army.getArmySize());


        // Negative test
        assertThrows(IllegalArgumentException.class, () -> {
            UnitFactory.createListOfUnits(0, "Infantry", "Grunt", 100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            UnitFactory.createListOfUnits(10, "Soldier", "Seargant", 100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            UnitFactory.createListOfUnits(10, "Infantry", "", 100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            UnitFactory.createListOfUnits(10, "Infantry", "Grunt", 0);
        });
    }
}
