package edu.austral.ingsis.clifford;

public class RmCommand implements Command{
  private final String name;
  private final Boolean recursive;

  public RmCommand( String name, Boolean recursive){
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public String execute(FileSystem fileSystem) {
    fileSystem.remove(name,recursive);
    return name + " deleated succesfully";
  }
}
