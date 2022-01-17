package test;

// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: josep236
// UT Student #: 1006430845
// Author: Jameson Joseph
//
// Student2:Arya Sharma
// UTORID user_name:shar1497
// UT Student #:1005692591
// Author:Arya Sharma
//
// Student3: Yuto Omachi
// UTORID user_name: omachiyu
// UT Student #: 1006005163
// Author: Yuto Omachi
//
// Student4: Jia Rong (Jerry) Dang
// UTORID user_name: dangjia
// UT Student #: 1005838685
// Author: Jerry Dang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.SaveJShell;
import exceptions.InvalidArgumentException;
import filesystem.Directory;
import filesystem.FileSystem;

/**
 * This is a JUnit class that tests SaveJShell
 * 
 * @author Jameson Joseph
 *
 */
public class SaveJShellTest {
  /**
   * Stores the instance of file system being worked on
   */
  FileSystem fileSystem;
  /**
   * Stores the instance of SaveJShell being tested
   */
  SaveJShell save;

  @Before
  /**
   * Sets up the file system and the SaveJShell instance
   * 
   * @throws Exception
   */
  public void setUp() throws Exception {
    fileSystem = FileSystem.createFileSystemInstance(new Directory("root", null));
    save = new SaveJShell(fileSystem);
  }

  @After
  /**
   * Resets the reference parameter of file system to null, allowing for new instance each test
   * 
   * @throws Exception
   */
  public void tearDown() throws Exception {
    Field field = (fileSystem.getClass()).getDeclaredField("fileSystemReference");
    field.setAccessible(true);
    field.set(null, null);
  }

  @Test
  /**
   * Tests whether SaveJShell is able to recognize invalid file names
   */
  public void testInvalidFileName() {
    String[] userInput = {"saveJShell", "C:\\Users\\User\\newfile"};
    try {
      save.runCommand(userInput);
      fail("should throw an exception");
    } catch (IOException | InvalidArgumentException e) {
      assertEquals("saveJShell: file name can only contain characters from "
          + "A-Z, a-z, or 1-9, slashes are not allowed either", e.getMessage());
    }
  }

  @Test
  /**
   * Tests whether SaveJShell is able to recognize when user inputs wrong number of commands
   */
  public void testWrongNumberOfArguments() {
    String[] userInput = {"saveJShell"};
    try {
      save.runCommand(userInput);
      fail("should throw an exception");
    } catch (IOException | InvalidArgumentException e) {
      assertEquals("saveJShell: requires one argument that is a file name", e.getMessage());
    }
    String[] secondInput = {"saveJShell", "newfile", "secondnewfile"};

    try {
      save.runCommand(secondInput);
      fail("should throw an exception");
    } catch (IOException | InvalidArgumentException e) {
      // TODO Auto-generated catch block
      assertEquals("saveJShell: requires one argument that is a file name", e.getMessage());
    }
  }

  @Test
  /**
   * Tests whether SaveJShell is able to create a file in the savedfiles directory
   */
  public void testFileCreated() {
    String[] userInput = {"saveJShell", "newfile"};
    try {
      save.runCommand(userInput);
      File file = new File("src/savedfiles", "newfile");
      assertEquals(true, file.exists());
    } catch (IOException | InvalidArgumentException e) {
      fail("should not throw an exception");
    }
  }


}
