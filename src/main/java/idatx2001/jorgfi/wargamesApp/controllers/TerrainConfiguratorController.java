package idatx2001.jorgfi.wargamesApp.controllers;

import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * Controller for the terrain configurator scene
 *  
 * @author jorgfi
 */
public class TerrainConfiguratorController {



// FIELDS -------------------------------------------------------------------------------------------


    // Scenes
    private Scene simulatorScene;
    private Scene armyConfiguratorScene;

    // FXML Elements
    @FXML
    private MFXRadioButton radioNone;
    @FXML
    private MFXRadioButton radioHill;
    @FXML
    private MFXRadioButton radioPlains;
    @FXML
    private MFXRadioButton radioForest;

    // Constant containing image paths
    private static final String[] IMAGE_PATHS = {"/images/NONE.jpeg", "/images/HILL.jpeg",
                                         "/images/PLAINS.jpeg", "/images/FOREST.jpeg"};
    
    // Controllers
    private SimulatorController simulatorController;

    // Fields
    private String image;
    

    

// SETTERS ---------------------------------------------------------------------------------------------    
    


    /**
     * Sets the simulator scene
     * @param scene Scene to be set
     */
    public void setSimulatorScene(Scene scene) {
        this.simulatorScene = scene;
    }

    /**
     * Sets the army configurator scene
     * @param scene to be set
     */
    public void setArmyConfiguratorScene(Scene scene) {
        this.armyConfiguratorScene = scene;
    }

    /**
     * Sets the simulator controller
     * @param controller to be set
     */
    public void setSimulatorController(SimulatorController controller) {
        this.simulatorController = controller;
    }




    
// BUTTON METHODS -------------------------------------------------------------------------------------------



    /**
     * Determines which terrain picture to use and shows
     * the simulator page
     */              
    public void continueToSimulation(ActionEvent actionEvent) {

        // Determines terrain picture
        if (radioNone.isSelected()) {
            image = IMAGE_PATHS[0];
            simulatorController.setTerrain("NONE");
        } else if (radioHill.isSelected()) {
            image = IMAGE_PATHS[1];
            simulatorController.setTerrain("HILL");
        } else if (radioPlains.isSelected()) {
            image = IMAGE_PATHS[2];
            simulatorController.setTerrain("PLAINS");
        } else {
            image = IMAGE_PATHS[3];
            simulatorController.setTerrain("FOREST");
        }

        // Sends image to simulator page for display
        Image terrainImage = new Image(getClass().getResource(image).toExternalForm());
        simulatorController.setImage(terrainImage);
        
        // Swaps scene
        Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(simulatorScene);
        primaryStage.centerOnScreen();
        primaryStage.setMaxHeight(630);
        primaryStage.setMaxWidth(900);
        primaryStage.setMinHeight(630);
        primaryStage.setMinWidth(900);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }


    /**
     * Shows the army configurator scene
     */
    public void returnToLastPage(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(armyConfiguratorScene);
        primaryStage.centerOnScreen();
        primaryStage.setMaxHeight(620);
        primaryStage.setMaxWidth(900);
        primaryStage.setMinHeight(620);
        primaryStage.setMinWidth(900);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }
}
