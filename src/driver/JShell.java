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
package driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import commands.Parser;
import exceptions.AlreadyExistsException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;

/**
 * This class allows the user to freely manipulate a file system by entering commands into the
 * JShell
 * 
 * @author Jerry Dang
 */
public class JShell {
  /**
   * Stores true value in order for loop to continuously run
   */
  private boolean promptUser;

  /**
   * JShell Constructor
   */
  private JShell() {
    promptUser = true;
  }

  /**
   * This is the main function of the class that operates the JShell by first booting the JShell
   * then prompting the user continually for input. Throws exceptions if input is invalid and exits
   * the program upon exit command
   * 
   * @param args The arguments from the command line
   * @throws IOException
   * @throws FileNotFoundException
   * @throws InvalidArgumentException
   */
  public static void main(String[] args)
      throws FileNotFoundException, IOException, InvalidArgumentException {
    // Continually prompts the user for input
    JShell shell = new JShell();
    Scanner in = new Scanner(System.in);
    while (shell.promptUser) {
      try {
        Parser input = new Parser();
        input.parseUserInput(in.nextLine());
      } catch (InvalidArgumentException | InvalidPathException | AlreadyExistsException
          | InstantiationException | IllegalAccessException | IllegalArgumentException
          | InvocationTargetException | NoSuchMethodException | SecurityException
          | ClassNotFoundException e) {
        System.out.println(e.getMessage());
      }
    }
    in.close();
  }

}
