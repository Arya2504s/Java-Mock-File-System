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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Concatenate;
import commands.Parser;
import exceptions.AlreadyExistsException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;
import filesystem.Directory;
import filesystem.FileSystem;

/**
 * This is a JUnit class that tests Parser
 * 
 * @author Jameson Joseph
 *
 */
public class ParserTest {
  /**
   * Stores the instance of file system being worked on
   */
  FileSystem fileSystem;
  /**
   * Stores the instance of Parser being tested
   */
  Parser parser;
  /**
   * Stores the instance of ByteArrayOutputStream being used
   */
  ByteArrayOutputStream out;
  /**
   * Stores the instance of Concatenate being used
   */
  Concatenate concatenate;

  @Before
  /**
   * Sets up the file system, Parser, out, concatenate, and reassigns the standard output stream
   * 
   * @throws Exception
   */
  public void setUp() throws Exception {
    fileSystem = FileSystem.createFileSystemInstance(new Directory("root", null));
    parser = new Parser();
    out = new ByteArrayOutputStream();
    concatenate = new Concatenate(fileSystem);
    System.setOut(new PrintStream(out));
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
    System.setOut(System.out);
  }

  @Test
  /**
   * Tests whether Parser recognizes invalid commands
   */
  public void testInvalidCommand() {
    try {
      parser.parseUserInput("moo");
      fail("should throw an exception");
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException | InvalidPathException | InvalidArgumentException
        | AlreadyExistsException | IOException e) {
      assertEquals("'" + "moo" + "' is not a valid command\n", e.getMessage());
    }
  }

  @Test
  /**
   * Tests whether Parser recognizes valid commands and valid arguments and adds the command to the
   * history list
   */
  public void testValidCommandValidArguments() {
    try {
      fileSystem.populateList("history");
      fileSystem.populateList("mkdir Dir1 Dir2");
      parser.parseUserInput("history 3");
      assertEquals("[history, mkdir Dir1 Dir2, history 3]", fileSystem.getHistory().toString());
      assertEquals("1.history\n2.mkdir Dir1 Dir2\n3.history 3", out.toString().trim());
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException | InvalidPathException | InvalidArgumentException
        | AlreadyExistsException | IOException e) {
      fail("should not throw an exception");
    }
  }

  @Test
  /**
   * Tests whether Parser recognizes valid commands with invalid arguments and adds the command to
   * the history list
   */
  public void testValidCommandWrongArguments() {
    try {
      parser.parseUserInput("echo hello world");
      fail("should throw an exception");
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException | InvalidPathException | InvalidArgumentException
        | AlreadyExistsException | IOException e) {
      assertEquals("argument must be in double quotes\n", e.getMessage());
      assertEquals("echo hello world", fileSystem.getHistory().get(0));
    }
  }

  @Test
  /**
   * Tests whether Parser disables LoadJShell command after first command isn't LoadJShell
   */
  public void testLoadJShellDisabled() {
    try {
      fileSystem.populateList("history");
      parser.parseUserInput("loadJShell MyDesktop");
      fail("should throw an exception");
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException | InvalidPathException | InvalidArgumentException
        | AlreadyExistsException | IOException e) {
      assertEquals("command disabled", e.getMessage());
    }
  }

  @Test
  /**
   * Tests whether Parser throws exceptions to JShell
   */
  public void testThrowsExceptions() {
    try {
      parser.parseUserInput("history hello");
      fail("should throw an exception");
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException | InvalidPathException | InvalidArgumentException
        | AlreadyExistsException | IOException e) {
      assertEquals("history: " + "hello" + ": numeric argument " + "required\n", e.getMessage());
    }
  }

  @Test
  /**
   * Tests whether Parser is able to recognize redirection
   */
  public void testRecognizesRedirection() {
    try {
      fileSystem.populateList("history");
      fileSystem.populateList("echo \"hey world\"");
      parser.parseUserInput("history > newfile");
      String[] userInput = {"cat", "newfile"};
      assertEquals("[history, echo \"hey world\", history > newfile]",
          fileSystem.getHistory().toString());
      assertEquals("", out.toString().trim());
      assertEquals("1.history\n" + "2.echo \"hey world\"\n" + "3.history > newfile",
          concatenate.runCommand(userInput).trim());
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException | InvalidPathException | InvalidArgumentException
        | AlreadyExistsException | IOException e) {
      fail("should not throw an exception");


    }
  }

}
