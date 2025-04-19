package edu.austral.ingsis.clifford;


public class LsCommand implements Command {
  private final String order;

  public LsCommand(String order) {
    this.order = order;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    return fileSystem.list(order).toString();
  }
}