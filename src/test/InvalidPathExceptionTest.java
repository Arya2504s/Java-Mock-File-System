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
import org.junit.Test;
import exceptions.InvalidPathException;

/**
 * This is a JUnit class that tests InvalidPathException
 * 
 * @author Jameson Joseph
 *
 */
public class InvalidPathExceptionTest {

  @Test
  /**
   * This method tests whether InvalidPathException throws the appropriate custom exception
   */
  public void testExceptionThrown() {
    try {
      throw new InvalidPathException("this is an invalid path");
    } catch (InvalidPathException e) {
      assertEquals("this is an invalid path", e.getMessage());
    }
  }

}
