package edu.austral.ingsis.clifford;

public class MkdirCommand implements Command {
  private final String directoryName;

  public MkdirCommand(String directoryName) {
    this.directoryName = directoryName;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    Directory newDirectory = new Directory(directoryName, fileSystem.getCurrentDirectory());
    fileSystem.addElement(newDirectory);
    return "'" + directoryName + "' directory created";
  }
}
