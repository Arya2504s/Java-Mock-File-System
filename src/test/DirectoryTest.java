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
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exceptions.InvalidArgumentException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;

/**
 * This is a JUnit class that tests Directory
 * 
 * @author Jameson Joseph
 *
 */
public class DirectoryTest {
  /**
   * Stores the instance of the fileSystem being worked on
   */
  FileSystem fileSystem;
  /**
   * Stores the root of the file system
   */
  Directory root;

  @Before
  /**
   * Sets up the file system, Parser, out, concatenate, and reassigns the standard output stream
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
    field.set(null, null);
  }

  @SuppressWarnings("unused")
  @Test
  /**
   * Tests whether a directory is successfully created
   */
  public void testCreateDirectory() {
    try {
      Directory dir1 = new Directory("dir1", root);
      File file1 = new File("file1", dir1);
      assertEquals("dir1",
          fileSystem.getRootDirectory().getChildDirectories().get(0).getDirectoryName());
      assertEquals(0, dir1.getChildDirectories().size());
      assertEquals(1, dir1.getChildFiles().size());
      assertEquals("root", dir1.getParentDirectory().getDirectoryName());
      assertEquals("/dir1", dir1.getAbsolutePath());
    } catch (InvalidArgumentException e) {
      fail("should not throw an exception");
    }
  }

  @Test
  /**
   * Tests whether we are able to use functions in Directory to modify a directory's child files/
   * directories
   */
  public void testModifyDirectory() {
    Directory dir1;
    try {
      dir1 = new Directory("dir1", root);
      assertEquals(0, dir1.getChildDirectories().size());
      assertEquals(0, dir1.getChildFiles().size());
      dir1.addChildDirectory(new Directory("dir2", dir1));
      dir1.addChildFile(new File("file1", dir1));
      assertEquals(1, dir1.getChildDirectories().size());
      assertEquals(1, dir1.getChildFiles().size());
      ArrayList<Directory> newDirList = new ArrayList<Directory>();
      newDirList.add(new Directory("dir3", dir1));
      newDirList.add(new Directory("newdir", dir1));
      dir1.setChildDirectories(newDirList);
      assertEquals(2, dir1.getChildDirectories().size());
    } catch (InvalidArgumentException e) {
      fail("should not throw an exception");
    }
  }

  @SuppressWarnings("unused")
  @Test
  /**
   * Tests whether we are able to use special characters in a directory name
   */
  public void testSpecialCharactersName() {
    try {
      Directory dir1 = new Directory("di$r1", root);
      fail("should throw an exception");
    } catch (InvalidArgumentException e) {
      assertEquals("invalid characters for the " + "directory name", e.getMessage());
    }
  }
}
