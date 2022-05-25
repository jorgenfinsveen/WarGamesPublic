package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the CommanderUnit-class
 * 
 * @author jorgfi
 */
public class CommanderUnitTest {
    
    /**
     * Declares an instance of the CommanderUnit with the main constructor
     * and checks that the fields has proper values.
     */
     @Test
     public void testCreationOfCommanderUnitObjectWithMainConstructor() {

        // Positive Test
        CommanderUnit commanderUnit1 = new CommanderUnit("Chief", 180, 50, 50);
        assertEquals("Chief", commanderUnit1.getName());
        assertEquals(180, commanderUnit1.getHealth());
        assertEquals(50, commanderUnit1.getAttack());
        assertEquals(50, commanderUnit1.getArmor());


        // Negative Test
        assertThrows(IllegalArgumentException.class, () -> {
            CommanderUnit commanderUnit2 = new CommanderUnit("", -100);
        });
     }

     /**
      * Declares an instance of the CommanderUnit with the simplified constructor
      * and checks that the field has proper values.
      */
      @Test
      public void testCreationOfCommanderUnitObjectWithSimplifiedConstructor() {

        // Positive Test
        CommanderUnit commanderUnit1 = new CommanderUnit("Chief", 180);
        assertEquals("Chief", commanderUnit1.getName());
        assertEquals(180, commanderUnit1.getHealth());
        assertEquals(25, commanderUnit1.getAttack());
        assertEquals(15, commanderUnit1.getArmor());


        // Negative Test
        assertThrows(IllegalArgumentException.class, () -> {
            CommanderUnit commanderUnit2 = new CommanderUnit("", -100);
        });
      }

      /**
       * Tests that the getAttackBonus() and getResistBonus() returns proper values
       */
      @Test void testGettersForAttackBonusAndResistBonus() {
          CommanderUnit commanderUnit1 = new CommanderUnit("Chief", 180, 35, 20);
          CommanderUnit commanderUnit2 = new CommanderUnit("Master", 125);
  
          assertEquals(6, commanderUnit1.getAttackBonus());
          commanderUnit1.attack(commanderUnit2);
          assertEquals(2, commanderUnit1.getAttackBonus());
  
          assertEquals(1, commanderUnit1.getResistBonus());
      }

      /**
     * Tests that the getTerrainAttackAndResistBonus() methods returns
     * correct values, and that those are applied to the getters for 
     * attack and resist bonus.
     */
    @Test
    public void testCOrrectValueReturnedFromGetAttackBonusWithTerrainBonus() {
        CommanderUnit commander = new CommanderUnit("Chief", 180, 75, 50);
        commander.setTerrain("FOREST");
        assertEquals(0, commander.getTerrainAttackAndResistBonus()[0]);
        assertEquals(-100, commander.getTerrainAttackAndResistBonus()[1]);
        assertEquals(0, commander.getResistBonus());

        commander.setTerrain("PLAINS");
        assertEquals(8, commander.getAttackBonus());
        assertEquals(1, commander.getResistBonus());

        commander.setTerrain("HILL");
        assertEquals(6, commander.getAttackBonus());
        assertEquals(1, commander.getResistBonus());

        commander.setTerrain("NONE");
        assertEquals(6, commander.getAttackBonus());
        assertEquals(1, commander.getResistBonus());
    }

    /**
     * Tests that the Units health gets decreased upon attacked
     * Sets infantry attack to 48 so that the total damage is 50
     */
    @Test
    public void testThatUnitActuallyGetsDamaged() {
        CommanderUnit commander = new CommanderUnit("Titan", 150, 10, 0);
        InfantryUnit infantry = new InfantryUnit("King", 200, 48, 100);

        assertEquals(150, commander.getHealth());
        infantry.attack(commander);
        assertEquals(101, commander.getHealth());
        infantry.attack(commander);
        assertEquals(52, commander.getHealth());
        infantry.attack(commander);
        assertEquals(3, commander.getHealth());
        infantry.attack(commander);
        assertEquals(0, commander.getHealth());
        assertEquals(false, commander.isAlive());
    }

}
