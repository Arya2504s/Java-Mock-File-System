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
 * This is a JUnit class that tests PopDirectory
 * 
 * @author Arya Sharma
 *
 */
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.PopDirectory;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;
import filesystem.Directory;
import filesystem.FileSystem;
import filesystem.StackNode;

public class PopDirectoryTest {
  private FileSystem fileSystem;
  private Directory root;
  private PopDirectory popd;

  @Before
  /**
   * Sets up file system and PushDirectory
   * 
   * @throws Exception
   */
  public void setUp() throws Exception {
    root = new Directory("root", null);
    fileSystem = FileSystem.createFileSystemInstance(root);
    popd = new PopDirectory(fileSystem);
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
   * This part tests the method runCommand when stack is empty
   * 
   * @throws InvalidArgumentException Stack is empty, Cannot perform this command.
   */
  @Test
  public void testEmptyStack() throws InvalidArgumentException, InvalidPathException {
    String userInput[] = {"popd"};
    try {
      popd.runCommand(userInput);
      fail("should throw an exception");
    } catch (InvalidArgumentException e) {
      assertEquals("Stack is empty, Cannot perform this command.", e.getMessage());
    }
  }

  /**
   * This part tests the method runCommand when there are too many arguments
   * 
   * @throws InvalidArgumentException Argument missing, no directory name
   */
  @Test
  public void testTooManyArgs() throws InvalidArgumentException, InvalidPathException {
    String userInput[] = {"popd", "d"};
    try {
      popd.runCommand(userInput);
      fail("should throw an exception");
    } catch (InvalidArgumentException e) {
      assertEquals("Invalid input. Too many arguments.", e.getMessage());
    }
  }

  /**
   * This part tests the method runCommand when inputs are valid
   */
  @Test
  public void testRunCommand1() throws InvalidArgumentException, InvalidPathException {
    String userInput[] = {"popd"};
    Directory newDir = new Directory("newDir", root);
    StackNode Top = new StackNode(newDir, null);
    fileSystem.setTop(Top);
    fileSystem.setCurrentDirectory(root);
    try {
      popd.runCommand(userInput);
      assertEquals("newDir", fileSystem.getCurrentDirectory().getDirectoryName());
    } catch (InvalidArgumentException e) {
      fail("should not throw an exception");
      e.getMessage();
    }
  }

  /**
   * This part tests the method runCommand when inputs are valid, and if it gives error when parent
   * Directory of Directory in stack is removed
   */
  @Test
  public void testRunCommand2() throws InvalidArgumentException, InvalidPathException {
    String userInput[] = {"popd"};
    Directory newDir = new Directory("newDir", root);
    Directory parentDir = newDir.getParentDirectory();
    Directory newDir2 = new Directory("newDir2", newDir);
    StackNode Top = new StackNode(newDir2, null);
    fileSystem.setTop(Top);
    fileSystem.setCurrentDirectory(root);
    ArrayList<Directory> dirList = parentDir.getChildDirectories();
    dirList.remove(newDir);
    parentDir.setChildDirectories(dirList);
    try {
      popd.runCommand(userInput);
      fail("should throw an exception");
    } catch (InvalidPathException e) {
      assertEquals("Directory doesnot exists", e.getMessage());
    }
  }

}
