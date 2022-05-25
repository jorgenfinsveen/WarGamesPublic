package idatx2001.jorgfi.wargamesApp.controllers;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Controller for the startup scene
 *  
 * @author jorgfi
 */
public class StartupController {

// FIELDS --------------------------------------------------------------------------------------------------------------------


    // Scenes
    private Scene armyConfiguratorScene;
    private Scene helpPageScene;



// SETTERS --------------------------------------------------------------------------------------------------


    /**
     * Sets the army configurator scene
     * @param scene Scene to be set
     */
    public void setArmyConfiguratorScene(Scene scene) {
        this.armyConfiguratorScene = scene;
    }


    /**
     * Sets the help page scene
     * @param scene Scene to be set
     */
    public void setHelpPageScene(Scene scene) {
        this.helpPageScene = scene;
    }
    


// BUTTON METHODS --------------------------------------------------------------------------------------------------



    /**
     * Changes the scene to the army configurator scene
     */
    public void startApplication(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(this.armyConfiguratorScene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(620);
        primaryStage.setMinWidth(900);
        primaryStage.setMaxHeight(620);
        primaryStage.setMaxWidth(900);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }


    /**
     * Cancel the application
     */
    public void exitApplication(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit Wargemes");
        alert.setContentText("Are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    /**
     * Shows the help page when help-button is clicked
     */
    public void showHelp(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(this.helpPageScene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }
}
