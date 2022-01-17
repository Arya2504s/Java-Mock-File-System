package exceptions;

// **********************************************************
// Assignment2:
// Student1:Jameson Joseph
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
@SuppressWarnings("serial")
/**
 * This class creates a new exception with a specific error message based on the checked exceptions.
 * 
 * @author Jameson Joseph
 */
public class InvalidArgumentException extends Exception {
  /**
   * This method calls the constructor of Exception class to create a new exception with
   * errorMessage.
   * 
   * @param errorMessage The string containing the specified error message.
   */
  public InvalidArgumentException(String errorMessage) {
    super(errorMessage);
  }

}
