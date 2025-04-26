package edu.austral.ingsis.clifford;

public class TouchCommand implements Command {
  private final String fileName;

  public TouchCommand(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    File newFile = new File(fileName, fileSystem.getCurrentDirectory());
    fileSystem.addElement(newFile);
    return "'" + fileName + "' file created";
  }
}
