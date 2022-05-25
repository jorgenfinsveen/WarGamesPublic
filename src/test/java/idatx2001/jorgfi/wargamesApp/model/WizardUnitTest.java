package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests that the wizard class works properly. 
 * Since the white and the dark wizard classes only have one field and 
 * one method that distinguish them from each other, this class will
 * test one of the subclasses, and then assume that the same results will
 * appear for the other one. There will however be tests for their unique
 * field and their castSpell method, but the last one will be tested in
 * the army and battle test classes.
 * 
 * @author jorgfi
 */
public class WizardUnitTest {
    

    /**
     * Tests the creation of a wizard using the main constructor.
     */
    @Test
    public void testCreationOfWhiteWizardUnitObjectWithMainConstructor() {

        // Positive Test
        Wizard wizard = new WhiteWizardUnit("Dumbledore", 100, 75, 50);

        assertEquals("Dumbledore", wizard.getName());
        assertEquals(100, wizard.getHealth());
        assertEquals(75, wizard.getAttack());
        assertEquals(50, wizard.getArmor());


        // Negative Test 
        assertThrows(IllegalArgumentException.class, () -> {
            Wizard wizard2 = new WhiteWizardUnit("", -100,-100,-100);
        });
    }

    /**
     * Tests the creation of a wizard using the simplified constructor.
     */
    @Test
    public void testCreationOfWhiteWizardUnitObjectWithSimpleConstructor() {

        // Positive Test
        Wizard wizard = new WhiteWizardUnit("Gandalf", 125);

        assertEquals("Gandalf", wizard.getName());
        assertEquals(125, wizard.getHealth());
        assertEquals(25, wizard.getAttack());
        assertEquals(15, wizard.getArmor());


        // Negative Test 
        assertThrows(IllegalArgumentException.class, () -> {
            Wizard wizard2 = new WhiteWizardUnit("", -100);
        });
    }

    /**
     * Checks that the getAttackBonus() and
     * getResistBonus() gives the correct values as result.
     */
    @Test
    public void testCorrectValueReturnedFromGetAttackBonusAndGetResistBonus() {
        Wizard wizard1 = new WhiteWizardUnit("Gandalf", 100, 75, 50);

        assertEquals(0, wizard1.getAttackBonus());
        assertEquals(2, wizard1.getResistBonus());
    }

    /**
     * Tests that the getTerrainAttackAndResistBonus() methods returns
     * correct values, and that those are applied to the getters for 
     * attack and resist bonus.
     */
    @Test
    public void testCOrrectValueReturnedFromGetAttackBonusWithTerrainBonus() {
        Wizard wizard = new WhiteWizardUnit("Gandalf", 100, 75, 50);
        wizard.setTerrain("FOREST");
        assertEquals(0, wizard.getTerrainAttackAndResistBonus()[0]);
        assertEquals(2, wizard.getTerrainAttackAndResistBonus()[1]);
        assertEquals(0, wizard.getAttackBonus());
        assertEquals(4, wizard.getResistBonus());

        wizard.setTerrain("PLAINS");
        assertEquals(0, wizard.getAttackBonus());
        assertEquals(1, wizard.getResistBonus());

        wizard.setTerrain("HILL");
        assertEquals(0, wizard.getAttackBonus());
        assertEquals(3, wizard.getResistBonus());

        wizard.setTerrain("NONE");
        assertEquals(0, wizard.getAttackBonus());
        assertEquals(2, wizard.getResistBonus());
    }

    /**
     * Tests the getType() method for White- and DarkWizardUnit.
     */
    @Test
    public void testGettersForGetTypeForWhiteAndDarkWizard() {
        WhiteWizardUnit white = new WhiteWizardUnit("Dumbledore", 100);
        DarkWizardUnit dark = new DarkWizardUnit("Gandalf", 100);

        assertEquals("White wizard", white.getType());
        assertEquals("Dark wizard", dark.getType());
    }

    /**
     * Tests that the Units health gets decreased upon attacked
     * Sets infantry attack to 48 so that the total damage is 50
     */
    @Test
    public void testThatUnitActuallyGetsDamaged() {
        WhiteWizardUnit wizard = new WhiteWizardUnit("Titan", 150, 10, 0);
        InfantryUnit infantry = new InfantryUnit("King", 200, 48, 100);

        assertEquals(150, wizard.getHealth());
        infantry.attack(wizard);
        assertEquals(102, wizard.getHealth());
        infantry.attack(wizard);
        assertEquals(54, wizard.getHealth());
        infantry.attack(wizard);
        assertEquals(6, wizard.getHealth());
        infantry.attack(wizard);
        assertEquals(0, wizard.getHealth());
        assertEquals(false, wizard.isAlive());
    }
}

