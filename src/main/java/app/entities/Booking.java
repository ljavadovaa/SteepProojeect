package app.entities;

import java.util.Objects;

public class Booking {
    private int bookingId;
    private Person person;
    private Flight flight;

    public Booking(int bookingId, Person person, Flight flight) {
      this.bookingId = bookingId;
      this.person = person;
      this.flight = flight;
    }
    public int getBookingId() { return bookingId; }
    public Person getPerson() { return person; }
    public Flight getFlight() { return flight; }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Booking booking = (Booking) o;
    return bookingId == booking.bookingId &&
            person.equals(booking.person) &&
            flight.equals(booking.flight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookingId, person, flight);
  }

  @Override
    public String toString() {
        return String.format("BOOKING ID: %d , PERSON{ %s } , FLIGHT{%s }", bookingId, person.toString(), flight.toString());
    }
}
