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
 * This is a JUnit class that tests StackNode
 * 
 * @author Arya Sharma
 *
 */
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidArgumentException;
import filesystem.Directory;
import filesystem.FileSystem;
import filesystem.StackNode;

public class StackNodeTest {

  /**
   * Stores the instance of Echo being tested
   */
  private Directory root;
  private FileSystem fileSystem;

  @Before
  /**
   * Sets up file system and Echo
   * 
   * @throws Exception
   */
  public void setUp() throws Exception {
    root = new Directory("root", null);
    fileSystem = FileSystem.createFileSystemInstance(root);
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
   * This method tests the the method getDir of StackNode
   */
  @Test
  public void testGetDir() throws InvalidArgumentException {

    Directory newDir = new Directory("newDir", root);
    StackNode sn = new StackNode(newDir, null);
    assertEquals("newDir", sn.getDir().getDirectoryName());
    assertEquals(null, sn.getNext());
  }

  /**
   * This method tests the the method getNext of StackNode
   */
  @Test
  public void testGetNext() throws InvalidArgumentException {
    Directory newDir = new Directory("newDir", root);
    Directory newDir2 = new Directory("newDir2", root);
    StackNode next = new StackNode(newDir2, null);
    StackNode sn = new StackNode(newDir, next);
    assertEquals("newDir", sn.getDir().getDirectoryName());
    assertEquals("newDir2", sn.getNext().getDir().getDirectoryName());
  }


}
