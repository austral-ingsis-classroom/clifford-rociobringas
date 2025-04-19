package edu.austral.ingsis.clifford;

import java.util.*;

public class FileSystem {
  private Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    this.root = new Directory("", null);
    this.currentDirectory = root;
  }

  void createFile(String name){
    File newFile = new File(name, currentDirectory);
    currentDirectory.addChild(newFile);
  }

  void createDirectory(String name){
    Directory newDirectory = new Directory(name, currentDirectory);
    currentDirectory.addChild(newDirectory);
  }

  String getCurrentPath(){
    return currentDirectory.getPath();
  }

  void changeDirectory(String path) {
    String[] parts = path.split("/");

    if (parts.length > 0 && parts[0].isEmpty()) { // Elimino el "" inicial
      parts = Arrays.copyOfRange(parts, 1, parts.length);
    }

    Directory current = root;

    if (!current.getName().equals(parts[0])) {
      throw new NoSuchElementException("Wrong path");
    }

    parts = Arrays.copyOfRange(parts, 1, parts.length);

    while (parts.length > 0) {
      String element = parts[0];

      boolean found = false;
      for (FileSystemElement child : current.listChildren()) {
        if (child.getName().equals(element) && child.isDirectory()) {
          current = (Directory) child;
          found = true;
          break;
        }
      }

      if (!found) {
        throw new NoSuchElementException("Wrong path");
      }

      parts = Arrays.copyOfRange(parts, 1, parts.length);
    }

    currentDirectory = current;
  }

  public List<FileSystemElement> list(String order) {
    List<FileSystemElement> elements = new ArrayList<>(currentDirectory.listChildren());

    if ("asc".equals(order)) {
      elements.sort(Comparator.comparing(FileSystemElement::getName));
    } else if ("desc".equals(order)) {
      elements.sort(Comparator.comparing(FileSystemElement::getName).reversed());
    }

    return elements;
  }

  public void remove(String name, boolean recursive) {
    FileSystemElement toRemove = null;

    for (FileSystemElement child : currentDirectory.listChildren()) {
      if (child.getName().equals(name)) {
        toRemove = child;
        break;
      }
    }

    if (toRemove == null) {
      throw new NoSuchElementException("No such file or directory: " + name);
    }

    if (toRemove.isDirectory()) {
      Directory dirToRemove = (Directory) toRemove;

      if (!recursive) {
        throw new IllegalStateException("Cannot remove directory without --recursive: " + name);
      }

      deleteDirectoryRecursively(dirToRemove);
    }

    currentDirectory.removeChild(toRemove);
  }

  private void deleteDirectoryRecursively(Directory directory) {
    List<FileSystemElement> children = new ArrayList<>(directory.listChildren());

    for (FileSystemElement child : children) {
      if (child.isDirectory()) {
        deleteDirectoryRecursively((Directory) child);
      }
      directory.removeChild(child);
    }
  }


}
