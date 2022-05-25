package idatx2001.jorgfi.wargamesApp.model;

/**
 * Represents a Unit in the game. The subclasses inherit
 * the Unit-class methods. Is an abstract class.
 * 
 * @author jorgfi
 * @version 1.0-SNAPSHOT
 */
public abstract class Unit {

    // Fields
    private String name;
    private int health;
    private int attack;
    private int armor;
    private Terrain terrain;
    private int attackFrequency;
    private int numberOfDealtHits;
    private int amountOfRecievedHits;

    private static enum Terrain {
        NONE, HILL, PLAINS, FOREST
    }

    /**
     * Constructor initializing a Unit-instance. 
     * 
     * @param name   String name of the unit
     * @param health int the quantified status of a units life
     * @param attack int representing the amount of damage that the unit is
     *               capable of causing
     * @param armor  int representing extra protection on top of the units health
     */
    public Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        if (name.isBlank()) throw new IllegalArgumentException("You must give the unit a name");
        if (health <= 0) throw new IllegalArgumentException("Health must be above 0");
        if (attack <= 0) throw new IllegalArgumentException("Attack must be above 0");
        this.name = name;
        this.setHealth(health);
        this.attack = attack;
        this.armor = armor;
        this.numberOfDealtHits = 0;
        this.amountOfRecievedHits = 0;
        this.terrain = Terrain.NONE;
        this.attackFrequency = 1;
    }

// ---------------- ACCESSORS --------------------------------

    /**
     * Returns the name of the unit-object
     * 
     * @return String name of the unit
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the health of the unit-object
     * 
     * @return int health of the unit
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the attack for this unit-object
     * 
     * @return int health of the unit
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Gets the armor for this unit-object
     * 
     * @return int armor of the unit
     */
    public int getArmor() {
        return this.armor;
    }

    /**
     * Returns the terrain at the units location.
     * 
     * @return String terrain name e.g "PLAINS"
     */
    public String getTerrain() {
        return String.valueOf(this.terrain);
    }

    /**
     * Generating a string which shows the
     * units name and remaining health.
     * 
     * @return String consisting the units name and health
     */
    @Override
    public String toString() {
        return getName() + " has " + getHealth() + "hp left.";
    }

    /**
     * Get the attack frequency
     * 
     * @return attackFrequency frequence of the attack
     */
    public int getAttackFrequency() {
        return this.attackFrequency;
    }

    /**
     * Get number of hits dealt by unit
     * @return int number of dealt hits
     */
    public int getNumberOfDealtHits() {
        return this.numberOfDealtHits;
    }

    /**
     * Get amount of hits recieved by unit
     * @return int amount number of recieved hits
     */
    public int getAmountOfRecievedHits() {
        return this.amountOfRecievedHits;
    }

    /**
     * Returns attack bonus depening on the Unit sub-class which inherits this method. 
     * @return int value representing extra damage
     */
    public abstract int getAttackBonus();

    /**
     * Returns unique resist bonus for each sub-class which inherits from this class. 
     * 
     * @return in value representing extra protection
     */
    public abstract int getResistBonus();

    /**
     * Returns further attack and resist bonus depending on the terrain
     * @return int[] value representing extra damage and resistance
     */
    public abstract int[] getTerrainAttackAndResistBonus();

    /**
     * Returns the type of the unit 
     * 
     * @return String type of the unit
     */
    public abstract String getType();

    public void castSpell(Army attacker) {
    }


    /**
     * Checks if the units health is 0 or not
     * 
     * @return boolean indicating if the units is alive or not
     */
    public boolean isAlive() {
        boolean alive = true;
        if (this.getHealth() <= 0) {
            alive = false;
        } 
        return alive;
    }



// ---------------- MUTATORS --------------------------------

    /**
     * Increments numberOfDealtHits by 1
     */
    public void incrementNumberOfDealtHits() {
        this.numberOfDealtHits++;
    }

    /**
     * Increments amountOfRecievedHits by 1
     */
    public void incrementAmountOfRecievedHits() {
        this.amountOfRecievedHits++;
    }

    /**
     * Mutates the health-field of the unit.
     * 
     * @param health int the new health which will be adjusted to
     */
    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0;
        }
        incrementAmountOfRecievedHits();
    }

    /**
     * Mutates the health-field but it does not count as an attack
     * @param health int the units new health
     */
    public void setHealthWithoutAttack(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Mutates the terrain of the unit. 
     * 
     * @param terrain Terrain to be set.
     */
    public void setTerrain(String terrain) {
        if ("HILL".equals(terrain.toUpperCase())) {
            this.terrain = Terrain.HILL;
        } else if ("FOREST".equals(terrain.toUpperCase())) {
            this.terrain = Terrain.FOREST;
        } else if ("PLAINS".equals(terrain.toUpperCase())) {
            this.terrain = Terrain.PLAINS;
        } else {
            this.terrain = Terrain.NONE;
        }
    } 

    /**
     * Deploys an attack on a opponent.
     * 
     * @param opponent Unit which are to be attacked
     */
    public void attack(Unit opponent) {
        int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus())
                + (opponent.getArmor() + opponent.getResistBonus());
        // An attack can not give more health to a unit
        if (newHealth > opponent.getHealth()) newHealth = opponent.getHealth();
        opponent.setHealth(newHealth);
        incrementNumberOfDealtHits();
    }

    /**
     * Sets a new value for the attack frequency
     * 
     * @param int amount of turns before next attack
     */
    public void setAttackFrequency(int frequency) {
            this.attackFrequency = frequency;
        }
    }

