package idatx2001.jorgfi.wargamesApp.model;

/**
 * Represents a unit of the type infantry in the wargame.
 * Inherits from the Unit-class.
 * 
 * @author jorgfi
 */
public class InfantryUnit extends Unit {

    private static final String UNIT_TYPE = "Infantry";

    /**
     * Simplified constructor with default values for attack and armor
     * 
     * @param name   String representing the name of the infantry-unit
     * @param health int the health of the infantry-unit
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    /**
     * Constructs an InfantryUnit-instance with all fields customizeable.
     * 
     * @param name   String representing the name of the infantry-unit
     * @param health int the health of the infantry-unit
     * @param attack int the amount of damage that the unit can do
     * @param armor  int the protection of the unit
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public String getType() {
        return InfantryUnit.UNIT_TYPE + "";
    }

    /**
     * Abstract method inherited from the Unit-class returning 
     * the attack bonus of this unit
     * 
     * @return int the amount of extra damage that the unit can
     *         deal.
     */
    @Override
    public int getAttackBonus() {
        return 2 + getTerrainAttackAndResistBonus()[0];
    }

    /**
     * Abstract method inherited from the Unit-class returning 
     * the resist bonus of this unit
     * 
     * @return int the amount of extra resistanse that the unit has.
     */
    @Override
    public int getResistBonus() {
        return 1 + getTerrainAttackAndResistBonus()[1];
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
        if (this.getTerrain().equals("FOREST")) {
            bonus[0] = 2;
            bonus[1] = 2;
        }
        return bonus;
    }
}
