package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemElement {
  public Directory(String name, Directory parent) {
    super(name, parent);
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  private List<FileSystemElement> children = new ArrayList<>();

  public void addChild(FileSystemElement child) {
    children.add(child); }

  public void removeChild(FileSystemElement child) {
    children.remove(child); }

  public List<FileSystemElement> getChildren() {
    return children; }
}
