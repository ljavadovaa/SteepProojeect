package app.controller;


import app.console.ConsoleMain;
import app.entities.Booking;
import app.entities.Flight;
import app.library.EnterNumber;
import app.service.BookingService;

import java.io.File;
import java.util.List;

public class BookingController {
  ConsoleMain console = new ConsoleMain();
  EnterNumber enterNumber = new EnterNumber();
  BookingService bookingService = new BookingService();

  public void cancelBooking() {
    console.printLn("Please Enter Booking ID: ");
    int id = enterNumber.enter_number();
    console.printLn("");
    bookingService.cancelBooking(id);
  }

  public void myFlights() {
    bookingService.getAllBookings();
  }

  public Booking getID(int id) {
    return bookingService.getID(id);
  }

  public List<Booking> getAll() {
    return bookingService.getAll();
  }

  public List<Booking> read() {
    return bookingService.read();
  }

  public void writeToFile(String s) {
    bookingService.writeToFile(s);
  }

  public boolean check_space(File file) {
    return bookingService.check_space(file);
  }

  public void cancelBooking(int id) {
    bookingService.cancelBooking(id);
  }

  public void resizeFreeSeat(Flight flight, int index) {
    bookingService.resizeFreeSeat(flight, index);
  }

  public String represent(Booking booking) {
    return bookingService.represent(booking);
  }

  public void book(int tickets, Flight filteredFlight) {
    bookingService.book(tickets, filteredFlight);
  }

  public void getAllBookings() {
    bookingService.getAllBookings();
  }
}
