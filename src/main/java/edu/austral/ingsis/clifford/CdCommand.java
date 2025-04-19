package edu.austral.ingsis.clifford;

public class CdCommand implements Command {
  private final String path;

  public CdCommand(String path) {
    this.path = path;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.changeDirectory(path);

    String currentPath = fileSystem.getCurrentPath();
    String name;

    if (currentPath.equals("/")) { // para que si estoy en ese muestre solo /
      name = "/";
    } else {
      String[] parts = currentPath.split("/");
      name = parts[parts.length - 1]; // sino, que me muestre el ultimo elemento donde estoy
    }

    return "moved to directory '" + name + "'";
  }
}
