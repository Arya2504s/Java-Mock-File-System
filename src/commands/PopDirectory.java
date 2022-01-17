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
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;
import filesystem.Directory;
import filesystem.FileSystem;
import filesystem.StackNode;

/**
 * This class implements the popd command. Throws exceptions when there are extra arguments or when
 * directory stack is empty
 * 
 * @author Jerry Dang
 */
public class PopDirectory implements Command {
  /**
   * Stores the instance of the current filesystem being worked on
   */
  private FileSystem fileSystem;

  /**
   * Initializes fileSystem with the instance of the current FileSystem being worked on.
   */
  public PopDirectory(FileSystem fSystem) {
    fileSystem = fSystem;
  }

  /**
   * Current top node on stack
   */
  private StackNode currTop;

  /**
   * This method removes the directory at the top of the directory stack and makes it the current
   * working directory. Throws exception if directory stack is empty
   * 
   * @throws InvalidArgumentException on empty directory stack
   * @throws InvalidPathException If top node on stack doesnot exists
   * @return Nothing
   */
  public void popDir() throws InvalidArgumentException, InvalidPathException {

    if (fileSystem.getTop() != null) {
      currTop = fileSystem.getTop();
    } else {
      throw new InvalidArgumentException("Stack is empty, Cannot perform this " + "command.");
    }
    StackNode newTop = new StackNode(null, null);
    if (currTop != null) {
      newTop = currTop;
      String dirName = newTop.getDir().getAbsolutePath();
      if (fileSystem.pathExists(dirName) != null
          && fileSystem.pathExists(dirName) instanceof Directory) {
        fileSystem.setCurrentDirectory(newTop.getDir());
        fileSystem.setTop(newTop);
        if (newTop.getNext() == null) {
          fileSystem.setTop(null);
        }
      } else {
        fileSystem.setTop(null);
        throw new InvalidPathException("Directory doesnot exists");
      }
    }
  }

  /**
   * This method changes the top directory of the stack to the current directory and removes that
   * directory from the stack. Throws exception is there are too many arguments
   * 
   * @param userInput A list containing the command and possibly arguments given by the user
   * @exception InvalidArgumentExpcetion on too many user arguments
   * @return Nothing
   */
  public String runCommand(String[] userInput)
      throws InvalidArgumentException, InvalidPathException {
    if (userInput.length > 1) {
      throw new InvalidArgumentException("Invalid input. Too many arguments.");
    } else {
      popDir();
    }
    return "\n";
  }
}
