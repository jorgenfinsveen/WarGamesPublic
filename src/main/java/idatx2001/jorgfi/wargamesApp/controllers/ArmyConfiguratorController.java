package idatx2001.jorgfi.wargamesApp.controllers;

import idatx2001.jorgfi.wargamesApp.other.FileHandler;
import idatx2001.jorgfi.wargamesApp.tools.Table;
import idatx2001.jorgfi.wargamesApp.tools.TableEntry;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Controller for the army configurator scene
 * 
 * @author jorgfi
 */
public class ArmyConfiguratorController {


// FIELDS --------------------------------------------------------------------------------------



  // FilHandler
  private FileHandler fileHandler = new FileHandler();


  // Neighbor scenes
  private Scene unitConficuratorScene1;
  private Scene unitConficuratorScene2;
  private Scene terrainConfiguratorScene;


  // Unit configurator controllers
  private UnitConfiguratorController1 unitConfiguratorController1;
  private UnitConfiguratorController2 unitConfiguratorController2;
  private SimulatorController simulatorController;


  // Army name inputs
  @FXML
  private MFXTextField inputArmy1Name;
  @FXML
  private MFXTextField inputArmy2Name;


  // FXML TableView elements
  @FXML
  private TableView army1Table;
  @FXML
  private TableView army2Table;


  // Entrylists for army1 and army2
  private ArrayList<TableEntry> entryList1 = new ArrayList<>();
  private ArrayList<TableEntry> entryList2 = new ArrayList<>();

  // Tables for army1 and army2
  private Table table1 = new Table();
  private Table table2 = new Table();
  
  


// INITIALIZING ---------------------------------------------------------------------------------  


  /**
   * Initializing the controller by creating columns for each TableView.
   * Sorts data to be stored in the table by type, name and amount
   */
  @SuppressWarnings("unchecked")
  public void initialize() {

    // Creating columns for each TableView
    TableColumn<TableEntry, String> typeColumn1 = new TableColumn<>("Type");
    TableColumn<TableEntry, String> nameColumn1 = new TableColumn<>("Name");
    TableColumn<TableEntry, String> amountColumn1 = new TableColumn<>("Amount");
    TableColumn<TableEntry, String> typeColumn2 = new TableColumn<>("Type");
    TableColumn<TableEntry, String> nameColumn2 = new TableColumn<>("Name");
    TableColumn<TableEntry, String> amountColumn2 = new TableColumn<>("Amount");

    // Categorizes what kind of data to be stored in each column
    typeColumn1.setCellValueFactory(new PropertyValueFactory<>("type"));
    nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
    amountColumn1.setCellValueFactory(new PropertyValueFactory<>("amount"));
    typeColumn2.setCellValueFactory(new PropertyValueFactory<>("type"));
    nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
    amountColumn2.setCellValueFactory(new PropertyValueFactory<>("amount"));

    // Adds the columns to the TableViews
    army1Table.getColumns().add(typeColumn1);
    army1Table.getColumns().add(nameColumn1);
    army1Table.getColumns().add(amountColumn1);
    army2Table.getColumns().add(typeColumn2);
    army2Table.getColumns().add(nameColumn2);
    army2Table.getColumns().add(amountColumn2);

    File file1 = new File("TestArmy1.csv");
    File file2 = new File("TestArmy2.csv");
    armyFromFile1(file1);
    armyFromFile2(file2);
  }
  




// SETTERS --------------------------------------------------------------------------------------------------------------------------------



  /**
   * Sets the unit configurator scenes for each army
   * @param scene1 scene for army1 
   * @param scene2 scene for army2
   */
  public void setUnitConficuratorScene(Scene scene1, Scene scene2) {
    this.unitConficuratorScene1 = scene1;
    this.unitConficuratorScene2 = scene2;
  }

  
  /** 
   * Sets the terrain configurator scene
   * @param scene scene to be set
   */
  public void setTerrainConfiguratorScene(Scene scene) {
    this.terrainConfiguratorScene = scene;
  }
  

  /**
   * Sets the unit configurator controllers
   * @param u1 UnitConfiguratorController1 controller 1
   * @param u2 UnitConfiguratorController2 controller 2
   */
  public void setUnitConfiguratorController(UnitConfiguratorController1 u1, 
                                            UnitConfiguratorController2 u2) {
    this.unitConfiguratorController1 = u1;
    this.unitConfiguratorController2 = u2;
  }


  /**
   * Sets the simulator controller
   * @param s SimulatorController to set
   */
  public void setSimulatorController(SimulatorController s) {
    this.simulatorController = s;
  }





// TABLE OPERATIONS ---------------------------------------------------------------------------------------
  


