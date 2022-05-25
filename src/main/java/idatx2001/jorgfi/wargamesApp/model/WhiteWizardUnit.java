package idatx2001.jorgfi.wargamesApp.model;

import java.util.ArrayList;
import java.util.Random;

public class WhiteWizardUnit extends Wizard{

    private static final String UNIT_TYPE = "White wizard";

    /**
     * Simplified constructor with default values for attack and armor
     * 
     * @param name   String representing the commanders name
     * @param health int representing the commanders health
     */
    public WhiteWizardUnit(String name, int health) {
        super(name, health, 25, 15);
    }

    /**
     * Constructor with full customization options for the
     * commanders fields
     * 
     * @param name   String representing the commanders name
     * @param health int representing the commanders health
     * @param attack int capable damage dealing
     * @param armor int extra layer of protection
     */
    public WhiteWizardUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public String getType() {
        return WhiteWizardUnit.UNIT_TYPE + "";
    }

    /**
     * Wizards dont attack like other units do. They casts spells.
     * @param opponent
     */
    @Override
    public void attack(Unit opponent) {
        // Does nothing.
    } 

    /**
     * Revives a random comrade from the wizards army
     * 
     * @param belongingArmy Army which the wizard is part of
     */
    @Override
    public void castSpell(Army belongingArmy) {
        ArrayList<Unit> candidates = belongingArmy.getFallenSoldiers();
        if (candidates.size() != 0) {
            Unit comrade = candidates.get(new Random().nextInt(candidates.size()));
            comrade.setHealth(100);
            belongingArmy.add(comrade);
            belongingArmy.removeFromFallenSoldiers(comrade);
        }
    }
}
