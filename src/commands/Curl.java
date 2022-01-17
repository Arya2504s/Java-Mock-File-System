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
import java.io.BufferedReader;
import filesystem.FileSystem;
import filesystem.Directory;
import filesystem.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import exceptions.InvalidArgumentException;

/**
 * This class implements the curl command, which retrieves a file from a website URL and adds it to
 * the current directory
 * 
 * @author Jerry Dang
 */
public class Curl implements Command {
  @SuppressWarnings("unused")
  private FileSystem fileSystem;

  /**
   * Initializes fileSystem with the instance of the current FileSystem being worked on.
   */
  public Curl(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * This method checks for various JavaIO and URL exceptions and reads the file contents and adds
   * it to the current directory.
   * 
   * @param userInput A list containing the command and possibly arguments given by the user
   * @return Nothing
   */
  public String runCommand(String[] userInput) throws InvalidArgumentException {

    if (userInput.length > 2) {
      throw new InvalidArgumentException("Too many arguments.");
    }
    String webAddress = userInput[1];
    String fName = webAddress.substring(webAddress.lastIndexOf('/') + 1).replace(".", "");
    URL targetFile;
    try {
      targetFile = new URL(webAddress);
    } catch (MalformedURLException e) {
      throw new InvalidArgumentException("Invalid URL detected.");
    }
    InputStreamReader inputStream;
    try {
      inputStream = new InputStreamReader(targetFile.openStream());
    } catch (IOException e) {
      throw new InvalidArgumentException("Cannot read from requested URL.");
    }
    BufferedReader in = new BufferedReader(inputStream);
    String newData = "", newLine = "";
    try {
      while ((newLine = in.readLine()) != null) {
        newData = newData + newLine + '\n';
      }
    } catch (IOException e) {
      throw new InvalidArgumentException("Cannot read from file in requested " + "URL");
    }
    Directory currDirectory = fileSystem.getCurrentDirectory();
    Object pathObject = fileSystem.pathExists(fName);
    if (pathObject == null) {
      File newFile = new File(fName, currDirectory);
      newFile.storeFileData(newData);
    } else {
      throw new InvalidArgumentException("File already exists.");
    }
    return "";
  }
}
