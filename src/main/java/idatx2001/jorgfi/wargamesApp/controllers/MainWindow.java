package idatx2001.jorgfi.wargamesApp.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


/**
 * Facade creating all scenes and controllers.
 *  
 * @author jorgfi 
 */
public class MainWindow extends Application {


// FIELDS -------------------------------------------------------------------------------------------


    // Path to the controllers package
    private static final String PATH = "/idatx2001/jorgfi/wargamesApp/controllers";


    // The primary stage
    private static Stage primaryStage;

    // The different scenes making up the application
    private Scene mainScene;
    private Scene armyConfiguratorScene;
    private Scene unitConficuratorScene1;
    private Scene unitConficuratorScene2;
    private Scene terrainConfiguratorScene;
    private Scene simulatorScene;
    private Scene helpScene;
  


// INITIALIZATION -------------------------------------------------------------------------------------

    /**
     * Creates all scenes and controllers, and distribute them where necessary.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
      
        try {
            // Setting primaryStage
            this.primaryStage = primaryStage;



            // Getter and loader for startup page
            FXMLLoader mainPaneLoader = new FXMLLoader(getClass().getResource("startup_page.fxml"));
            Parent mainPane = mainPaneLoader.load();
            this.mainScene = new Scene(mainPane, 600, 400);
        
            // Getter and loader for army configurator page
            FXMLLoader armyConfiguratorPageLoader = new FXMLLoader(getClass().getResource("army_configurator.fxml"));
            Parent armyConfiguratorPane = armyConfiguratorPageLoader.load();
            this.armyConfiguratorScene = new Scene(armyConfiguratorPane);
            
            // Getter and loader for unit configurator page 1
            FXMLLoader unitConfiguratorPageLoader1 = new FXMLLoader(getClass().getResource("unit_configurator1.fxml"));
            Parent unitConfiguratorPane1 = unitConfiguratorPageLoader1.load();
            this.unitConficuratorScene1 = new Scene(unitConfiguratorPane1);

            // Getter and loader for unit configurator page 2
            FXMLLoader unitConfiguratorPageLoader2 = new FXMLLoader(getClass().getResource("unit_configurator2.fxml"));
            Parent unitConfiguratorPane2 = unitConfiguratorPageLoader2.load();
            this.unitConficuratorScene2 = new Scene(unitConfiguratorPane2);

            // Getter and loader for terrain configurator 
            FXMLLoader terrainConfiguratorPageLoader = new FXMLLoader(getClass().getResource("terrain_configurator.fxml"));
            Parent terrainConfiguratorPane = terrainConfiguratorPageLoader.load();
            this.terrainConfiguratorScene = new Scene(terrainConfiguratorPane);

            // Getter and loader for simulator page
            FXMLLoader simulatorPageLoader = new FXMLLoader(getClass().getResource("simulator.fxml"));
            Parent simulatorPane = simulatorPageLoader.load();
            this.simulatorScene = new Scene(simulatorPane);

            // Getter and loader for help page
            FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("help_page.fxml"));
            Parent helpPane = helpLoader.load();
            this.helpScene = new Scene(helpPane);




            // Controllers
            StartupController startupController = mainPaneLoader.getController();
            ArmyConfiguratorController armyConfiguratorController = armyConfiguratorPageLoader.getController();
            TerrainConfiguratorController terrainConfiguratorController = terrainConfiguratorPageLoader.getController();
            SimulatorController simulatorController = simulatorPageLoader.getController();
            UnitConfiguratorController1 unitConfiguratorController1 = unitConfiguratorPageLoader1.getController();
            UnitConfiguratorController2 unitConfiguratorController2 = unitConfiguratorPageLoader2.getController();
            HelpController helpController = helpLoader.getController();



            // Creating new stages
            Stage stage1 = new Stage();
            stage1.setScene(unitConficuratorScene1);
            Stage stage2 = new Stage();
            stage2.setScene(unitConficuratorScene2);


            // Distributing scenes and controllers
            startupController.setArmyConfiguratorScene(this.armyConfiguratorScene);
            startupController.setHelpPageScene(this.helpScene);

            armyConfiguratorController.setUnitConficuratorScene(this.unitConficuratorScene1, this.unitConficuratorScene2);
            armyConfiguratorController.setTerrainConfiguratorScene(this.terrainConfiguratorScene);
            armyConfiguratorController.setUnitConfiguratorController(unitConfiguratorController1, unitConfiguratorController2);
            armyConfiguratorController.setSimulatorController(simulatorController);

            terrainConfiguratorController.setSimulatorScene(this.simulatorScene);
            terrainConfiguratorController.setSimulatorController(simulatorController);
            terrainConfiguratorController.setArmyConfiguratorScene(armyConfiguratorScene);

            unitConfiguratorController1.setArmyConfiguratorController(armyConfiguratorController);
            unitConfiguratorController1.setPrimaryStage(stage1);
            unitConfiguratorController2.setArmyConfiguratorController(armyConfiguratorController);
            unitConfiguratorController2.setPrimaryStage(stage2);

            simulatorController.setTerrainConfiguratorScene(terrainConfiguratorScene);

            helpController.setStartupScene(mainScene);

            

            
            // Shows the startup page
            primaryStage.setTitle("WarGames");
            primaryStage.setResizable(false);
            primaryStage.setScene(this.mainScene);
            primaryStage.show();
            
            primaryStage.setOnCloseRequest(event ->{
                event.consume();   
                exitApplication(primaryStage);
            }); 

        } catch (Exception e) {
            System.out.println("\n\nFEILMELDING\n");
            e.printStackTrace();
        }
    }

// ---------------------------------------------------------------------------------------------------
    
    /**
     * Main method launching the application
     */
    public static void main(String[] args) {
        launch(args);
      }


    /**
     * Stops the application after user confirmation
     */
    public static void exitApplication(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit WarGames");
        alert.setContentText("Are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}
