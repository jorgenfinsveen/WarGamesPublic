package idatx2001.jorgfi.wargamesApp.model;

import java.util.ArrayList;

/**
 * Represents a simulation of a battle between to armies.
 * 
 * @author jorgfi
 */
public class Battle {

    // Fields which contain the armies which will be fighting
    private Army armyOne;
    private Army armyTwo;


    // ArrayList containing log of army activity
    private ArrayList<String> army1Log = new ArrayList<>();
    private ArrayList<String> army2Log = new ArrayList<>();

    /**
     * Constructor that takes in two Army-objects as parameters
     * 
     * @param armyOne the first army
     * @param armyTwo the opposing army
     */
    public Battle(Army armyOne, Army armyTwo) {
        if (!armyOne.hasUnits() || !armyTwo.hasUnits()) throw new IllegalArgumentException("Both armies needs to contain at least 1 warrior");
        if (armyOne.equals(armyTwo)) throw new IllegalArgumentException("An army cannot fight itself");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Simulates a battle between the two armies.
     * While both armies has units in them, the method
     * will pick two random units from each army and run
     * their attack-methods on each other. When one of the
     * opponents dies, the loosing opponent will be removed
     * from its army, since its now "dead". This loop will
     * continue until one of the armies dont have ant units left.
     * 
     * @return Army-object which are victorious
     */
    public Army simulate() {
        Army victorious;
        int round = 0;

        while (armyOne.hasUnits() && armyTwo.hasUnits()) {

            if (round % 2 == 0) {
                armyAttack(armyOne, armyTwo);
            } else {
                armyAttack(armyTwo, armyOne);
            }
            armyOne.curseActivation();
            armyTwo.curseActivation();
            round++;
        }
        if (armyOne.hasUnits()) {
            victorious = armyOne;
        } else {
            victorious = armyTwo;
        }
        return victorious;
    }

    /**
     * Performs an attack on a random unit in the opposing army.
     * Removes the defending unit from the army if their health
     * is 0 or lower.
     * @param attacker the army which will be attacking
     * @param defender the army which will be defending
     */
    public void armyAttack(Army attacker, Army defender) {

        // TODO: Lag if-setning for å bestemme log å bruke


        Unit attackingUnit = attacker.getRandom();
        Unit defendingUnit = defender.getRandom();
        if (!(attackingUnit instanceof Wizard)) {
            attackingUnit.attack(defendingUnit);
            pickLog(attacker).add(attackingUnit.getName() + " attacked " + defendingUnit.getName() + "!");
            if (!defendingUnit.isAlive()) {
                defender.remove(defendingUnit);

            }
            // Wizards doesnt attack, they cast spells
        } else if (attackingUnit instanceof WhiteWizardUnit) {
            attackingUnit.castSpell(attacker);
            pickLog(attacker).add("A white wizard revived a fallen soldier!");
        } else if (attackingUnit instanceof DarkWizardUnit) {
            attackingUnit.castSpell(defender);
            pickLog(attacker).add("A dark wizard cursed an enemy!");
        }
    }

    /**
     * Calls simulate() and returns the winner as a String.
     * @return String containing the name of the victorious army
     */
    @Override
    public String toString() {
        return simulate().getName() + " won the battle!";
    }

    /**
     * Returns the activity log for army 1
     * @return ArrayList army log
     */
    public ArrayList<String> getLog1() {
        return this.army1Log;
    }

    /**
     * Returns the activity log for army 2
     * @return ArrayList army log
     */
    public ArrayList<String> getLog2() {
        return this.army2Log;
    }

    /**
     * Picks which log to use
     * @param attacker Army which log to pick
     * @return ArrayList<String> picked log
     */
    public ArrayList<String> pickLog(Army attacker) {
        if (attacker.equals(armyOne)) {
            return army1Log;
        } else {
            return army2Log;
        }
    }
}
