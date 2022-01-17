package filesystem;

import java.io.Serializable;

// **********************************************************
// Assignment2:
// Student1:Arya Sharma
// UTORID user_name:shar1497
// UT Student #:1005692591
// Author: Arya Sharma
//
// Student2: Yuto Omachi
// UTORID user_name: omachiyu
// UT Student #: 1006005163
// Author: Yuto Omachi
//
// Student3:Jameson Joseph
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
/**
 * This class creates instances of nodes for Stack
 * 
 * @author Arya Sharma
 */
@SuppressWarnings("serial")
public class StackNode implements Serializable {
  /**
   * An Object of type Directory, stores the directory of the node
   */
  private Directory Dir;
  /**
   * An Object of type StackNode, stores the object of next node in the stack
   */
  private StackNode next;

  /**
   * This constructor creates an object of StackNode,initializing it's instance variables.
   * 
   * @param Dir Object of type Directory, the Directory that is to be pushed
   * @param next Object of type StackNode, the next node in stack
   * @return Nothing
   */
  public StackNode(Directory Dir, StackNode next) {
    this.Dir = Dir;
    this.next = next;
  }

  /**
   * This method returns the node after this node in the stack
   * 
   * @return An object of StackNode
   */
  public StackNode getNext() {
    return this.next;
  }

  /**
   * This method returns the directory of the node
   * 
   * @return An object of Directory
   */
  public Directory getDir() {
    return this.Dir;
  }
}