  /** 
   * Ads a pack of units to the army1 table
   * @param type String type of unit
   * @param name String name of unit
   * @param amount int amount of units
   * @param health int health of units
   * @param attack int attack of units
   * @param armor int armor value
   * @param isCustom boolean custom or default unit
   */
  @SuppressWarnings("unchecked")
  public void addUnitsToTable1(String type, String name, int amount, int health,
                               int attack, int armor, boolean isCustom) {
    
    if (isCustom) {
      TableEntry entry = new TableEntry(type, name, amount, health, attack, armor);
      army1Table.getItems().add(entry);
      entryList1.add(entry);
      table1.addEntry(entry);
    } else {
      TableEntry entry = new TableEntry(type, name, amount, health);
      army1Table.getItems().add(entry);
      entryList1.add(entry);
      table1.addEntry(entry);
    }
  }

  

  /** 
   * Ads a pack of units to the army2 table
   * @param type String type of unit
   * @param name String name of unit
   * @param amount int amount of units
   * @param health int health of units
   * @param attack int attack of units
   * @param armor int armor value
   * @param isCustom boolean custom or default unit
   */
  @SuppressWarnings("unchecked")
  public void addUnitsToTable2(String type, String name, int amount, int health,
                               int attack, int armor, boolean isCustom) {
    
    
    if (isCustom) {
      TableEntry entry = new TableEntry(type, name, amount, health, attack, armor);
      army2Table.getItems().add(entry);
      entryList2.add(entry);
      table2.addEntry(entry);
    } else {
      TableEntry entry = new TableEntry(type, name, amount, health);
      army2Table.getItems().add(entry);
      entryList2.add(entry);
      table2.addEntry(entry);
    }
  }



  /**
   * Removes selected unit-pack form the army1 table.
   */
  public void removeUnitsFromTable1() {

    ObservableList<TableEntry> selectedRows;
    ObservableList<TableEntry> allEntries;
    allEntries = army1Table.getItems();
    selectedRows = army1Table.getSelectionModel().getSelectedItems();

    for (TableEntry entry : selectedRows) {
      allEntries.remove(entry);
      table1.removeEntry(entry);
    }
  }

  /**
   * Removes selected unit-pack form the army2 table.
   */
  public void removeUnitsFromTable2() {

    ObservableList<TableEntry> selectedRows;
    ObservableList<TableEntry> allEntries;
    allEntries = army2Table.getItems();
    selectedRows = army2Table.getSelectionModel().getSelectedItems();

    for (TableEntry entry : selectedRows) {
      allEntries.remove(entry);
      table2.removeEntry(entry);
    }
  }
  




// UNIT OPERATIONS --------------------------------------------------------------------------------


  /**
   * Opens unit configurator 1 on a specified unit, allowing the user to edit it
   */
  @FXML
  public void editUnit1(ActionEvent actionEvent) {
    unitConfiguratorController1.setEditingUnit(true);
    unitConfiguratorController1.setTableView(army1Table);
    ObservableList<TableEntry> selectedRows;

    selectedRows = army1Table.getSelectionModel().getSelectedItems();

    showUnitConfig1(actionEvent);
    for (TableEntry entry : selectedRows) {
      unitConfiguratorController1.showEntry(entry);
      unitConfiguratorController1.setEntry(entry);
    }
    army1Table.refresh();
  }


  /**
   * Opens unit configurator 2 on a specified unit, allowing the user to edit it
   */
  @FXML
  public void editUnit2(ActionEvent actionEvent) {
    unitConfiguratorController2.setEditingUnit(true);
    unitConfiguratorController2.setTableView(army2Table);
    ObservableList<TableEntry> selectedRows;

    selectedRows = army2Table.getSelectionModel().getSelectedItems();

    showUnitConfig2(actionEvent);
    for (TableEntry entry : selectedRows) {
      unitConfiguratorController2.showEntry(entry);
      unitConfiguratorController2.setEntry(entry);
    }
    army2Table.refresh();
  }




  
  /**
   * Activates when the user presses the Add unit button for army1
   */
  public void addUnitButtonPressed1(ActionEvent actionEvent) {
    unitConfiguratorController1.setEditingUnit(false);
    showUnitConfig1(actionEvent);
  }


  /**
   * Activates when the user presses the Add unit button for army2
   */
  public void addUnitButtonPressed2(ActionEvent actionEvent) {
    unitConfiguratorController2.setEditingUnit(false);
    showUnitConfig2(actionEvent);
  }




// SCENE DISPLAYERS ----------------------------------------------------------------------------

  

