package idatx2001.jorgfi.wargamesApp.other;

import idatx2001.jorgfi.wargamesApp.tools.Table;
import idatx2001.jorgfi.wargamesApp.tools.TableEntry;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javafx.stage.FileChooser;


// TODO: Får feilmelding om at Table ikke har TableEntries



/**
 * Class responsible for writing and reading to/from .csv files.
 * 
 * @author jorgfi
 */
public class FileHandler {
  
  /**
   * Method that returns a table from a .csv file.
   * @return Table table containing the table entries
   */
  public static Table getTableFromFileCSV(Path path) throws IllegalArgumentException{

    Table table = new Table();
    ArrayList<String> lines = new ArrayList<>();

    try(BufferedReader reader = Files.newBufferedReader(path)) {
        String line;
        while((line = reader.readLine())!= null){
            lines.add(line);
        }
    } catch (IOException e) {
        /* File does not exist. */
        throw new IllegalArgumentException("File not found");
    }
    if(lines.isEmpty()){throw new IllegalArgumentException("No content in file");}


    for(int i = 0; i< lines.size();i++){

      String [] words = lines.get(i).split(",");

      // Not custom units
      if (words.length == 4) {
        String type = words[0];
        String name = words[1];
        int amount = Integer.parseInt(words[2]);
        int health = Integer.parseInt(words[3]);
        table.addEntry(new TableEntry(type, name, amount, health));
      }

      // Custom units
      else if (words.length == 6) {
        String type = words[0];
        String name = words[1];
        int amount = Integer.parseInt(words[2]);
        int health = Integer.parseInt(words[3]);
        int attack = Integer.parseInt(words[4]);
        int armor = Integer.parseInt(words[5]);
        table.addEntry(new TableEntry(type, name, amount, health, attack, armor));
      }
    }
    
    return table;
}

/**
 * Creates a file containing all TableEntries from the table
 */

public static Path saveArmyToFile(Table table)throws IllegalArgumentException{
    
  // Checks that the table contains TableEntries
  if(table.getTable().size() == 0) {throw new IllegalArgumentException("The table must have at least one TableEntry");}
  System.out.println(table.getTable().size());

  // Open a window for choosing file to read
  FileChooser chooser = new FileChooser();
  
  // Filtrates files to only be of type .csv
  chooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSV Files","*.csv")
  ); 
  
  // Creates a new file to write
  File file = chooser.showSaveDialog(null); 

  // Writes to file
  try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {

    // For each TableEntry in the Table
    for (TableEntry entry : table.getTable()) {

      // If the TableEntry contains custom units
      if (entry.isCustom()) {
        writer.write(entry.getType() + "," + entry.getName() + "," + 
                      entry.getAmount() + "," + entry.getHealth() + "," +
                      entry.getAttack() + "," + entry.getArmor() + "\n");
      }
      // If the TableEntry contains default units
      else if (!entry.isCustom()) {
        writer.write(entry.getType() + "," + entry.getName() + "," + 
                      entry.getAmount() + "," + entry.getHealth() + "\n");
      }
    }
  } catch (IOException e) {throw new IllegalArgumentException("Could not write no file");}

  return file.toPath().toAbsolutePath();
}


/**
 * Opens a window to open the file.
 * Prompts the user to select a cvs file.
 * (Only "csv" files)
 *
 * @return returns a file object from the file-chooser window.
 */
public static File getFile(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CSV Files","*.csv")
    );
    return fileChooser.showOpenDialog(null);
  }
}
