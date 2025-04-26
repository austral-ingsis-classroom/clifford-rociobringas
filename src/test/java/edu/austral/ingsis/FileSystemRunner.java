package edu.austral.ingsis;

import edu.austral.ingsis.clifford.*;

import java.util.ArrayList;
import java.util.List;
// Ahora FileSystemRunner solo ejecuta
// antes rompia con el principio de single responsability porque hacia dos cosas
// (ejecutaba comando e interpetaba textos)
// la parte de intepretar comandos la movi a command parcer, y ahora este solo ejectuta


public class FileSystemRunner {

  private final FileSystem fileSystem = new FileSystem();
  private final CommandParser commandParser = new CommandParser();

  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();

    for (String input : commands) {
      try {
        Command command = commandParser.parseCommand(input);
        String result = command.execute(fileSystem);
        results.add(result);
      } catch (Exception e) {
        results.add(e.getMessage());
      }
    }

    return results;
  }
}


// inmutable
// sacar logica en file system y ponerlo en los comandos , command parcer
// si agrego x comando, no deberia cambiar el file system
// ADT !OO