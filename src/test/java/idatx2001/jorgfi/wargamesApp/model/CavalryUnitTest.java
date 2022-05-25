package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Tests the CavalryUnit-class.
 * 
 * @author jorgfi
 */
public class CavalryUnitTest {

    /**
     * Decalres an instance of the CavalryUnit-class with
     * deafult values for attack and health values.
     * Checks if all fields of the object are correct.
     */
    @Test
    public void testCreationOfCavalryUnitObjectWithMainConstructor() {

        // Positive Test
        CavalryUnit cavalryUnit1 = new CavalryUnit("Knight", 100, 75, 50);

        assertEquals("Knight", cavalryUnit1.getName());
        assertEquals(100, cavalryUnit1.getHealth());
        assertEquals(75, cavalryUnit1.getAttack());
        assertEquals(50, cavalryUnit1.getArmor());


        // Negative Test 
        assertThrows(IllegalArgumentException.class, () -> {
            CavalryUnit cavalryUnit2 = new CavalryUnit("", -100,-100,-100);
        });
    }

    /**
     * Declares an instance of the CavalryUnit-class with
     * all customizeable fields. Checks if all fields of the
     * object is correct.
     */
    @Test
    public void testCreationOfCavalryUnitObjectWithSimpleConstructor() {

        // Positive Test
        CavalryUnit cavalryUnit1 = new CavalryUnit("Raider", 125);

        assertEquals("Raider", cavalryUnit1.getName());
        assertEquals(125, cavalryUnit1.getHealth());
        assertEquals(20, cavalryUnit1.getAttack());
        assertEquals(12, cavalryUnit1.getArmor());


        // Negative Test 
        assertThrows(IllegalArgumentException.class, () -> {
            CavalryUnit cavalryUnit2 = new CavalryUnit("", -100);
        });
    }

    /**
     * Checks that the Cavalry-units getAttackBonus() and
     * getResistBonus() gives the correct values as result.
     */
    @Test
    public void testCorrectValueReturnedFromGetAttackBonusAndGetResistBonus() {
        CavalryUnit cavalryUnit1 = new CavalryUnit("Knight", 100, 75, 50);
        CavalryUnit cavalryUnit2 = new CavalryUnit("Raider", 125);

        assertEquals(6, cavalryUnit1.getAttackBonus());
        cavalryUnit1.attack(cavalryUnit2);
        assertEquals(2, cavalryUnit1.getAttackBonus());

        assertEquals(1, cavalryUnit1.getResistBonus());
    }

    /**
     * Tests that the getTerrainAttackAndResistBonus() methods returns
     * correct values, and that those are applied to the getters for 
     * attack and resist bonus.
     */
    @Test
    public void testCOrrectValueReturnedFromGetAttackBonusWithTerrainBonus() {
        CavalryUnit cavalry = new CavalryUnit("Knight", 100, 75, 50);
        cavalry.setTerrain("FOREST");
        assertEquals(0, cavalry.getTerrainAttackAndResistBonus()[0]);
        assertEquals(-100, cavalry.getTerrainAttackAndResistBonus()[1]);
        assertEquals(0, cavalry.getResistBonus());

        cavalry.setTerrain("PLAINS");
        assertEquals(8, cavalry.getAttackBonus());
        assertEquals(1, cavalry.getResistBonus());

        cavalry.setTerrain("HILL");
        assertEquals(6, cavalry.getAttackBonus());
        assertEquals(1, cavalry.getResistBonus());

        cavalry.setTerrain("NONE");
        assertEquals(6, cavalry.getAttackBonus());
        assertEquals(1, cavalry.getResistBonus());
    }

    /**
     * Tests that the Units health gets decreased upon attacked
     * Sets infantry attack to 48 so that the total damage is 50
     */
    @Test
    public void testThatUnitActuallyGetsDamaged() {
        CavalryUnit cavalry = new CavalryUnit("Titan", 150, 10, 0);
        InfantryUnit infantry = new InfantryUnit("King", 200, 48, 100);

        assertEquals(150, cavalry.getHealth());
        infantry.attack(cavalry);
        assertEquals(101, cavalry.getHealth());
        infantry.attack(cavalry);
        assertEquals(52, cavalry.getHealth());
        infantry.attack(cavalry);
        assertEquals(3, cavalry.getHealth());
        infantry.attack(cavalry);
        assertEquals(0, cavalry.getHealth());
        assertEquals(false, cavalry.isAlive());
    }
}
