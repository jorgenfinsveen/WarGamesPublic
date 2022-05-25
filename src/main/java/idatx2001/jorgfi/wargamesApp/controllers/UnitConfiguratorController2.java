package idatx2001.jorgfi.wargamesApp.controllers;

import idatx2001.jorgfi.wargamesApp.tools.TableEntry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Controller for the unit configurator scene for army 2
 *  
 * @author jorgfi
 */
public class UnitConfiguratorController2 {


// FIELDS -------------------------------------------------------------------------------------------

   // FXML Elements
   @FXML
   private ChoiceBox<String> choiceBox;
   @FXML
   private TextField inputUnitName;
   @FXML
   private TextField inputUnitHealth;
   @FXML
   private TextField inputUnitArmor;
   @FXML
   private TextField inputUnitAttack;
   @FXML
   private TextField inputUnitAmount;
   @FXML
   private TableView army2Table;

   
   // Controller
   private ArmyConfiguratorController armyConfiguratorController;


   // Variables
   private Boolean editingUnit = false;
   private Boolean confirmedEdit = false;


   // Entry to change
   private TableEntry entry;


   // Cloned entry
   private TableEntry clonedEntry;


   // Primarystage
   private Stage primaryStage;


// INITIALIZATION -------------------------------------------------------------------------------------


   /**
    * Sets options for the ChoiceBox and adds listners on the
    * input fields
    */
   public void initialize() {

       // Sets options for the ChoiceBox
       choiceBox.setItems(FXCollections.observableArrayList(
           "Commander", "Cavalry", 
           "Infantry", "Ranger",
           "Giant", "Dark wizard",
           "White wizard" ));
       
       // Makes sure that only numbers can be entered in the textfields
       inputUnitHealth.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> extracted(inputUnitHealth, newValue));
       inputUnitArmor.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> extracted(inputUnitArmor, newValue));
       inputUnitAttack.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> extracted(inputUnitAttack, newValue));
       inputUnitAmount.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> extracted(inputUnitAmount, newValue)); 
   }




// SETTERS ------------------------------------------------------------------------------------------------



    /**
    * Sets the army configurator controller
    * @param armyConfiguratorController controller to be set
    */
    public void setArmyConfiguratorController(ArmyConfiguratorController armyConfiguratorController) {
        this.armyConfiguratorController = armyConfiguratorController;
    }
 

    /**
     * Sets the TableView for army2 in armyConfiguratorPage
     * @param tableView TableView to set
     */
    public void setTableView(TableView tableView) {
        this.army2Table = tableView;
    }
 

    /**
     * Changes value which tells the program if a new unit is being created
     * or if an existing unit is being editet
     * @param bool Boolean bool false if new unit
     */
    public void setEditingUnit(Boolean bool) {
        this.editingUnit = bool;
    }


    /**
     * Sets the entry to change
     * @param entry TableEntry to change
     */
    public void setEntry(TableEntry entry) {
        this.entry = entry;
    }


    /**
     * Clones a TableEntry so that the entry can be restored upon cancelling when editing
     * an existing entry
     * @param entry Entry to clone
     */ 
    public void cloneEntry(TableEntry entry) {
        if (entry.isCustom())  {
            clonedEntry = new TableEntry(entry.getType(), entry.getName(), entry.getAmount(), 
                                         entry.getHealth(), entry.getAttack(), entry.getArmor());
        } else {
            clonedEntry = new TableEntry(entry.getType(), entry.getName(), entry.getAmount(), 
                                         entry.getHealth());
        }
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setOnCloseRequest(event ->{
            ActionEvent ae = new ActionEvent();
            event.consume();   
            returnToArmyConfigurator(ae);
        }); 
    }


