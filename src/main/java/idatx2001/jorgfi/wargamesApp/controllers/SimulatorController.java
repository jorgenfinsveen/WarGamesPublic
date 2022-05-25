package idatx2001.jorgfi.wargamesApp.controllers;

import idatx2001.jorgfi.wargamesApp.model.Army;
import idatx2001.jorgfi.wargamesApp.model.Battle;
import idatx2001.jorgfi.wargamesApp.model.Unit;
import idatx2001.jorgfi.wargamesApp.tools.Table;
import idatx2001.jorgfi.wargamesApp.tools.TableEntry;
import idatx2001.jorgfi.wargamesApp.tools.UnitFactory;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;





/**
 * Controller for the simulator scene
 *  
 * @author jorgfi
 */
public class SimulatorController {


// FIELDS ------------------------------------------------------------------------------------------    



    // Scenes
    private Scene terrainConfiguratorScene;


    // FXML Elements
    @FXML
    private ImageView simulatorImageView;
    @FXML
    private TextField winnerTextField;
    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;
    @FXML
    private Text terrainTxt;
    @FXML
    private Text unitsLeft1;
    @FXML
    private Text unitsLeft2;
    

    // Terrain 
    private String terrain;


    // Tables
    private Table table1;
    private Table table2;


    // Armies
    private Army army1;
    private Army army2;


    // Battle
    private Battle battle;


    // Armynames
    private String army1Name;
    private String army2Name;


    
// SETTERS ------------------------------------------------------------------------------------------
 


    /**
     * Sets image representing the chosen terrain
     * @param terrainImage Image to be shown
     */
    public void setImage(Image terrainImage) {
        simulatorImageView.setImage(terrainImage);
    }


    /**
     * Sets the terrain configurator scene
     * @param scene Scene to be set
     */
    public void setTerrainConfiguratorScene(Scene scene) {
        this.terrainConfiguratorScene = scene;
    }


    /**
     * Sets the terrain to use in the simulation
     * @param terrain String terrain to use
     */
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }


    /**
     * Sets the tables containing the units created in the army configurator
     * @param table1 Table containing army 1
     * @param table2 Table containing army 2
     */
    public void setTables(Table table1, Table table2) {
        this.table1 = table1;
        this.table2 = table2;
    }


    /**
     * Sets the army names
     * @param name1 String name of army1
     * @param name2 String name of army2
     */
    public void setArmyNames(String name1, String name2) {
        this.army1Name = name1;
        this.army2Name = name2;
    }


// BUTTON METHODS -----------------------------------------------------------------------------------
    


    /**
     * Starts the simulation between the armies
     */
    public void simulateBattle(ActionEvent actionEvent) {
        
        textArea1.clear();
        textArea2.clear();

        army1 = createArmiesFromTable(table1, army1Name);
        army2 = createArmiesFromTable(table2, army2Name);
        battle = new Battle(army1, army2);

        String winner = "The winner is " + battle.simulate().getName();

        ArrayList<String> log1 = battle.getLog1();
        ArrayList<String> log2 = battle.getLog2();

        Iterator<String> it1 = log1.iterator();
        Iterator<String> it2 = log2.iterator();

        int counter = 0;
        while (it1.hasNext() || it2.hasNext()) {
            if (counter%2 == 0) {
                textArea1.appendText(it1.next() + "\n");
            } else {
                textArea2.appendText(it2.next() + "\n");
            }
            counter++;
        }
        unitsLeft1.setText("Units left: " + army1.getArmySize());
        unitsLeft2.setText("Units left: " + army2.getArmySize());
        winnerTextField.setText(winner + "!");
    }


    /**
     * Sets the current scene to terrain configurator.
     */
    public void returnToTerrainConfig(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(terrainConfiguratorScene);
        primaryStage.centerOnScreen();
        primaryStage.setFullScreen(false);
        primaryStage.setMaxHeight(420);
        primaryStage.setMaxWidth(250);
        primaryStage.setMinHeight(420);
        primaryStage.setMinWidth(250);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }




// BATTLE CONFIGURATIONS -----------------------------------------------------------------------------




    public Army createArmiesFromTable(Table table, String armyName) {
        ArrayList<TableEntry> customUnits = new ArrayList<>();
        ArrayList<TableEntry> defaultUnits = new ArrayList<>();
        ArrayList<Unit> armyList = new ArrayList<>();
        if (table.getTable().size() == 0) {
            throw new IllegalArgumentException("Table must have at least one unit");
        } else {

            for (TableEntry entry : table.getTable()) {
                if (entry.isCustom()) {
                    customUnits.add(entry);
                    armyList.addAll(UnitFactory.createListOfCustomUnits(entry.getAmount(), entry.getType(), 
                                                                    entry.getName(), entry.getHealth(),
                                                                    entry.getAttack(), entry.getArmor()));
                } else {
                    defaultUnits.add(entry);
                    armyList.addAll(UnitFactory.createListOfUnits(entry.getAmount(), entry.getType(), 
                                                                    entry.getName(), entry.getHealth()));
                }
            }
            return new Army(armyName, armyList);
        }
    }
}
