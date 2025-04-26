package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class FileSystem {
  private final Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    this.root = new Directory("", null);
    this.currentDirectory = root;
  }

  // agrego elemento (sin saber que es) al directory, no importa si es file o direc
  public void addElement(FileSystemElement element) {
    currentDirectory.addChild(element);
  }

  // no me importa el path, solo cambio
  public void setCurrentDirectory(Directory directory) {
    this.currentDirectory = directory;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public String getCurrentPath() {
    return currentDirectory.getPath();
  }

  public List<FileSystemElement> listElements(String order) {
    List<FileSystemElement> elements = new ArrayList<>(currentDirectory.listChildren());

    if (order == null) {
      return elements;
    }

    if ("asc".equals(order)) {
      elements.sort(Comparator.comparing(FileSystemElement::getName));
    } else if ("desc".equals(order)) {
      elements.sort(Comparator.comparing(FileSystemElement::getName).reversed());
    }

    return elements;
  }

  public FileSystemElement findElement(String name) {
    for (FileSystemElement child : currentDirectory.listChildren()) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    throw new NoSuchElementException("No such file or directory: " + name);
  }

  public Directory getRoot() {
    return root;
  }
}