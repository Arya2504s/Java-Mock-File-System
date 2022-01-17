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
// Author:
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
/**
 * This is a JUnit class that tests Exit
 * 
 * @author Jerry Dang
 *
 */
import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.After;

import exceptions.InvalidArgumentException;
import commands.Exit;
import filesystem.Directory;
import filesystem.FileSystem;

public class ExitTest {
  /**
   * Stores the instance of the file system being worked on
   */
  private FileSystem fileSystem;
  private Directory root;
  /**
   * Stores the instance of Manual being tested
   */
  private Exit ex;

  @Before
  /**
   * Sets up file system and Exit
   * 
   * @throws Exception
   */
  public void setUp() throws Exception {
    root = new Directory("root", null);
    fileSystem = FileSystem.createFileSystemInstance(root);
    ex = new Exit(fileSystem);
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

  @Test
  /**
   * Tests Exit with arguments
   */
  public void testArguments() {
    String[] commandInputArray = {"exit", "ls"};
    try {
      ex.runCommand(commandInputArray);
      fail("should throw an exception");
    } catch (InvalidArgumentException e) {
      assertEquals("There are too many " + "arguments\n", e.getMessage());
    }
  }



}
