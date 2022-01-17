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
import exceptions.InvalidPathException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;

/**
 * This class changes the current working directory of a given FileSystem
 *
 * @author Yuto Omachi
 */
public class ChangeDirectory implements Command {
  /**
   * Stores the instance of the current FileSystem being worked on.
   */
  FileSystem fileSystem;

  /**
   * This method is a constructor for ChangeDirectory and the stores the current filesystem being
   * worked on
   * 
   * @param fileSystem the fileSystem that we work on
   */
  public ChangeDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * This method changes the currentDirectory in fileSystem to userInput[1]. If no such path is
   * found do not change and thrown an Exception
   * 
   * @param userInput list of userInputs
   * @exception InvalidArgumentException on too many or not enough user input passed to the method
   * @exception InvalidPathException on path that does not exist in the fileSystem
   * @return empty string
   */
  public String runCommand(String[] userInput)
      throws InvalidArgumentException, InvalidPathException {
    if (userInput.length < 2) {
      throw new InvalidArgumentException("cd: not enough arguments");
    } else if (userInput.length > 2) {
      throw new InvalidArgumentException("cd: too many arguments");
    }
    Object possiblePath = fileSystem.pathExists(userInput[1]);
    if (possiblePath instanceof Directory) {
      fileSystem.setCurrentDirectory((Directory) possiblePath);
      return "";
    }
    if (possiblePath instanceof File) {
      throw new InvalidPathException("cd: not a directory: " + userInput[1]);
    }
    throw new InvalidPathException("cd: no such file or directory: " + userInput[1]);
  }
}
