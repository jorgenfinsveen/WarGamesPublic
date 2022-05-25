package idatx2001.jorgfi.wargamesApp.model;

/**
 * Represents a more capable verion of a CavalryUnit. It
 * inherits all the fields and methods from the CavalryUnit class,
 * but it has a better default values for attack and armor.
 * 
 * @author jorgfi
 */
public class CommanderUnit extends CavalryUnit {


    private static final String UNIT_TYPE = "Commander";

    /**
     * Simplified constructor with default values for attack and armor
     * 
     * @param name   String representing the commanders name
     * @param health int representing the commanders health
     */
    public CommanderUnit(String name, int health) {
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
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    @Override
    public String getType() {
        return CommanderUnit.UNIT_TYPE + "";
    }
}
