package commands;
//**********************************************************
//Assignment2:
//Student1:Arya Sharma
//UTORID user_name:shar1497
//UT Student #:1005692591
//Author:Arya Sharma
//
//Student2: Yuto Omachi
//UTORID user_name: omachiyu
//UT Student #: 1006005163
//Author: Yuto Omachi
//
//Student3:
//UTORID user_name: josep236
//UT Student #: 1006430845
//Author: Jameson Joseph
//
//Student4: Jia Rong (Jerry) Dang
//UTORID user_name: dangjia
//UT Student #: 1005838685
//Author: Jerry Dang
//
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//I have also read the plagiarism section in the course info
//sheet of CSC B07 and understand the consequences.
//*********************************************************
import java.io.IOException;
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
import exceptions.AlreadyExistsException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPathException;

/**
 * This class serves as the interface for all the subclasses of Command.
 *
 * @author Jameson Joseph
 */
public interface Command {
  /**
   * This method will execute the appropriate procedures/methods in order for the user given command
   * to be executed.
   * 
   * @param userInput A list containing the command and given user arguments.
   * @return None
   * @throws InvalidPathException On invalid path input
   * @throws InvalidArgumentException On invalid argument input
   * @throws AlreadyExistsException On a directory/file that already exists
   * @throws IOException On inappropriate input/output
   * @throws ClassNotFoundException On classes that cannot be found
   */
  public String runCommand(String[] userInput) throws InvalidPathException,
      InvalidArgumentException, AlreadyExistsException, IOException, ClassNotFoundException;
}
