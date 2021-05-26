import java.io.*;

/*

Contains the methods needed to read from a file
and write to a file

*/
class FileIOManager {

  private FileReader reader; //the thing that reads the file
  private FileWriter writer; //the thing that writes characters to a file

  protected boolean appendToFileWhenWritingSolution;

  protected char character; //the current character
  private int i;

  public FileIOManager() {

    reader = null;
    writer = null;

  }

  //Attempts to open specified file, returns true if success, false otherwises
  protected boolean openFile(String fileName) {

    try {

      reader = new FileReader(fileName);

    } catch (Exception e) {

      System.out.println("File of name "+fileName+ " not found.");
      return false;

    }

    return true;

  }

  //Reads in another character, returns true if there was another character to read, false otherwise
  protected boolean readInNextCharacter() {

    try {

      if ((i = reader.read()) != -1) {

        character = (char) i;

      }
      else {

        reader.close();
        return false;

      }

    } catch (Exception e) {

      return false;

    }

    return true;

  }

  //Methods for writing to a file:
  protected void writeMessageToFile(String nameOfFileToWriteTo, String messageToWriteIntoFile) {

    if (writer == null) {

      //initialize the writer

      try {

        writer = new FileWriter(nameOfFileToWriteTo, appendToFileWhenWritingSolution);

      } catch (IOException exception) {

        System.out.println(exception.getMessage());

      }

    }

    //write characters into the file
    for (int characterIndex = 0; characterIndex < messageToWriteIntoFile.length(); characterIndex++) {

      try  {

        writer.write(messageToWriteIntoFile.charAt(characterIndex));

      } catch (IOException exception) {

          System.out.println(exception.getMessage());

      }

    }

    try {

      writer.close();

    } catch (IOException exception) {

      System.out.println(exception.getMessage());

    }

  }

}
