package edu.austral.ingsis.clifford;

public class PwdCommand implements Command {

  @Override
  public String execute(FileSystem fileSystem) {
    return fileSystem.getCurrentPath();
  }
}
