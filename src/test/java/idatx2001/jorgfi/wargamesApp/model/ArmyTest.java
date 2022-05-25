package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import idatx2001.jorgfi.wargamesApp.tools.UnitFactory;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;





/**
 * Tests the Army-class.
 * 
 * @author jorgfi
 */
public class ArmyTest {



    /**
     * Fills a list with a set og units 
     * 
     * @return ArrayList<Unit> containing units in an army
     */
    public ArrayList<Unit> makeArmyList() {
        ArrayList<Unit> armyList = new ArrayList<>();

        // Fills armyList with units using the factory-class
        armyList.addAll(UnitFactory.createListOfUnits(500, "Infantry", "Footman", 100));
        armyList.addAll(UnitFactory.createListOfUnits(100, "Cavalry", "Knight", 100));
        armyList.addAll(UnitFactory.createListOfUnits(500, "Ranger", "Archer", 100));
        armyList.add(UnitFactory.createNewUnit("Commander", "Mountain King", 180));

        return armyList;
    }


    /**
     * Fills a list with an extended set og units 
     * 
     * @return ArrayList<Unit> containing units in an army
     */
    public ArrayList<Unit> makeArmyListExtended() {
        ArrayList<Unit> armyList = new ArrayList<>();

        // Fills armyList with units using the factory-class
        armyList.addAll(UnitFactory.createListOfUnits(500, "Infantry", "Footman", 100));
        armyList.addAll(UnitFactory.createListOfUnits(100, "Cavalry", "Knight", 100));
        armyList.addAll(UnitFactory.createListOfUnits(500, "Ranger", "Archer", 100));
        armyList.add(UnitFactory.createNewUnit("Commander", "Mountain King", 180));
        armyList.addAll(UnitFactory.createListOfUnits(10, "Giant", "Orc", 100));
        armyList.addAll(UnitFactory.createListOfUnits(20, "White wizard", "Mage", 100));
        armyList.addAll(UnitFactory.createListOfUnits(30, "Dark wizard", "Warlock", 100));

        return armyList;
    }

    /**
     * Tests that an instance of the Army-class with the simplified
     * constructor gets correctly initialized
     */
    @Test
    public void testCreationOfArmyObjectWithSimplifiedMethod() {

        // Positive Test
        Army army = new Army("Norwegian Army");
        assertEquals("Norwegian Army", army.getName());


        // Negative Test
        assertThrows(IllegalArgumentException.class, () -> {
            Army army2 = new Army("");
        });
    }

    /**
     * Tests that an instance of the Army-class with the main constructor
     * gets correct fields
     */
    @Test
    public void testCreationOfArmyObjectWithMainMethod() {

        // Positive Test
        ArrayList<Unit> armyList = makeArmyList();
        Army army = new Army("Sweedish army", armyList);

        // Tests that the name of the army is correct
        assertEquals("Sweedish army", army.getName());

        // Tests that all units were added to the army
        assertEquals(armyList, army.getAllUnits());




        // Negative Test 
        ArrayList<Unit> armyList2 = new ArrayList<>();
        // Tests that the list-parameter is not accepted when empty
        assertThrows(IllegalArgumentException.class, () -> {
            Army army2 = new Army("er", armyList2);
        });
    }

    /**
     * Tests all acessormethods
     */
    @Test
    public void testGettersAndSetters() {
        Army army = new Army("Norwegian Army");
        // Tests getName()
        assertEquals("Norwegian Army", army.getName());

        army.add(new InfantryUnit("Infantry", 100));
        // Tests that the unit actually was added to the army
        assertEquals(1, army.getAllUnits().size());

        Unit unit = army.getRandom();
        army.remove(unit);
        // Tests that the unit was removed succesfully
        assertEquals(0, army.getAllUnits().size());
    }

    /**
     * Checks that an army containing units are able to return a 
     * random unit with the getRandom method.
     */
    @Test
    public void testGetRandom() {
        ArrayList<Unit> armyList = makeArmyList();
        Army army = new Army("Norwegian Army", armyList);

        Unit randomUnit = army.getRandom();
        assertNotNull(randomUnit);
        assertTrue(randomUnit instanceof Unit);
    }

    /**
     * Tests that hashCode() method returns a hash code
     */
    @Test
    public void testHashCode() {
        ArrayList<Unit> armyList = makeArmyList();
        Army army = new Army("Norwegian Army", armyList);

        assertNotNull(army.hashCode());
    }


    /**
     * Tests the getter methods which returns all instances of a
     * specific unit-type in an army
     */
    @Test
    public void testGetAllInstancesOfAUnitType() {

        // Creates a new army
        Army army = new Army("TestArmy", makeArmyListExtended());

        // Checks that the expected size of the lists returned are correct
        assertEquals(500, army.getAllInfantryUnits().size());
        assertEquals(100, army.getAllCavalryUnits().size());
        assertEquals(500, army.getAllRangedUnits().size());
        assertEquals(1, army.getAllCommanderUnits().size());
        assertEquals(10, army.getAllGiantUnits().size());
        assertEquals(20, army.getAllWhiteWizardUnits().size());
        assertEquals(30, army.getAllDarkWizardUnits().size());

        // Checks that the lists returned contain units with names matching those created
        assertEquals("Footman", army.getAllInfantryUnits().get(0).getName());
        assertEquals("Knight", army.getAllCavalryUnits().get(0).getName());
        assertEquals("Archer", army.getAllRangedUnits().get(0).getName());
        assertEquals("Mountain King", army.getAllCommanderUnits().get(0).getName());
        assertEquals("Orc", army.getAllGiantUnits().get(0).getName());
        assertEquals("Mage", army.getAllWhiteWizardUnits().get(0).getName());
        assertEquals("Warlock", army.getAllDarkWizardUnits().get(0).getName());

    }
}
