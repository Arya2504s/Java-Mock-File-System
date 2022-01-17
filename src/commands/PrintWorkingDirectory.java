package commands;

// **********************************************************
// Assignment2:
// Student1: Yuto Omachi
// UTORID user_name: omachiyu
// UT Student #: 1006005163
// Author: Yuto Omachi
//
// Student2:Arya Sharma
// UTORID user_name:shar1497
// UT Student #:1005692591
// Author:Arya Sharma
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
import exceptions.InvalidArgumentException;
import filesystem.FileSystem;

/**
 * This class prints the current working directory of the given fileSystem
 *
 * @author Yuto Omachi
 */
public class PrintWorkingDirectory implements Command {
  /**
   * Stores the instance of the current FileSystem being worked on.
   */
  FileSystem fileSystem;

  /**
   * This method is a constructor for PrintWorkingDirectory and initializes the fileSystem instance
   * variable
   * 
   * @param fileSystem fileSystem that we are working on
   */
  public PrintWorkingDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override

  /**
   * This method prints the currentWorkingDirectory in the form of Absolute Path Exception is thrown
   * on incorrect number of userInputs
   * 
   * @param userInput list of userInputs
   * @exception InvalidArgumentException on too many or not enough user input passed to the method
   * @return String representation of current working directory (absolute path)
   */
  public String runCommand(String[] userInput) throws InvalidArgumentException {
    if (userInput.length > 1) {
      throw new InvalidArgumentException("pwd: too many arguments");
    }
    return fileSystem.getCurrentDirectory().getAbsolutePath();
  }
}
