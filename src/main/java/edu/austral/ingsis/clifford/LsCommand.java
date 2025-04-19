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
      // porque hago toString me da error porque es una lista
      result += elements.get(i).getName();
      if (i < elements.size() - 1) { // para agregar espacio entre los nombres
        result += " ";
      }
    }

    return result;
  }
}

