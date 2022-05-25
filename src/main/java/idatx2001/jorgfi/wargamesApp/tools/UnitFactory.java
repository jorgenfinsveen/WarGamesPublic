package idatx2001.jorgfi.wargamesApp.tools;

import idatx2001.jorgfi.wargamesApp.model.CavalryUnit;
import idatx2001.jorgfi.wargamesApp.model.CommanderUnit;
import idatx2001.jorgfi.wargamesApp.model.DarkWizardUnit;
import idatx2001.jorgfi.wargamesApp.model.GiantUnit;
import idatx2001.jorgfi.wargamesApp.model.InfantryUnit;
import idatx2001.jorgfi.wargamesApp.model.RangedUnit;
import idatx2001.jorgfi.wargamesApp.model.Unit;
import idatx2001.jorgfi.wargamesApp.model.WhiteWizardUnit;
import java.util.ArrayList;
import java.util.List;




/**
 * Represents a factory for units in the wargame. 
 * Contains methods for creating a single og several units. 
 * 
 * @author jorgfi
 */
public class UnitFactory {
    
    public static final List<String> UNIT_TYPES = List.of("Ranger", "Infantry", "Commander",
                                                          "Cavalry", "White wizard",
                                                          "Dark wizard", "Giant");


    public static final List<String> TERRAINS = List.of("NONE", "HILL", "PLAINS", "FOREST");



// SINGELTONS --------------------------------------------------------------------------------------------

    /** 
     * Creates a new unit with the given type, name and health
     * 
     * @param type String type of unit e.g. CavalryUnit
     * @param name String name which the unit will be called
     * @param health int health of the unit
     * @return Unit
     */
    public static Unit createNewUnit(String type, String name, int health) {
        if (health <= 0 || name.isEmpty()) {return null;}
        switch (type) {
            // This is a switch statement. It is checking the type of unit and returning the correct
            // type of unit.
            case "Ranger": return new RangedUnit(name, health);
            case "Infantry": return  new InfantryUnit(name, health);
            case "Commander": return new CommanderUnit(name, health);
            case "Cavalry": return new CavalryUnit(name, health);
            case "White wizard": return new WhiteWizardUnit(name, health);
            case "Dark wizard": return new DarkWizardUnit(name, health);
            case "Giant": return new GiantUnit(name, health);
            default: return null;
        }
    }

    /**
     * Creates a custom unit with full field flexibility
     * 
     * @param type String type of the units
     * @param name String what to call the units
     * @param health int health of the units
     * @param attack int attack value
     * @param armor int protection value
     * @return Unit produced
     */
    public static Unit createNewCustomUnit(String type, String name, int health,int attack, int armor) {
        if (health <= 0 || name.isEmpty() || attack <= 0 || armor <= 0) {return null;}
        switch (type) {
            // This is a switch statement. It is checking the type of unit and returning the correct
            // type of unit.
            case "Ranger": return new RangedUnit(name, health, attack, armor);
            case "Infantry": return  new InfantryUnit(name, health, attack, armor);
            case "Commander": return new CommanderUnit(name, health, attack, armor);
            case "Cavalry": return new CavalryUnit(name, health, attack, armor);
            case "White wizard": return new WhiteWizardUnit(name, health, attack, armor);
            case "Dark wizard": return new DarkWizardUnit(name, health, attack, armor);
            case "Giant": return new GiantUnit(name, health, attack, armor);
            default: return null;
        }
    }








// MULTITONS ----------------------------------------------------------------------------------------
   


    /**
     * Creates several units of a given type, name and health in a list.
     * This will create n identical units with matching fields.
     * 
     * @param amount int amount of units to create
     * @param type String type of the units
     * @param name String what to call the units
     * @param health int health of the units
     * @return List of units produced
     * @throws IllegalArgumentException if the parameters are invalid
     */
    public static List<Unit> createListOfUnits(int amount, String type, String name, int health) throws IllegalArgumentException {
        List<Unit> units = new ArrayList<>();

        if (!(UNIT_TYPES.contains(type))) {throw new IllegalArgumentException("That is not a valid type");}
        if (amount <= 0) {throw new IllegalArgumentException("There must be at least one unit");}
        if (health <= 0) {throw new IllegalArgumentException("Health must be greater than 0");}
        if (name.isEmpty()) {throw new IllegalArgumentException("Name must not be empty");}

        for(int i = 0; i < amount; i++) {
            units.add(createNewUnit(type, name, health));
        }
        return units;
    }


    
    /**
     * Creates several custom units of a given type in a list.
     * This will create n identical units with matching fields.
     * 
     * @param amount int amount of units to create
     * @param type String type of the units
     * @param name String what to call the units
     * @param health int health of the units
     * @param attack int attack value
     * @param armor int protection value
     * @return List of units produced
     * @throws IllegalArgumentException in case of an invalid parameters
     */
    public static List<Unit> createListOfCustomUnits(int amount, String type, String name, int health, int attack, int armor) throws IllegalArgumentException {
        List<Unit> units = new ArrayList<>();
        
        if (!(UNIT_TYPES.contains(type))) {throw new IllegalArgumentException("That is not a valid type");}
        if (amount <= 0) {throw new IllegalArgumentException("There must be at least one unit");}
        if (health <= 0) {throw new IllegalArgumentException("Health must be greater than 0");}
        if (name.isEmpty()) {throw new IllegalArgumentException("Name must not be empty");}
        if (attack <= 0) {throw new IllegalArgumentException("Attack must be greater than 0");}
        if (armor <= 0) {throw new IllegalArgumentException("Armor must be greater than 0");}
    
        for(int i = 0; i < amount; i++) {
            units.add(createNewCustomUnit(type, name, health, attack, armor));
        }
        return units;
    }






// TERRAIN MUTATORS ----------------------------------------------------------------------------------    



    /**
     * Sets terrain on a single unit
     * @param unit Unit to alter
     * @param terrain String new terrain
     */
    public static void modifyUnitTerrain(Unit unit, String terrain) {
        if (TERRAINS.contains(terrain)) {
            unit.setTerrain(terrain);
        } else {
            throw new IllegalArgumentException("Not a valid terrain");
        }
    }
    


    /**
     * Sets terrain on multiple units
     * @param armyList ArrayList containing units to alter
     * @param terrain String new terrain
     */
    public static void modifyArmyTerrain(ArrayList<Unit> armyList, String terrain) {
        if (TERRAINS.contains(terrain)) {
            for (Unit unit : armyList) {
                unit.setTerrain(terrain);
            }
        } else {
            throw new IllegalArgumentException("Not a valid terrain");
        }
    }
}
