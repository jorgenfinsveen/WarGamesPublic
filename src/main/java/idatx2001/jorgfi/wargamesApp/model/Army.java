package idatx2001.jorgfi.wargamesApp.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents an Army. It is a collection of different units
 * 
 * @author jorgfi
 */
public class Army {

    /**
     * Fields
     */
    private String name;
    private ArrayList<Unit> units;
    private ArrayList<Unit> fallenSoldiers = new ArrayList<>();
    private ArrayList<Unit> cursedSoldiers = new ArrayList<>();

    /**
     * Constructor which only takes the name parameter.
     * Declares a list which can be filled with
     * units through add() or addAll().
     * 
     * @param name String that represents the name of the army
     */
    public Army(String name) {
        if (name != null && !"".equals(name)) {
            this.name = name;
            this.units = new ArrayList<>();
        } else {
            throw new IllegalArgumentException("Invalid values for unit. Please check parameters and try again.");
        }
    }

    /**
     * Constructor which takes both name and units
     * as parameters. This constructor allows the user
     * to insert a prefilled list of units which the
     * army will consist of.
     * 
     * @param name  String that represents the name of the army
     * @param units  List which represents a list of units which are the
     *              soldiers of the army.
     */
    public Army(String name, ArrayList<Unit> units) {
        if (name != null && !" ".equals(name) && units.size() > 0) {
            this.name = name;
            // Declares an ArrayList and fills it with the contents of units
            this.units = new ArrayList<>();
            this.units.addAll(units);
        } else throw new IllegalArgumentException("Invalid values for unit. Please check parameters and try again.");
    }

    /**
     * Returns the name of the Army-object as a string
     * 
     * @return String name of the army
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns list of all fallen soldiers
     * 
     * @return ArrayList<Unit> list over fallen soldiers
     */
    public ArrayList<Unit> getFallenSoldiers() {
        return this.fallenSoldiers;
    }

    /**
     * Returns list of all cursed soldiers
     * 
     * @return ArrayList<Unit> list over cursed soldiers
     */
    public ArrayList<Unit> getCursedSoldiers() {
        return this.cursedSoldiers;
    }

 
    /**
     * Adds a unit to the cursedSoldiers list
     * 
     * @param unit to be added
     */
    public void addToCursedSoldiers(Unit unit) {
        cursedSoldiers.add(unit);
    }
    /**
     * Adds a single unit to the units ArrayList which
     * represents the army members.
     * 
     * @param unit to be added
     */
    public void add(Unit unit) {
        if (unit != null) {
            this.units.add(unit);
        } else {
            throw new IllegalArgumentException("The input was invalid");
        }
    }

    /**
     * Adds all elements of a list to the units
     * ArrayList, which holds the members of the army
     * 
     * @param units List of units to be added to the army
     */
    public void addAll(ArrayList<Unit> units) {
        if (units != null) {
            this.units.addAll(units);
        } else {
            throw new IllegalArgumentException("There was no warriors in the army");
        }
    }

    /**
     * Removes a unit from the units ArrayList in the Army instance.  
     * 
     * @param unit the unit which are to be removed
     */
    public void remove(Unit unit) {
        if (unit != null) {
            this.units.remove(unit);
        } else {
            throw new IllegalArgumentException("ERROR: Unit doesnt exist");
        }
        fallenSoldiers.add(unit);
    }

    /**
     * Checks if the army contains one or several units.
     * 
     * @return boolean (empty = true)
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }

    /**
     * Returns all the units in the army
     * 
     * @return List
     */
    public ArrayList<Unit> getAllUnits() {
        return units;
    }

    /**
     * Removes a fallen unit from the fallenSoldiers ArrayList
     * 
     * @param unit to be removed from the ArrayList
     */
    public void removeFromFallenSoldiers(Unit unit) {
        fallenSoldiers.remove(unit);
    }

    /**
     * Removes a cursed unit from the cursedSoldiers ArrayList
     * 
     * @return unit to be removed from the ArrayList
     */
    public void removeFromCursedSoldiers(Unit unit) {
        cursedSoldiers.remove(unit);
    }

    /**
     * Method that picks a random unit from the army
     * 
     * @return random Unit from the army
     */
    public Unit getRandom() {
        Unit randomUnit = null;
        if (hasUnits()) {
           randomUnit = units.get(new Random().nextInt(units.size()));
        }
        return randomUnit;
    }

    /**
     * Returns the amount of units in the army
     * 
     * @return int amount of units in the army
     */
    public int getArmySize() {
        return units.size();
    }

    /**
     * Checks if two objects are equal.
     * 
     * @param object to be compared with
     * @return boolean (equal = true)
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Army army = (Army) object;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     * Generates a hashcode based on the army name and contents
     * 
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }


    /** 
     * Makes a String with the given name and units of the Army
     * 
     * @return String containing name + units
     */
    @Override
    public String toString() {
        return "Army: " + "\n" +
                "name: " + this.name + "\n" +
                "units: " + this.units;
    }


    /**
     * Gets all RangedUnits in the army-instance
     * 
     * @return ArrayList containing all RangedUnits
     */
    public ArrayList<Unit> getAllRangedUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(RangedUnit.class::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Gets all InfantryUnits in the army-instance
     * 
     * @return ArrayList containing all InfantryUnits
     */
    public ArrayList<Unit> getAllInfantryUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(InfantryUnit.class::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Gets all CavalryUnits in the army-instance, but 
     * excludes the CommanderUnits which are sub-classes of
     * CavalryUnit
     * 
     * @return ArrayList containing all CavalryUnits exept CommanderUnits
     */
    public ArrayList<Unit> getAllCavalryUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(CavalryUnit.class::isInstance)
                .filter(unit -> !(unit instanceof CommanderUnit))
                .collect(Collectors.toList());
    }

    /**
     * Gets all CommanderUnits in the army-instance
     * 
     * @return ArrayList containing all CommanderUnits
     */
    public ArrayList<Unit> getAllCommanderUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(CommanderUnit.class::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Gets all GiantUnits in the army-instance
     * 
     * @return ArrayList containing all GiantUnits
     */
    public ArrayList<Unit> getAllGiantUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(GiantUnit.class::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Gets all WhiteWizardUnits in the army-instance
     * 
     * @return ArrayList containing all WhiteWizardUnits
     */
    public ArrayList<Unit> getAllWhiteWizardUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(WhiteWizardUnit.class::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Gets all DarkWizardUnits in the army-instance
     * 
     * @return ArrayList containing all DarkWizardUnits
     */
    public ArrayList<Unit> getAllDarkWizardUnits() {
        return (ArrayList<Unit>) getAllUnits().stream()
                .filter(DarkWizardUnit.class::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Represents a curse which will drain 5 hp from those
     * affected by it for each round of the game.
     * It uses setHealthWithoutAttack so that the curse, 
     * does not affect the amount of recieved hits
     */
    public void curseActivation() {
        for (Unit unit : cursedSoldiers) {
            unit.setHealthWithoutAttack(unit.getHealth() - 5);
            // This is a way to remove a unit from an army.
            if (!unit.isAlive()) {
                this.remove(unit);
            }
        }
    }
}
