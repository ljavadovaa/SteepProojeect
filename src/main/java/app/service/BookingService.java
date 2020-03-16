package app.service;

import app.console.ConsoleMain;
import app.dao.BookingDAO;
import app.dao.FlightDAO;
import app.entities.Booking;
import app.entities.Flight;
import app.entities.Person;
import app.library.EnterNumber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    ConsoleMain console = new ConsoleMain();
    BookingDAO bookingDAO = new BookingDAO();
    FlightDAO flightDAO = new FlightDAO();
    FlightService flightService = new FlightService();
    EnterNumber enterNumber = new EnterNumber();

  public Booking getID(int id) { return bookingDAO.getID(id); }

  public List<Booking> getAll() { return bookingDAO.getAll(); }

  public List<Booking> read() { return bookingDAO.read(); }

  public  BookingService(){}

    public void writeToFile(String s) {
      File file = new File("src/main/java/app/database/Bookings.txt");
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(s);
        bw.close();
      }   catch (IOException ex) {
        console.printLn("IO EXCEPTION FOUND!");
      }
    }

  public boolean check_space(File file){
    return (file.length() == 0);
  }

  public void cancelBooking(int id) {
    List<Booking> bookings = new ArrayList<>(bookingDAO.getAll());
    StringBuilder sb = new StringBuilder();
//    List<Booking> collect = bookings.stream().filter(booking -> booking.getBookingId() != id).collect(Collectors.toList());
//    collect.forEach(booking -> sb.append(represent(booking)).append("\n"));
//    writeToFile(sb.toString());
//    console.printLn("\n");

    bookings.forEach(b -> {
      if (b.getBookingId() != id) {
        sb.append(b.toString());
        sb.append("\n");
      } else {
        console.printLn(represent(b));
        console.printLn("");
        console.print("  === CANCELLED === \n");
        resizeFreeSeat(b.getFlight(), 1);
      }
    });
    writeToFile(sb.toString());
    console.printLn("\n");
  }

  public void resizeFreeSeat(Flight flight, int index) {
    List<Flight> flights = new ArrayList<>(flightDAO.getAll());
    StringBuilder sb1 = new StringBuilder();
    flights.forEach(f -> {
      if (f.getId() == flight.getId()) {
        int s = f.getFreeSeats() + index;
        f.setFreeSeats(s);
      }
      sb1.append(f.getId());
      sb1.append(",");
      sb1.append(f.getFrom());
      sb1.append(",");
      sb1.append(f.getTo());
      sb1.append(",");
      sb1.append(f.getTime());
      sb1.append(",");
      sb1.append(f.getFreeSeats());
      sb1.append(",");
      sb1.append(f.getAllSeats());
      sb1.append("\n");
      flightService.writeToFile(sb1.toString());
    });
  }

  public String represent(Booking booking) {
    return String.format("BOOKING ID: %d , PERSON{ %s } , FLIGHT{ %s }", booking.getBookingId(), booking.getPerson().toString(), booking.getFlight().toString());
  }

  public void book(int tickets, Flight filteredFlight) {
    List<Booking> bookings = new ArrayList<>(getAll());
    Booking lastBooking = getID(bookings.size());
    int lastIndex = lastBooking.getBookingId();

    for (int i = 1; i <= tickets; i++) {
      console.printLn("      ===== NEW BOOKING =====\n");
      lastIndex++;
      Booking b = createBooking(lastIndex, filteredFlight);
      bookings.add(b);
    }

    console.printLn("Booking successfully completed!\n");
    StringBuilder sb = new StringBuilder();
            bookings.stream().forEach(b -> sb.append(b.toString()).append("\n"));
    writeToFile(sb.toString());
  }

  private Booking createBooking(int lastIndex, Flight filteredFlight) {
    console.printLn("ENTER YOUR NAME! ");
    String name = console.readLn();
    console.printLn("ENTER YOUR SURNAME! ");
    String surName = console.readLn();
    resizeFreeSeat(filteredFlight, -1);
    String dateTime = filteredFlight.getTime();
    String date = dateTime.split(" ")[0];
    filteredFlight.setDate(date);
    return new Booking(lastIndex, new Person(name, surName), filteredFlight);
  }

  public void getAllBookings() {
    List<Booking> bookings = new ArrayList<>(bookingDAO.getAll());
    console.printLn("ENTER YOUR NAME! ");
    String name = console.readLn().trim();
    console.printLn("ENTER YOUR SURNAME! ");
    String surName = console.readLn().trim();
    int k = 0;
    for (Booking b: bookings){
      if(b.getPerson().getName().toLowerCase().equals(name.toLowerCase()) && b.getPerson().getSurname().toLowerCase().equals(surName.toLowerCase())) {
        console.printLn(represent(b));
        k++;
      }
    }
    if(k == 0) console.printLn("     Bookings not found!\n");
  }
}
