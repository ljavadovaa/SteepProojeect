package app.entities;

import java.util.Objects;

public class Flight {
    private int id;
    private String from;
    private String to;
    private String time;
    private int allSeats;
    private int freeSeats;

    public Flight(int id, String from, String to, String time, int freeSeats, int allSeats) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.time = time;
        this.allSeats = allSeats;
        this.freeSeats = freeSeats;
    }

    public int getId() { return id; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getTime() { return time; }
    public int getAllSeats() { return allSeats; }
    public int getFreeSeats() { return freeSeats; }

    public void setFreeSeats(int freeSeats) {
      this.freeSeats = freeSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                allSeats == flight.allSeats &&
                freeSeats == flight.freeSeats &&
                from.equals(flight.from) &&
                to.equals(flight.to) &&
                time.equals(flight.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, time, allSeats, freeSeats);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s to %s | %s | maxSeats: %d", id, from, to, time, allSeats);
    }
}
