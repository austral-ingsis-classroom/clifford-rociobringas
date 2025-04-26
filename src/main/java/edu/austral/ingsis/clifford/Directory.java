package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemElement {

  private final List<FileSystemElement> children = new ArrayList<>();

  public Directory(String name, Directory parent) {
    super(name, parent);
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public FileSystemElement findElementByName(String name) {
    for (FileSystemElement child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    return null;
  }

  public void addChild(FileSystemElement child) {
    children.add(child);
  }

  public void removeChild(FileSystemElement child) {
    children.remove(child);
  }

  public List<FileSystemElement> listChildren() {
    return children;
  }
}
