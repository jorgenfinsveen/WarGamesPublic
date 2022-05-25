package idatx2001.jorgfi.wargamesApp.model;

/**
 * Represents a Wizard Unit. Wizards can be divided into two
 * categories, white and dark wizards. Theese sub-classes will 
 * inherit from the Wizard-class, but they will have different
 * castSpell() methods.
 * 
 * @author jorgfi
 */
public abstract class Wizard extends Unit{
    
    /**
     * Simple constructor with suggested values for attack
     * and armor
     * 
     * @param name   String representing the name of the unit
     * @param health int representing the health of the unit
     */
    public Wizard(String name, int health) {
        // Inherits name and health from the super-class
        // But has own values for attack and armor
        super(name, health, 20, 20);
    }

    /**
     * More advanced constructor containing all fields
     * from the Unit super-class
     * 
     * @param name   String representing the units name
     * @param health int representing the heath of the unit
     * @param attack int representing the amount of damage possible for this unit
     * @param armor  int representing the protection around the unit
     */
    public Wizard(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public abstract String getType();

    /**
     * Abstract method from the Unit class.
     * Calculates the bonus damage which the unit can deal
     * 
     * @return int bonus. The units attack bonus
     *         starts at a value of 6, but decreases to 2 after the
     *         first attack
     */
    @Override
    public int getAttackBonus() {
        return 0;
    }

    /**
     * Abstract method from the Unit class.
     * Calculates the bonus resistance for the unit 
     * 
     * @return int 1, which is the add-on resistance
     *         which this unit recieves
     */
    @Override
    public int getResistBonus() {
        return 2 + getTerrainAttackAndResistBonus()[1];
    }

    /**
     * Calculates extra bonus damage and resistance depending on 
     * the units terrain.
     * 
     * @return int[] bonus representing extra damage- 
     *          and resist bonus where
     *          bonus[0] is attack bonus and bonus[1]
     *          is resist bonus.
     */
    @Override
    public int[] getTerrainAttackAndResistBonus() {
        int[] bonus = {0,0};
        if (this.getTerrain().equals("PLAINS")) {
            bonus[0] = 0;
            bonus[1] = -1;
        } else if (this.getTerrain().equals("FOREST")) {
            bonus[0] = 0;
            bonus[1] = 2;
        } else if (this.getTerrain().equals("HILL")) {
            bonus[0] = 0;
            bonus[1] = 1;
        }
        return bonus;
    }

    /**
     * Casts a spell on the opponent. Replaces the attack() method.
     */
    @Override
    public abstract void castSpell(Army army);
}   


