package test;

// **********************************************************
// Assignment2:
// Student1:Arya Sharma
// UTORID user_name:shar1497
// UT Student #:1005692591
// Author:Arya Sharma
//
// Student2: Yuto Omachi
// UTORID user_name: omachiyu
// UT Student #: 1006005163
// Author: Yuto Omachi
//
// Student3:
// UTORID user_name: josep236
// UT Student #: 1006430845
// Author: Jameson Joseph
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
/**
 * This is a JUnit class that tests Concatenate
 * 
 * @author Arya Sharma
 *
 */
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Concatenate;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;

public class ConcatenateTest {
  /**
   * Stores the instance of the file system being worked on
   */
  private FileSystem fileSystem;
  private Directory root;
  /**
   * Stores the instance of Concatenate being tested
   */
  private Concatenate cat;

  @Before
  /**
   * Sets up file system and Concatenate
   * 
   * @throws Exception
   */
  public void setUp() throws Exception {
    root = new Directory("root", null);
    fileSystem = FileSystem.createFileSystemInstance(root);
    cat = new Concatenate(fileSystem);
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
    field.set(null, null); // setting the ref parameter to null
  }

  /**
   * This part tests the method runCommand when arguments are missing
   * 
   * @throws InvalidArgumentException Missing Arguments, no file names
   */
  @Test
  public void testNotEnoughArgs() throws InvalidArgumentException, InvalidPathException {
    String userInput[] = {"cat"};
    try {
      cat.runCommand(userInput);
      fail("should throw an exception");
    } catch (InvalidArgumentException e) {
      assertEquals("Missing Arguments, no file names", e.getMessage());
    }
  }

  /**
   * This part tests the method runCommand when the file path is invalid
   * 
   * @throws InvalidPathException
   */
  public void testInvalidPath() throws InvalidArgumentException, InvalidPathException {
    String userInput[] = {"cat", "newfile"};
    try {
      cat.runCommand(userInput);
      fail("should throw an exception");
    } catch (InvalidPathException e) {
      assertEquals("File:newfile doesnot exist", e.getMessage());
    }
  }

  /**
   * This part tests the method runCommand of when only 1 file is passed as argument or when 2 files
   * are passed but only 1 file exists
   * 
   * @throws InvalidPathException If file does not exist
   */
  @Test
  public void testSingleFile() throws InvalidArgumentException, InvalidPathException {
    String userInput1[] = {"cat", "newfile"};

    File newfile = new File("newfile", root);
    newfile.storeFileData("ABCD");

    try {
      assertEquals("ABCD", cat.runCommand(userInput1));
    } catch (InvalidPathException e) {
      fail("should not throw an exception");
      e.getStackTrace();
    }

    String userInput2[] = {"cat", "newfile", "file1"};
    try {
      cat.runCommand(userInput2);
      fail("should throw an exception");
    } catch (InvalidPathException e) {
      assertEquals("ABCD&\nFile:file1 doesnot exist&", e.getMessage());
    }
  }

  /**
   * This part tests the method runCommand when multiple files are passed as arguments
   */
  @Test
  public void testMultipleFile() throws InvalidArgumentException, InvalidPathException {
    File newfile = new File("newfile", root);
    newfile.storeFileData("ABCD");
    File file1 = new File("file1", root);
    file1.storeFileData("DEF");
    String userInput[] = {"cat", "newfile", "file1"};
    try {
      assertEquals("ABCD\n\n\nDEF", cat.runCommand(userInput));
    } catch (InvalidPathException e) {
      fail("should not throw an exception");
      e.getStackTrace();
    }
  }

}
