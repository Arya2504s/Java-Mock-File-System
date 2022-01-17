package commands;

// **********************************************************
// Assignment2:
// Student1:Arya Sharma
// UTORID user_name: shar1497
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
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.StackNode;

/**
 * This class implements the push directory command, it traverser userInput and throws exceptions if
 * the directory does not exist/the userInput has arguments missing/argument is not a directory
 * 
 * @author Arya Sharma
 */
public class PushDirectory implements Command {
  private FileSystem fileSystem;

  /**
   * Initializes fileSystem with the instance of the current FileSystem being worked on.
   */
  public PushDirectory(FileSystem fSystem) {
    fileSystem = fSystem;
  }

  /**
   * This is an instance of StackNode, it holds the topmost node of the Stack.
   */
  private StackNode top;

  /**
   * This method determines pushes directories in to directory stack
   * 
   * @param Instance of Directory to be pushed onto stack
   * @return Nothing
   */
  public void pushDir(Directory dir) {
    top = fileSystem.getTop();
    StackNode tempNode = new StackNode(fileSystem.getCurrentDirectory(), top);
    top = tempNode;
    fileSystem.setTop(top);
    fileSystem.setCurrentDirectory(dir);
  }

  /**
   * This method determines the appropriate PushDirectory method to call based on the contents of
   * userInput.
   * 
   * @param userInput A list containing the command and given user arguments
   * @return Nothing
   * @throws InvalidArgumentException missing directory name
   * @throws InvalidArgumentException too many Arguments
   * @throws InvalidPathException if no such Directory exists
   * @throws InvalidPathException is a file, it should be a directory
   */
  public String runCommand(String[] userInput)
      throws InvalidPathException, InvalidArgumentException {
    if (userInput.length == 1) {
      throw new InvalidArgumentException("Argument missing, no directory name");
    } else if (userInput.length > 2) {
      throw new InvalidArgumentException("too many Arguments");
    } else if (fileSystem.pathExists(userInput[1]) == null) {
      throw new InvalidPathException("No such Directory");
    } else if (fileSystem.pathExists(userInput[1]) instanceof File) {
      throw new InvalidPathException(userInput[1] + " is a file, it should be a directory");
    } else {
      Directory dir = (Directory) fileSystem.pathExists(userInput[1]);
      pushDir(dir);
    }
    return "";
  }
}
