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

import exceptions.InvalidArgumentException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import driver.JShell;

/**
 * This is a JUnit class that tests JShell
 * 
 * @author Jameson Joseph
 *
 */
public class JShellTest {
  /**
   * Stores the instance of ByteArrayOutputStream being used
   */
  ByteArrayOutputStream out;
  /**
   * Stores the instance of ByteArrayInputStream being used
   */
  ByteArrayInputStream in;
  /**
   * Stores the instance of JShell being tested
   */
  JShell shell;

  @Before
  /**
   * Sets up new input stream and output stream
   */
  public void setUp() {
    in = new ByteArrayInputStream("histor".getBytes());
    out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    System.setIn(in);
  }

  @After
  /**
   * Resets input and output stream back to StandardOutput and StandardInput
   */
  public void tearDown() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  /**
   * Tests whether the user is being prompted for input
   */
  public void testUserIsBeingPrompted() {
    String[] args = {};
    try {
      JShell.main(args);
    } catch (NoSuchElementException | IOException | InvalidArgumentException e) {
      assertEquals("'histor' is not a valid command", out.toString().trim());
    }
  }

}
