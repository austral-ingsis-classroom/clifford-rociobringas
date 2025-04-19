package edu.austral.ingsis.clifford;

public class CdCommand implements Command{
  private final String path;

  public CdCommand(String path){
    this.path = path;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.changeDirectory(path);
    return "Moved to directory: '" + path + "'";
  }
}
