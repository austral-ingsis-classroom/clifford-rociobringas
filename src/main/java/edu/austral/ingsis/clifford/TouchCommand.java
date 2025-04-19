package edu.austral.ingsis.clifford;

public class TouchCommand implements Command{
  private final String fileName;

  public TouchCommand(String fileName){
    this.fileName = fileName;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.createFile(fileName);
    return fileName + " created";
  }
}
