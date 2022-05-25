package idatx2001.jorgfi.wargamesApp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import idatx2001.jorgfi.wargamesApp.tools.UnitFactory;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;





/**
 * Tests the Battle-class. It is also used
 * to test that the entire game is working so far.
 * 
 * @author jorgfi
 */
public class BattleTest {

    UnitFactory factory = new UnitFactory();

    /**
     * Tests the simulation.
     * Declares two ArrayLists which represents two armies. It
     * fills them up with different warriors based on the example
     * from the task-assignment.
     * The two ArrayLists are used as parameters for two different
     * armies. The main constructor of the army-class is used here.
     * Declares an instance of the battle class and simulates a
     * battle between the two armies. The winner army are to be
     * written out to the console.
     */
    @Test
    public void testSimulation() {

        // Positive Test
        Army army1 = new Army("Human army", createSampleArmy1());
        Army army2 = new Army("Orcish horde", createSampleArmy2());


        Battle battle = new Battle(army1, army2);
        Army winner = battle.simulate();
        System.out.println(winner.getName() + " wins with " + winner.getAllUnits().size() + " warriors left: "  + winner.getAllUnits());
        assertTrue(winner.hasUnits());


        // Negative test
        if (winner.equals(army1)) {
            assertFalse(army2.hasUnits());
        } else {
            assertFalse(army1.hasUnits());
        }
    }

    /**
     * Tests that a battle cannot be made without units in both armies, and that
     * the battle must contain two different armies
     */
    @Test
    public void testInvalidCreationOfBattleInstnace() {
        Army army1 = new Army("TestArmy1");
        Army army2 = new Army("TestArmy2", createSampleArmy1());

        // Tests that an army without units are not accepted
        assertThrows(IllegalArgumentException.class, () -> {
            Battle battle = new Battle(army1, army2);
        });

        // Tests that a battle cannot contain the same army for both fields in constructor
        assertThrows(IllegalArgumentException.class, () -> {
            Battle battle = new Battle(army2, army2);
        });
    }

    /**
     * Creates a sample army number 1
     * @return ArrayList list of units to be added to the army
     */
    public ArrayList<Unit> createSampleArmy1() {
        ArrayList<Unit> armyList1 = new ArrayList<>();
        armyList1.addAll(factory.createListOfUnits(500, "Infantry", "Footman", 100));
        armyList1.addAll(factory.createListOfUnits(100, "Cavalry", "Knight", 100));
        armyList1.addAll(factory.createListOfUnits(100, "Ranger", "Archer", 100));
        armyList1.addAll(factory.createListOfUnits(5, "White wizard", "Wizard", 75));
        armyList1.addAll(factory.createListOfUnits(5, "Dark wizard", "Wich", 75));
        armyList1.add(factory.createNewUnit("Giant", "Troll", 30));
        armyList1.add(factory.createNewUnit("Commander", "Mountain King", 180));

        return armyList1;
    }

    /**
     * Creates a sample army number 2
     * @return ArrayList list of units to be added to the army
     */
    public ArrayList<Unit> createSampleArmy2() {
        ArrayList<Unit> armyList2 = new ArrayList<>();
        armyList2.addAll(factory.createListOfUnits(500, "Infantry", "Grunt", 100));
        armyList2.addAll(factory.createListOfUnits(100, "Cavalry", "Raider", 100));
        armyList2.addAll(factory.createListOfUnits(100, "Ranger", "Spearman", 100));
        armyList2.addAll(factory.createListOfUnits(5, "White wizard", "Warlock", 75));
        armyList2.addAll(factory.createListOfUnits(5, "Dark wizard", "Mage", 75));
        armyList2.add(factory.createNewUnit("Giant", "Titan", 30));
        armyList2.add(factory.createNewUnit("Commander", "Gul´dan", 180));

        return armyList2;
    }

    /**
     * Creates a sample army number 3
     * @return ArrayList list of units to be added to the army
     */
    public ArrayList<Unit> createSampleArmy3() {
        ArrayList<Unit> armyList = new ArrayList<>();
        armyList.addAll(factory.createListOfUnits(500, "Infantry", "Grunt", 100));
        return armyList;
    }

    /**
     * Creates a list with white wizard units.
     * @return ArrayList<Unit> containing WhiteWizardUnits.
     */
    public ArrayList<Unit> createWhiteWizardArmy() {
        ArrayList<Unit> armyList = new ArrayList<>();
        armyList.addAll(factory.createListOfUnits(711, "White wizard", "Wich", 75));
        
        return armyList;
    }

    /**
     * Creates a list with dark wizard units.
     * @return ArrayList<Unit> containing DarkWizardUnits.
     */
    public ArrayList<Unit> createDarkWizardArmy() {
        ArrayList<Unit> armyList = new ArrayList<>();
        armyList.addAll(factory.createListOfUnits(711, "Dark wizard", "Warlock", 75));
        
        return armyList;
    }

    /**
     * Tests that the WhiteWizardUnit is capable of reviving a unit
     * using its castSpell() method.
     */
    @Test
    public void testWhiteWizardUnitCastSpellMethod() {
        Army humanArmy = new Army("Human army", createSampleArmy1());
        Army wizardArmy = new Army("Wizard army", createWhiteWizardArmy());
        Unit opponent = humanArmy.getRandom();
        Unit wizard = wizardArmy.getRandom();

        opponent.setHealth(0);
        humanArmy.remove(opponent);
        assertEquals(711, humanArmy.getArmySize());
        assertEquals(0, opponent.getHealth());
        wizard.castSpell(humanArmy);
        assertEquals(712, humanArmy.getArmySize());
        assertEquals(100, opponent.getHealth());
    }

    /**
     * Tests that the DarkWizardUnits castSpell() method does curse
     * the affected Units correctly.
     */
    @Test
    public void testDarkWizardUnitCastSpellMethod() {
        Army humanArmy = new Army("Human army", createSampleArmy3());
        Army wizardArmy = new Army("Wizard army", createDarkWizardArmy());
        Unit wizard = wizardArmy.getRandom();
        for (int i = 0; i < 3; i++) {
            wizard.castSpell(humanArmy);
        }

        for (int i = 0; i < 10; i++) {
            for (Unit unit : humanArmy.getCursedSoldiers()) {
                assertEquals(100 - 5*i, unit.getHealth());
            }
            humanArmy.curseActivation();
        }
    }
}
