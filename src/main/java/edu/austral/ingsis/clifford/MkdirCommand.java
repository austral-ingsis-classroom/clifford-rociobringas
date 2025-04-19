package edu.austral.ingsis.clifford;

public class MkdirCommand implements Command {
  private final String directoryName;

  public MkdirCommand(String directoryName){
    this.directoryName = directoryName;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.createDirectory(directoryName);
    return "'" + directoryName + "' directory created";
  }
}