  /** 
   * Closes army configurator scene and opens the terrain configurator
   */
  public void showTerrainConfigurator(ActionEvent actionEvent) {

    // Checks that both armies are named and contains units
    if (table1.getTable().size() == 0 || table2.getTable().size() == 0) {
      missingUnitsAlert();
    } else if (inputArmy1Name.getText() == "" || inputArmy2Name.getText() == "" ) {
      missingArmyNamesAlert();
    } else {

      simulatorController.setTables(table1, table2);
      simulatorController.setArmyNames(inputArmy1Name.getText(), inputArmy2Name.getText());

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
  }
  



  /** 
   * Shows the unit configurator for army1
   */
  public void showUnitConfig1(ActionEvent actionEvent) {
    
    
    Stage stage1 = new Stage();
    Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
    
    stage1.setScene(this.unitConficuratorScene1);
    stage1.centerOnScreen();
    stage1.setResizable(false);
    stage1.initModality(Modality.APPLICATION_MODAL);
    stage1.setMinHeight(400);
    stage1.setMinWidth(300);
    stage1.setResizable(false);
    stage1.setFullScreen(false);
    
    stage1.show();
  }

  
  /** 
   * Show the unit configurator for army2
   */
  public void showUnitConfig2(ActionEvent actionEvent) {
    Stage stage2 = new Stage();
    Stage primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
    
    stage2.setScene(this.unitConficuratorScene2);
    stage2.centerOnScreen();
    stage2.setResizable(false);
    stage2.initModality(Modality.APPLICATION_MODAL);
    stage2.setMinHeight(400);
    stage2.setMinWidth(300);
    stage2.setResizable(false);
    stage2.setFullScreen(false);
    
    
    stage2.show();
  }






// FILE HANDLING ------------------------------------------------------------------------------------


  /**
   * Fills army1 table with an army from a csv file
   * @param file File to read
   */
  public void armyFromFile1(File file) {
    String fileName = file.getName().toString();
    String[] fileNameSplit = fileName.split(".csv");
    inputArmy1Name.setText(fileNameSplit[0]);

    Table table = fileHandler.getTableFromFileCSV(file.toPath());

    for (TableEntry entry : table.getTable()) {
      if (entry.isCustom()) {
        addUnitsToTable1(entry.getType(), entry.getName(), entry.getAmount(), 
                         entry.getHealth(), entry.getAttack(), entry.getArmor(), true);
      } else if (!entry.isCustom()) {
        addUnitsToTable1(entry.getType(), entry.getName(), entry.getAmount(), 
                         entry.getHealth(), entry.getAttack(), entry.getArmor(), false);
      }

    }
  }


  /**
   * Fills army2 table with an army from a csv file
   * @param file File to read
   */
  public void armyFromFile2(File file) {
    String fileName = file.getName().toString();
    String[] fileNameSplit = fileName.split(".csv");
    inputArmy2Name.setText(fileNameSplit[0]);

    Table table = fileHandler.getTableFromFileCSV(file.toPath());

    for (TableEntry entry : table.getTable()) {
      if (entry.isCustom()) {
        addUnitsToTable2(entry.getType(), entry.getName(), entry.getAmount(), 
                         entry.getHealth(), entry.getAttack(), entry.getArmor(), true);
      } else if (!entry.isCustom()) {
        addUnitsToTable2(entry.getType(), entry.getName(), entry.getAmount(), 
                         entry.getHealth(), entry.getAttack(), entry.getArmor(), false);
      }
    }
  }
  


  /**
   * Activates when the user presses the Army from file 1 button,
   * and checks that the file is not empty
   */
  @FXML
  public void addArmy1Btn(){
      File file = FileHandler.getFile();
      if(file == null){}
        else{
          armyFromFile1(file);
      }
  }

  /**
   * Activates when the user presses the Army from file 2 button,
   * and checks that the file is not empty
   */
  @FXML
  public void addArmy2Btn(){
      File file = FileHandler.getFile();
      if(file == null){}
        else{
          armyFromFile2(file);
      }
  }


  /**
   * Saves all units in table 1 to a csv file
   */
  @FXML
  public void saveArmyToFile1() {
    FileHandler.saveArmyToFile(table1);
  }

  
  /**
   * Saves all units in table 2 to a csv file
   */
  @FXML
  public void saveArmyToFile2() {
    FileHandler.saveArmyToFile(table2);
  }





  // ALERTS --------------------------------------------------------------------------------------------------


    /**
     * Creates a popup alert that informs the user about missing army names
     */
    public void missingArmyNamesAlert() {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("missingArmyNamesAlert");
      alert.setHeaderText("Missing armynames");
      alert.setContentText("You need to name the armies");
      alert.show();
    }


    /**
     * Creates a popup alert that informs the user about missing inputs
     */
    public void missingUnitsAlert() {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("missingUnitsAlert");
      alert.setHeaderText("No warriors");
      alert.setContentText("There must be at least one unit per army.");
      alert.show();
  }
}

