package app.library;

import app.console.ConsoleMain;

public class EnterNumber {
  ConsoleMain console = new ConsoleMain();
  public int enter_number() {
    int number = 0;
    console.print("\nEnter a number: ");
    while (true) {
      try {
        String a = console.readLn().trim();
        number = Integer.parseInt(a);
        break;
      } catch (Exception e) {
        console.printLn("Try again!");
      }
    }
    return number;
  }
}
