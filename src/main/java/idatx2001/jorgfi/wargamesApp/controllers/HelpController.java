package idatx2001.jorgfi.wargamesApp.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Controller for the help page
 * 
 * @author jorgfi
 */
public class HelpController {


// FIELDS -------------------------------------------------------------------------------------------------


    // Scenes
    private Scene startupScene;



    
// SETTER --------------------------------------------------------------------------------------------------------------------------------


    /**
     * Sets the startup scene
     * @param scene Scene to set
     */
    public void setStartupScene(Scene scene) {
        this.startupScene = scene;
    }




// BUTTON METHODS -------------------------------------------------------------------------------------------

    /**
     * Activates when the return button is clicked. Switches scene to
     * startup page scene
     */
    public void returnToStartup(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(startupScene);
        primaryStage.centerOnScreen();
        primaryStage.setMaxHeight(400);
        primaryStage.setMaxWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }
}
