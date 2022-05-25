package idatx2001.jorgfi.wargamesApp.tools;
import idatx2001.jorgfi.wargamesApp.model.Army;
import idatx2001.jorgfi.wargamesApp.model.Battle;

/**
 * This is a class representing the design pattern for a facade.
 * It is currently not in use in the application, but it would be
 * a good idea to put it to use in future version of WarGames.
 * 
 * @author jorgfi
 */
public class Facade {
    

// FIELDS --------------------------------------------------------------------------------------------------------------------------------


    private Army armyOne;
    private Army armyTwo;
    private Battle simulation;
    private static Facade instance;
    



// CONSTRUCTOR --------------------------------------------------------------------------------------------------
    

    /**
     * Constructs a new facade
     */
    public Facade(){
        armyOne = new Army("Army1");
        armyTwo = new Army("Army2");
        simulation = new Battle(armyOne, armyTwo);

    }



// ACCESSORS ------------------------------------------------------------------------------------------------
   

    /**
     * Gets the current instance of the facade
     * @return Facade current instance
     */
    public static Facade getInstance() {
        if (instance == null){
            synchronized (Facade.class){
                instance = new Facade();
            }
        }
        return instance;
    }


    /**
     * Gets the battle which are simulated
     * @return Battle simulation between armyOne and armyTwo
     */
    public Battle getSimulation() {
        return this.simulation;
    }


    /**
     * Gets the first army
     * @return Army army1
     */
    public Army getArmy1() {
        return this.armyOne;
    }


    /**
     * Gets the second army
     * @return Army army2
     */
    public Army getArmy2() {
        return this.armyTwo;
    }



// MUTATORS -------------------------------------------------------------------------------------------------


    /**
     * Sets the battle to use in the simulation
     * @param simulation Battle simulation to work with
     */
    public void setSimulation(Battle simulation) {
        this.simulation = simulation;
    }


    /**
     * Sets the first army
     * @param newArmy Army to set to
     */
    public void setArmy1(Army newArmy) {
        this.armyOne = newArmy;
    }


    /**
     * Sets the second army
     * @param newArmy Army to set to
     */
    public void setArmy2(Army newArmy) {
        this.armyTwo = newArmy;
    }

    
    /**
     * Resets armyOne, armyTwo and the simulation
     */
    public void resetAll(){
        setArmy1(new Army("Army1"));
        setArmy2(new Army("Army2"));
        setSimulation(new Battle(armyOne, armyTwo));
    }
}

