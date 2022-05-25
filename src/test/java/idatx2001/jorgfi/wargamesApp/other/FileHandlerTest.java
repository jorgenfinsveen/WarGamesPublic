package idatx2001.jorgfi.wargamesApp.other;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import idatx2001.jorgfi.wargamesApp.tools.Table;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;


/**
 * Tests the FileHandler class
 * 
 * @author jorgfi
 */
public class FileHandlerTest {
    

    /**
     * Tests that the FileHandler can open a correctly configured file
     */
    @Test
    public void testCorrectFileGetsRead(){
        //Testing Negative, therefore no arrange or act needed.
        try{
            Table table = FileHandler.getTableFromFileCSV(
                    Path.of("src/test/java/idatx2001/jorgfi/wargamesApp/other/testfiles/correctFile.csv")
            );

            //Assert
            assertEquals(1,table.getTable().size());
        }catch (Exception ex){
            fail();
        }
    }


    /**
     * Tests that the FileHandler throws an exception when trying to open an empty file
     */
    @Test
    public void testEmptyFileGetsRead() {
        try{  
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {Table table = FileHandler.getTableFromFileCSV(
                Path.of("src/test/java/idatx2001/jorgfi/wargamesApp/other/testfiles/emptyFile.csv")
        );});
        }catch (Exception ex){
            fail();
        }
    }


    /**
     * Tests that the FileHandler throws IllegalArgumentException when trying to open a file
     * that does not exist
     */
    @Test
    public void noneExistingFileGetsRead() {
        try{  
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {Table table = FileHandler.getTableFromFileCSV(
                Path.of("src/test/java/idatx2001/jorgfi/wargamesApp/other/testfiles/NOFILE.csv")
        );});
        }catch (Exception ex){
            fail();
        }
    }
}
