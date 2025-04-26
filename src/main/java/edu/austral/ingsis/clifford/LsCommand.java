package edu.austral.ingsis.clifford;

import java.util.List;

public class LsCommand implements Command {
  private final String order;

  public LsCommand(String order) {
    this.order = order;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    List<FileSystemElement> elements = fileSystem.listElements(order);
    sortElements(elements);
    return formatElements(elements);
  }

  private void sortElements(List<FileSystemElement> elements) {
    if ("asc".equals(order)) {
      elements.sort((a, b) -> a.getName().compareTo(b.getName()));
    } else if ("desc".equals(order)) {
      elements.sort((a, b) -> b.getName().compareTo(a.getName()));
    }
    // Si order es null, no hago nada (mantengo el orden original)
  }

  private String formatElements(List<FileSystemElement> elements) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < elements.size(); i++) {
      result.append(elements.get(i).getName());
      if (i < elements.size() - 1) {
        result.append(" ");
      }
    }
    return result.toString();
  }
}