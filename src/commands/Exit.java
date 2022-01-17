package commands;

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
import exceptions.InvalidArgumentException;
import filesystem.FileSystem;

/**
 * This class implements the exit command, which upon being called quits the JShell
 * 
 * @author Jerry Dang
 */
public class Exit implements Command {

  @SuppressWarnings("unused")
  /**
   * Stores the current instance of the filesystem being worked on
   */
  private FileSystem fileSystem;

  /**
   * Initializes fileSystem with the instance of the current FileSystem being worked on.
   */
  public Exit(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * This method quits the current JShell instance. Throws exception if there are arguments given by
   * user
   * 
   * @param userInput A list containing the command and possibly arguments given by the user
   * @exception InvalidArgumentException on too many user arguments
   * @return Nothing
   */
  public String runCommand(String[] userInput) throws InvalidArgumentException {
    if (userInput.length > 1)
      throw new InvalidArgumentException("There are too many " + "arguments\n");
    // Quit the program
    System.exit(0);
    return "\n";
  }

}
