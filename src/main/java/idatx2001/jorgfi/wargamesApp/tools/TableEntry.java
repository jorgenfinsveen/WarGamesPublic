package idatx2001.jorgfi.wargamesApp.tools;



/**
 * Represents an entry in a table. The entry contains
 * information regarding a pack of units made by the user.
 * Each TableEntry object can be compared to an inqury of
 * a number of units.
 * 
 * @author jorgfi
 */
public class TableEntry {
    
    // Fields
    private String type;
    private String name;
    private int amount;
    private int health;
    private int attack;
    private int armor;
    private boolean customAttackAndArmor;
    
    /**
     * Constructs an object of TableEntry with default properties
     * @param type String type of unit
     * @param name String name of the unit
     * @param amount Int amount of units
     * @param health Int health of units
     */
    public TableEntry(String type, String name, int amount, int health)  {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.health = health;
        this.customAttackAndArmor = false;
    }

    /**
     * Constructs an object of TableEntry with custom fields
     * @param type String type of unit
     * @param name String name of the unit
     * @param amount Int amount of units
     * @param health Int health of units
     * @param attack Int attack value
     * @param armor Int armor value
     */
    public TableEntry(String type, String name, int amount, int health, int attack, int armor)  {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.customAttackAndArmor = true;
    }

    /**
     * Accesses the type field
     * @return String type 
     */
    public String getType() {
        return this.type;
    }

    /**
     * Accesses the name field
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accesses the amount field
     * @return int amount
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Returns an integer value representing unit health
     * @return Integer unit health value
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the attack value
     * @return Int attack
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Returns the armor value
     * @return Int armor
     */
    public int getArmor() {
        return this.armor;
    }

    /**
     * Returns true if attack and armor are not default values
     * @param Boolean 
     */
    public boolean isCustom() {
        return this.customAttackAndArmor;
    }

    /**
     * Mutates the type field
     * @param type String new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Mutates the name field
     * @param name String new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutates the amount field
     * @param amount int new amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Mutates the attack field
     * @param attack int new attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Mutates the armor field
     * @param armor int new armor
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Mutates the isCustom field
     * @param isCustom boolean true if custom
     */
    public void setIsCustom(boolean isCustom) {
        this.customAttackAndArmor = isCustom;
    }

    /**
     * Mutates health field
     * @param health int new value
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
