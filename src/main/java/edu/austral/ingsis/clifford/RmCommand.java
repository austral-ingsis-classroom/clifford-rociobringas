package edu.austral.ingsis.clifford;

import java.util.NoSuchElementException;

public class RmCommand implements Command {
  private final String name;
  private final Boolean recursive;

  public RmCommand(String name, Boolean recursive) {
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    Directory current = fileSystem.getCurrentDirectory();
    FileSystemElement toRemove = current.findElementByName(name);

    if (toRemove == null) {
      throw new NoSuchElementException("No such file or directory: " + name);
    }

    if (toRemove.isDirectory()) {
      if (!recursive) {
        throw new IllegalStateException("cannot remove '" + name + "', is a directory");
      }
      deleteDirectoryRecursively((Directory) toRemove);
    }

    current.removeChild(toRemove);

    return "'" + name + "' removed";
  }

  private void deleteDirectoryRecursively(Directory directory) {
    for (FileSystemElement child : directory.listChildren()) {
      if (child.isDirectory()) {
        deleteDirectoryRecursively((Directory) child);
      }
    }
    Directory parent = directory.getParent();
    if (parent != null) {
      parent.removeChild(directory);
    }
  }
}