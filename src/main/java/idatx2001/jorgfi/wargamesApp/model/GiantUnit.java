package idatx2001.jorgfi.wargamesApp.model;

/**
 * Represents a giant. The giant is an extra powerfull unit, but 
 * on the other hand, it is very slow, so a giant can only attack
 * once per third round that it has participated in. Inherits from
 * Unit-class.
 * 
 * @author jorgfi
 */
public class GiantUnit extends Unit{

    // TODO: Finn en bedre måte å oppdatere attackFrequency

    private static final String UNIT_TYPE = "Giant";
    
    /**
     * Simple constructor with suggested values for attack
     * and armor
     * 
     * @param name   String representing the name of the unit
     * @param health int representing the health of the unit
     */
    public GiantUnit(String name, int health) {
        // Inherits name and health from the super-class
        // But has own values for attack and armor
        super(name, health, 120, 80);
        updateAttackFrequency();
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
    public GiantUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
        updateAttackFrequency();
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public String getType() {
        return GiantUnit.UNIT_TYPE + "";
    }

    /**
     * Abstract method from the Unit class.
     * Calculates the bonus damage which the unit can deal.
     * The GiantUnit is very slow, and its attackspeed is only
     * 1/3 of a normal unit's attack speed'.
     * 
     * @return int bonus. The units attack bonus is 0
     *         
     */
    @Override
    public int getAttackBonus() {
        return getTerrainAttackAndResistBonus()[0];
    }

    /**
     * Abstract method from the Unit class.
     * Calculates the bonus resistance for the unit 
     * 
     * @return int bonus. The units resist bonus is 0
     */
    @Override
    public int getResistBonus() {
        return 0 + getTerrainAttackAndResistBonus()[1];
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
            bonus[0] = 15;
            bonus[1] = -10;
        } else if (this.getTerrain().equals("FOREST")) {
            bonus[0] = 20;
            bonus[1] = 10;
        } else if (this.getTerrain().equals("HILL")) {
            bonus[0] = 5;
            bonus[1] = -5;
        } else {
            bonus[0] = 0;
            bonus[1] = 0;
        }
        return bonus;
    }

    /**
     * Sets a new value for the attack frequency
     * 
     * @param int amount of turns before next attack
     */
    public void updateAttackFrequency() {
        if (this.getTerrain().equals("PLAINS")) setAttackFrequency(2);
        else if (this.getTerrain().equals("FOREST")) setAttackFrequency(4);
        else if (this.getTerrain().equals("HILL")) setAttackFrequency(3);
        else setAttackFrequency(3);
    }


    @Override
    public void attack(Unit opponent) {
        if (this.getNumberOfDealtHits() % getAttackFrequency() == 0) {
            int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus())
                    + (opponent.getArmor() + opponent.getResistBonus());
            // An attack can not give more health to a unit
            if (newHealth > opponent.getHealth()) newHealth = opponent.getHealth();
            opponent.setHealth(newHealth);
        } 
        incrementNumberOfDealtHits();
    }
}
