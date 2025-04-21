package edu.austral.ingsis.clifford;


import java.util.List;

public class LsCommand implements Command {
  private final String order;

  public LsCommand(String order) {
    this.order = order;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    List<FileSystemElement> elements = fileSystem.list(order);
    String result = "";

    for (int i = 0; i < elements.size(); i++) {
      result += elements.get(i).getName();
      if (i < elements.size() - 1) {
        result += " ";
      }
    }

    return result;
  }
}

