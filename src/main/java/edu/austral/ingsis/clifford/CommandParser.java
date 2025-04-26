package edu.austral.ingsis.clifford;


public class CommandParser {
// saque la logica de que comando hacer de file system y la puse aca, entonces file system solo llama a esto
  public Command parseCommand(String input) {
    String[] parts = input.split(" ");

    switch (parts[0]) {
      case "ls":
        return parseLsCommand(parts);
      case "mkdir":
        return new MkdirCommand(parts[1]);
      case "touch":
        return new TouchCommand(parts[1]);
      case "cd":
        return new CdCommand(parts[1]);
      case "pwd":
        return new PwdCommand();
      case "rm":
        return parseRmCommand(parts);
      default:
        throw new IllegalArgumentException("Unknown command: " + parts[0]);
    }
  }

  private Command parseLsCommand(String[] parts) {
    String order = null;
    if (parts.length > 1 && parts[1].startsWith("--ord=")) {
      order = parts[1].substring("--ord=".length());
    }
    return new LsCommand(order);
  }

  private Command parseRmCommand(String[] parts) {
    boolean recursive = false;
    String name;

    if (parts.length == 3 && parts[1].equals("--recursive")) {
      recursive = true;
      name = parts[2];
    } else {
      name = parts[1];
    }
    return new RmCommand(name, recursive);
  }
}