// BUTTON METHODS ------------------------------------------------------------------------------------



    /**
    * Saves the unit specified by the user and creates a list of them
    */
    public void confirmUnit(ActionEvent actionEvent) {

        this.confirmedEdit = true;
        
        // Checks that the input is valid
        if (inputUnitHealth.getText() == "" || inputUnitAmount.getText() == "" ||
            inputUnitName.getText() == "" || choiceBox.getValue() == "") {
                missingInputsAlert();
        } else {
            if (!editingUnit) {
                int amount = Integer.parseInt(inputUnitAmount.getText());
                int health = Integer.parseInt(inputUnitHealth.getText());
                String name = inputUnitName.getText();
                String type = choiceBox.getValue();
                if (inputUnitAttack.getText() == "" || inputUnitArmor.getText() == "") {
                    armyConfiguratorController.addUnitsToTable2(type, name, amount, health, 0, 0, false);
                } else {
                    int attack = Integer.parseInt(inputUnitAttack.getText());
                    int armor = Integer.parseInt(inputUnitArmor.getText());
                    armyConfiguratorController.addUnitsToTable2(type, name, amount, health, attack, armor, true);
                }
            } 
            editingUnit = false;
            returnToArmyConfigurator(actionEvent);
        }
    }


    /**
     * Opens an existing TableEntry to display
     * @param entry TableEntry to open
     */
    public void showEntry(TableEntry entry) {

        editingUnit = true;

        cloneEntry(entry);

        inputUnitName.setText(entry.getName() + "");
        inputUnitAmount.setText(entry.getAmount() + "");
        inputUnitHealth.setText(entry.getHealth() + "");
        inputUnitAttack.setText(entry.getAttack() + "");
        inputUnitArmor.setText(entry.getArmor() + "");
        choiceBox.setValue(entry.getType() + "");

        inputUnitName.textProperty().addListener(nameChangeListener);
        inputUnitAmount.textProperty().addListener(amountChangeListener);
        inputUnitHealth.textProperty().addListener(healthChangeListener);
        inputUnitAttack.textProperty().addListener(attackChangeListener);
        inputUnitArmor.textProperty().addListener(armorChangeListener);
        choiceBox.valueProperty().addListener(typeChangeListener);
    }



    /**
    * Sets the army configurator to the current sceme
    */
   public void returnToArmyConfigurator(ActionEvent actionEvent) {


    if (!editingUnit) {
        // Clear textfields
        inputUnitName.clear();
        inputUnitAmount.clear();
        inputUnitHealth.clear();
        inputUnitAttack.clear();
        inputUnitArmor.clear();
        choiceBox.hide();

        this.confirmedEdit = false;
        primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    } else {

        if (!confirmedEdit) {
            choiceBox.setValue(clonedEntry.getType());
            inputUnitName.setText(clonedEntry.getName());
            inputUnitHealth.setText(clonedEntry.getHealth() + "");
            inputUnitAttack.setText(clonedEntry.getAttack() + "");
            inputUnitArmor.setText(clonedEntry.getArmor() + "");
            inputUnitAmount.setText(clonedEntry.getAmount() + "");
            this.confirmedEdit = false;
        }

        // Checks for missing inputs
        if (inputUnitHealth.getText() == "" || inputUnitAmount.getText() == "" ||
            inputUnitName.getText() == "" || choiceBox.getValue() == "") {
            this.confirmedEdit = false;
            missingInputsAlert();
        } else {
            // Removes listners
            inputUnitName.textProperty().removeListener(nameChangeListener);
            inputUnitAmount.textProperty().removeListener(amountChangeListener);
            inputUnitHealth.textProperty().removeListener(healthChangeListener);
            inputUnitAttack.textProperty().removeListener(attackChangeListener);
            inputUnitArmor.textProperty().removeListener(armorChangeListener);
            choiceBox.valueProperty().removeListener(typeChangeListener);



            // Clear textfields
            inputUnitName.clear();
            inputUnitAmount.clear();
            inputUnitHealth.clear();
            inputUnitAttack.clear();
            inputUnitArmor.clear();
            choiceBox.hide();

            this.confirmedEdit = false;
            primaryStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.close();
        }
    }
}



// EXTRACTED LAMBDA METHOD ------------------------------------------------------------------------------------


   /**
    * Prevents the user from typing anything other than an integer.
    * Makes it easier to validate inputs
    * @param field Input field to check
    * @param newValue value entered by the user
    */
   private static void extracted(TextField field, String newValue) {

       // Removes everything except integers
       if (!newValue.matches("\\d*")) {
           field.setText(newValue.replaceAll("[^\\d]", ""));
       }

       // Removing zeroes in front of integers
       if (field.getText().equals("0")) {
           field.setText(newValue.replace("0", ""));
       }
   }




// CHANGELISTENER METHODS ---------------------------------------------------------------------------


    // On changing unit-type
    ChangeListener<String> typeChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            entry.setType(newValue + "");
            army2Table.refresh();
        }
    };

    // On changing unit-name
    ChangeListener<String> nameChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue == "") {
                if (inputUnitName.getText() != "" && inputUnitName.getText() != null) {
                    newValue = inputUnitName.getText();
                } else {
                    newValue = clonedEntry.getName();
                }
            }
            entry.setName(newValue + "");
            army2Table.refresh();
        }
    };

    // On changing unit-amount
    ChangeListener<String> amountChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            entry.setAmount(Integer.parseInt(newValue));
            army2Table.refresh();
        }
    };

    // On changing unit-health
    ChangeListener<String> healthChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            entry.setHealth(Integer.parseInt(newValue));
            army2Table.refresh();
        }
    };

    // On changing unit-attack
    ChangeListener<String> attackChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            entry.setAttack(Integer.parseInt(newValue));
            army2Table.refresh();
        }
    };

    // On changing unit-armor
    ChangeListener<String> armorChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            entry.setArmor(Integer.parseInt(newValue));
            army2Table.refresh();
        }
    };



    

// ALERTS --------------------------------------------------------------------------------------------------


    /**
     * Creates a popup alert that informs the user about missing inputs
     */
    public void missingInputsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("missingInputsAlert");
        alert.setHeaderText("Missing inputs");
        alert.setContentText("The type, name, health and amount must be specified.");
        alert.show();
    }
}
