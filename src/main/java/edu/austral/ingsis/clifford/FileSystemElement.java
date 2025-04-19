package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// representa cualquier cosa que este dentro del file system
public abstract class FileSystemElement {
  String name;
  Directory parent;

  public FileSystemElement(String name, Directory parent){
    this.name = name;
    this.parent = parent;
  }

  public String getName(){
    return name;
  }

  public String getPath() {
    if (parent == null) {
      return "/"; // Estoy en el root
    }

    List<String> path = new ArrayList<>();
    FileSystemElement current = this;

    // voy fijandome los parents
    while (current.parent != null) {
      path.add(current.name);
      current = current.parent;
    }

    // invierto porque agrego al reves
    Collections.reverse(path);

    // agrego "/"
    return "/" + String.join("/", path);
  }

  public abstract boolean isDirectory();


}
