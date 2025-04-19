package edu.austral.ingsis;

import edu.austral.ingsis.clifford.*;

import java.util.ArrayList;
import java.util.List;

public class FileSystemRunner {

  private final FileSystem fileSystem = new FileSystem();

  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();

    for (String input : commands) {
      try {
        Command command = parseCommand(input);
        String result = command.execute(fileSystem);
        results.add(result);
      } catch (Exception e) {
        results.add(e.getMessage());
      }
    }

    return results;
  }

  private Command parseCommand(String input) {
    String[] parts = input.split(" ");

    switch (parts[0]) {
      case "ls":
        String order = null;
        if (parts.length > 1 && parts[1].startsWith("--ord=")) {
          order = parts[1].substring("--ord=".length());
        }
        return new LsCommand(order);

      case "mkdir":
        return new MkdirCommand(parts[1]);

      case "touch":
        return new TouchCommand(parts[1]);

      case "cd":
        return new CdCommand(parts[1]);

      case "pwd":
        return new PwdCommand();

      case "rm":
        boolean recursive = false;
        String name;

        if (parts.length == 3 && parts[1].equals("--recursive")) {
          recursive = true;
          name = parts[2];
        } else {
          name = parts[1];
        }
        return new RmCommand(name, recursive);

      default:
        throw new IllegalArgumentException("Unknown command: " + parts[0]);
    }
  }
}