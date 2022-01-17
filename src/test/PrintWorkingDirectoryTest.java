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
import commands.PrintWorkingDirectory;
import exceptions.InvalidArgumentException;
import filesystem.Directory;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit class that tests PrintWorkingDirectory
 * 
 * @author Yuto Omachi
 */

public class PrintWorkingDirectoryTest {
  private FileSystem fileSystem;
  private Directory root;
  private PrintWorkingDirectory pwd;

  @Before
  public void setUp() throws Exception {
    root = new Directory("root", null);
    fileSystem = FileSystem.createFileSystemInstance(root);
    pwd = new PrintWorkingDirectory(fileSystem);
  }

  @After
  public void tearDown() throws Exception {
    Field field = (fileSystem.getClass()).getDeclaredField("fileSystemReference");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @SuppressWarnings("unused")
  @Test
  /**
   * Tests whether PrintWorkingDirectory can throw appropriate exception with too many arguments
   */
  public void testTooManyArgument() {
    String userInput[] = {"pwd", "/"};
    try {
      String outPut = pwd.runCommand(userInput);
      fail("should throw an exception");
    } catch (InvalidArgumentException e) {
      assertEquals("pwd: too many arguments", e.getMessage());
    }
  }

  @Test
  /**
   * Tests whether PrintWorkingDirectory can print valid path with only root
   */
  public void testRootPWD() {
    String userInput[] = {"pwd"};
    try {
      String outPut = pwd.runCommand(userInput);
      assertEquals("/", outPut);
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
      fail("Should not throw any exception");
    }
  }

  @Test
  /**
   * Tests whether PrintWorkingDirectory can print valid path when the current working directory is
   * in nested directory
   */
  public void testComplexPWD() {
    String userInput[] = {"pwd"};
    try {
      Directory dir1 = new Directory("dir1", root);
      Directory dir2 = new Directory("dir2", dir1);
      fileSystem.setCurrentDirectory(dir2);
    } catch (InvalidArgumentException e) {
      // When something went wrong with creating directories
      fail("Error setting up directories");
    }
    try {
      String outPut = pwd.runCommand(userInput);
      assertEquals("/dir1/dir2", outPut);
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
      fail("Should not throw any exception");
    }
  }
}
