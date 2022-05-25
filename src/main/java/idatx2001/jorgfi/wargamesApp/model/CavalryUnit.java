package idatx2001.jorgfi.wargamesApp.model;

/**
 * Sub-class that inherit from the super class Unit representing
 * a unit of the type cavalry in the game.
 * 
 * @author jorgfi
 */
public class CavalryUnit extends Unit {

    
    private static final String UNIT_TYPE = "Cavalry";
    

    /**
     * Simple constructor with suggested values for attack
     * and armor
     * 
     * @param name   String representing the name of the unit
     * @param health int representing the health of the unit
     */
    public CavalryUnit(String name, int health) {
        // Inherits name and health from the super-class
        // But has own values for attack and armor
        super(name, health, 20, 12);
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
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public String getType() {
        return CavalryUnit.UNIT_TYPE + "";
    }

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
        int bonus;
        if (getNumberOfDealtHits() == 0) {
            bonus = 6;
        } else {
            bonus = 2;
        }
        if (getTerrainAttackAndResistBonus()[0] != -100) {
            bonus += getTerrainAttackAndResistBonus()[0];
        } else {
            bonus = 0;
        }
        return bonus;
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
        int bonus = 1;
        if (getTerrainAttackAndResistBonus()[1] != -100) {
            bonus += getTerrainAttackAndResistBonus()[1];
        } else {
            bonus = 0;
        }
        return bonus;
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
            bonus[0] = 2;
            bonus[1] = 0;
        } else if (this.getTerrain().equals("FOREST")) {
            bonus[0] = 0;
            bonus[1] = -100;
        }
        return bonus;
    }
}   