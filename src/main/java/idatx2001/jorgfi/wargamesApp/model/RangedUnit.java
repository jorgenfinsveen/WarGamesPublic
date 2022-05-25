package idatx2001.jorgfi.wargamesApp.model;

/**
 * Represents a ranger unit in the game.
 * Inherits from the Unit-class.
 * 
 * @author jorgfi
 */
public class RangedUnit extends Unit {

    private static final String UNIT_TYPE = "Ranger";

    /**
     * Field Represents the amount of attacks that
     * the unit has recieved. Is relevant for the
     * getResistBonus() method.
     */

    /**
     * Simplified constructor with default values for attack and armor
     * 
     * @param name   String representing the name of the ranger
     * @param health int the health of the ranger
     */
    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
    }

    /**
     * Constructs a RangedUnit-instance without default values for attack and armor
     * 
     * @param name   String representing the name of the ranger
     * @param health int the health of the ranger
     * @param attack int the rangers damage capability
     * @param armor  int the rangers protection
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public String getType() {
        return RangedUnit.UNIT_TYPE + "";
    }

    /**
     * Calculates the attack bonus of this unit and returns it.
     * Abstract method inherited from the Unit-class.
     * 
     * @return int the amount of extra damage that the unit can
     *          deal to the opponent.
     */
    @Override
    public int getAttackBonus() {
        return 3 + getTerrainAttackAndResistBonus()[0];
    }

    /**
     * Calculates the resist bonus of this unit and returns it.
     * Abstract method inherited from the Unit-class.
     * 
     * @return the amount of extra protection that the unit
     *         recieves. Starts with a resistance-value of 6, but
     *         decreases by 2 after first and second attack dealt
     *         to this opponent. Stabilizes at a resistbonus of 2
     */
    @Override
    public int getResistBonus() {
        int bonus = 0;
        
        if (getAmountOfRecievedHits() == 0) {
            bonus = 6;
        } else if (getAmountOfRecievedHits() == 1) {
            bonus = 4;
        } else {
            bonus = 2;
        }
        return bonus + getTerrainAttackAndResistBonus()[1];
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
        if (this.getTerrain().equals("HILL")) {
            bonus[0] = 2;
            bonus[1] = 0;
        } else if (this.getTerrain().equals("FOREST")) {
            bonus[0] = -1;
            bonus[1] = 0;
        }
        return bonus;
    }
}
