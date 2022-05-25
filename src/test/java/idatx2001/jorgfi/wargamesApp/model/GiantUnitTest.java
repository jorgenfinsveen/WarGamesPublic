package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the GiantUnit methods and creation.
 * 
 * @author jorgfi
 */
public class GiantUnitTest {
    
     /**
     * Decalres an instance of the GiantUnit-class with
     * deafult values for attack and health values.
     * Checks if all fields of the object are correct.
     */
    @Test
    public void testCreationOfGiantUnitObjectWithMainConstructor() {

        // Positive Test
        GiantUnit giant = new GiantUnit("Titan", 100, 99, 98);

        assertEquals("Titan", giant.getName());
        assertEquals(100, giant.getHealth());
        assertEquals(99, giant.getAttack());
        assertEquals(98, giant.getArmor());


        // Negative Test 
        assertThrows(IllegalArgumentException.class, () -> {
            GiantUnit giant1 = new GiantUnit("", -100,-100,-100);
        });
    }

    /**
     * Declares an instance of the GiantUnit-class with
     * all customizeable fields. Checks if all fields of the
     * object is correct.
     */
    @Test
    public void testCreationOfGiantUnitObjectWithSimpleConstructor() {

        // Positive Test
        GiantUnit giant = new GiantUnit("Troll", 125);

        assertEquals("Troll", giant.getName());
        assertEquals(125, giant.getHealth());
        assertEquals(120, giant.getAttack());
        assertEquals(80, giant.getArmor());


        // Negative Test 
        assertThrows(IllegalArgumentException.class, () -> {
            GiantUnit giant2 = new GiantUnit("", -100);
        });
    }


     /**
     * Checks that the Giant-units getAttackBonus() and
     * getResistBonus() gives the correct values as result.
     */
    @Test
    public void testCorrectValueReturnedFromGetAttackBonus() {
        GiantUnit giant = new GiantUnit("Titan", 100);
        assertEquals(0, giant.getAttackBonus()); 
    }

    /**
     * Tests that the getTerrainAttackAndResistBonus() methods returns
     * correct values, and that those are applied to the getters for 
     * attack and resist bonus.
     */
    @Test
    public void testCorrectValueReturnedFromGetAttackBonusWithTerrainBonus() {
        GiantUnit giant = new GiantUnit("Titan", 100);
        giant.setTerrain("FOREST");
        assertEquals(20, giant.getTerrainAttackAndResistBonus()[0]);
        assertEquals(10, giant.getTerrainAttackAndResistBonus()[1]);

        giant.setTerrain("PLAINS");
        assertEquals(15, giant.getAttackBonus());
        assertEquals(-10, giant.getResistBonus());

        giant.setTerrain("HILL");
        assertEquals(5, giant.getAttackBonus());
        assertEquals(-5, giant.getResistBonus());

        giant.setTerrain("NONE");
        assertEquals(0, giant.getAttackBonus());
        assertEquals(0, giant.getResistBonus());
    }


    /**
     * Tests that the attackfrequency is correct depending on
     * the terrain
     */
    @Test
    public void testCorrectValueReturnedFromGetAttackFrequency() {
        GiantUnit giant = new GiantUnit("Titan", 100);

        assertEquals(3, giant.getAttackFrequency());

        giant.setTerrain("FOREST");
        giant.updateAttackFrequency();
        assertEquals(4, giant.getAttackFrequency());

        giant.setTerrain("PLAINS");
        giant.updateAttackFrequency();
        assertEquals(2, giant.getAttackFrequency());

        giant.setTerrain("HILL");
        giant.updateAttackFrequency();
        assertEquals(3, giant.getAttackFrequency());
    }

    /**
     * Tests that the Giant unit only attacks according to the specified attack frequency
     */
    @Test
    public void testThatGiantOnlyAttacksAccordingToAttackFrequency() {
        GiantUnit giant = new GiantUnit("Titan", 100);
        CommanderUnit commander = new CommanderUnit("King", 500, 1, 1);

        assertEquals(500, commander.getHealth());
        giant.attack(commander);
        assertEquals(382, commander.getHealth());
        giant.attack(commander);
        assertEquals(382, commander.getHealth());
        giant.attack(commander);
        assertEquals(382, commander.getHealth());
        giant.attack(commander);
        assertEquals(264, commander.getHealth());
    }

    /**
     * Tests that the Giants health gets decreased upon attacked
     * Sets infantry attack to 48 so that the total damage is 50
     */
    @Test
    public void testThatUnitActuallyGetsDamaged() {
        GiantUnit giant = new GiantUnit("Titan", 150, 10, 0);
        InfantryUnit infantry = new InfantryUnit("King", 200, 48, 100);

        assertEquals(150, giant.getHealth());
        infantry.attack(giant);
        assertEquals(100, giant.getHealth());
        infantry.attack(giant);
        assertEquals(50, giant.getHealth());
        infantry.attack(giant);
        assertEquals(0, giant.getHealth());
        assertEquals(false, giant.isAlive());
    }
}
