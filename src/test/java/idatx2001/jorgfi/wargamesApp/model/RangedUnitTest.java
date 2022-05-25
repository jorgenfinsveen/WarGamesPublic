package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Tests the RangedUnit-class.
 * 
 * @author jorgfi
 */
public class RangedUnitTest {

    /**
     * Declares an instance of the RangedUnit-class with the
     * main constructor. Checks that all paramaters are correct.
     */
    @Test
    public void testCreationOfRangedUnitObjectWithMainConstructor() {

        // Positive test
        RangedUnit rangedUnit1 = new RangedUnit("Archer", 100, 75, 50);

        assertEquals("Archer", rangedUnit1.getName());
        assertEquals(100, rangedUnit1.getHealth());
        assertEquals(75, rangedUnit1.getAttack());
        assertEquals(50, rangedUnit1.getArmor());


        // Negative test
        assertThrows(IllegalArgumentException.class, () -> {
            RangedUnit rangedUnit2 = new RangedUnit("", -100, -100, -100);
        });
    }

    /**
     * Declares an instance of the RangedUnit-class with the
     * simplified constructor. Checks that both given and default
     * parameters are correct.
     */
    @Test
    public void testCreationOfRangedUnitObjectWithSimpleConstructor() {

        // Positive test
        RangedUnit rangedUnit1 = new RangedUnit("Spearman", 125);

        assertEquals("Spearman", rangedUnit1.getName());
        assertEquals(125, rangedUnit1.getHealth());
        assertEquals(15, rangedUnit1.getAttack());
        assertEquals(8, rangedUnit1.getArmor());


         // Negative test
         assertThrows(IllegalArgumentException.class, () -> {
            RangedUnit rangedUnit2 = new RangedUnit("", -100);
        });
    }

    /**
     * Checks that the Ranger-units getAttackBonus() and
     * getResistBonus() gives the correct values as result.
     */
    @Test
    public void testCorrectValueReturnedFromGetAttackBonusAndGetResistBonus() {
        RangedUnit rangedUnit1 = new RangedUnit("Archer", 100, 75, 50);
        RangedUnit rangedUnit2 = new RangedUnit("Spearman", 125);

        assertEquals(3, rangedUnit1.getAttackBonus());
        assertEquals(6, rangedUnit1.getResistBonus());

        rangedUnit2.attack(rangedUnit1);
        assertEquals(4, rangedUnit1.getResistBonus()); 

        rangedUnit2.attack(rangedUnit1);
        assertEquals(2, rangedUnit1.getResistBonus()); 
    }

    /**
     * Tests that the getTerrainAttackAndResistBonus() methods returns
     * correct values, and that those are applied to the getters for 
     * attack and resist bonus.
     */
    @Test
    public void testCOrrectValueReturnedFromGetAttackBonusWithTerrainBonus() {
        RangedUnit ranger = new RangedUnit("Footman", 100, 75, 50);
        ranger.setTerrain("HILL");
        assertEquals(2, ranger.getTerrainAttackAndResistBonus()[0]);
        assertEquals(0, ranger.getTerrainAttackAndResistBonus()[1]);
        assertEquals(5, ranger.getAttackBonus()); // 3
        assertEquals(6, ranger.getResistBonus()); // 6

        ranger.setTerrain("FOREST");
        assertEquals(2, ranger.getAttackBonus());
        System.out.println(ranger.getAmountOfRecievedHits());
        assertEquals(6, ranger.getResistBonus());

        ranger.setTerrain("PLANES");
        assertEquals(3, ranger.getAttackBonus());
        assertEquals(6, ranger.getResistBonus());

        ranger.setTerrain("NONE");
        assertEquals(3, ranger.getAttackBonus());
        assertEquals(6, ranger.getResistBonus());
    }

    /**
     * Tests that the Units health gets decreased upon attacked
     * Sets infantry attack to 48 so that the total damage is 50
     */
    @Test
    public void testThatUnitActuallyGetsDamaged() {
        RangedUnit ranger = new RangedUnit("Titan", 150, 10, 0);
        InfantryUnit infantry = new InfantryUnit("King", 200, 48, 100);

        assertEquals(150, ranger.getHealth());
        infantry.attack(ranger);
        assertEquals(106, ranger.getHealth());
        infantry.attack(ranger);
        assertEquals(60, ranger.getHealth());
        infantry.attack(ranger);
        assertEquals(12, ranger.getHealth());
        infantry.attack(ranger);
        assertEquals(0, ranger.getHealth());
        assertEquals(false, ranger.isAlive());
    }
}
