package app.entities;

public enum Airport {
  Baku(30),
  London(20),
  Paris(25),
  Moscow(30),
  Berlin(45),
  Barcelona(35),
  Istanbul(40),
  Madrid(30),
  Chicago(50),
  Roma(35),
  Vienna(45),
  Porto(30),
  Helsinki(25),
  Sydney(50),
  Beijing(35);

  public int seats;

  Airport(int seats) {
    this.seats = seats;
  }
}
