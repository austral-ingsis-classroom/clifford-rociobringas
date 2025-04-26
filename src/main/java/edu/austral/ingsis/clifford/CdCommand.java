package edu.austral.ingsis.clifford;

import java.util.NoSuchElementException;
import java.util.Arrays;

public class CdCommand implements Command {
  private final String path;

  public CdCommand(String path) {
    this.path = path;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    Directory targetDirectory = findTargetDirectory(fileSystem);
    fileSystem.setCurrentDirectory(targetDirectory);
    String currentPath = fileSystem.getCurrentPath();
    String name = "/".equals(currentPath) ? "/" : currentPath.substring(currentPath.lastIndexOf("/") + 1);
    return "moved to directory '" + name + "'";
  }

  private Directory findTargetDirectory(FileSystem fileSystem) {
    Directory current = fileSystem.getCurrentDirectory();
    Directory root = fileSystem.getRoot();
    Directory baseDirectory = path.startsWith("/") ? root : current;

    String[] parts = path.split("/");
    if (parts.length > 0 && parts[0].isEmpty()) {
      parts = Arrays.copyOfRange(parts, 1, parts.length);
    }

    Directory target = baseDirectory;

    for (String part : parts) {
      if (part.equals("") || part.equals(".")) {
        continue;
      } else if (part.equals("..")) {
        if (target.getParent() != null) {
          target = target.getParent();
        }
      } else {
        FileSystemElement found = target.findElementByName(part);
        if (found == null || !found.isDirectory()) {
          throw new NoSuchElementException("'" + part + "' directory does not exist");
        }
        target = (Directory) found;
      }
    }

    return target;
  }
}