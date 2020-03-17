package app.controller;

import app.console.ConsoleMain;
import app.entities.Booking;
import app.entities.Flight;
import app.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {
  ConsoleMain console;
  BookingController bookingController;

  @BeforeEach
  void setUp() {
    console = new ConsoleMain();
    bookingController = new BookingController();
  }

  @Test
  void cancelBooking() {
  }

  @Test
  void myFlights() {
  }

  @Test
  void getID() {
    List<Booking> bookings = new ArrayList<>();
    bookings.add(new Booking(1272,new Person("Emin","Fil"),
            new Flight(50,"Kiev","Ganja","18/03/2020",30,30)));
    bookings.add(new Booking(1273,new Person("Leman","Jav"),
            new Flight(49,"Kiev","Baku","19/03/2020",25,25)));
    bookingController.getAll().addAll(bookings);

    Booking expected = bookingController.getAll().get(49);
    Booking actual = bookingController.getID(50);
    assertEquals(expected,actual);
  }

  @Test
  void getAll() {
    List<Booking> bookings = new ArrayList<>();
    bookings.add(new Booking(1272,new Person("Emin","Fil"),
            new Flight(50,"Kiev","Ganja","18/03/2020",30,30)));
    bookings.add(new Booking(1273,new Person("Leman","Jav"),
            new Flight(49,"Kiev","Baku","19/03/2020",25,25)));
    bookingController.getAll().addAll(bookings);
    int expected = 1273;
    int actual = bookingController.getAll().size();
    assertEquals(expected,actual);
  }

  @Test
  void read() {
    List<Booking> bookings = new ArrayList<>();
    try {
      File file = new File("src/main/java/app/database/Bookings.txt");
      BufferedReader bf = new BufferedReader(new FileReader(file));
      bf.lines().forEach(line -> {
        String[] arrayAll = line.split(" , ");
        String[] arrayID = arrayAll[0].split(" ");
        String[] arrayP = arrayAll[1].split(" ");
        String[] arrayF = arrayAll[2].split(" ");

        bookings.add(new Booking(Integer.parseInt(arrayID[2]), new Person(arrayP[1], arrayP[2])
                , new Flight(Integer.parseInt(arrayF[1]), arrayF[3], arrayF[5], arrayF[7],
                Integer.parseInt(arrayF[10]), Integer.parseInt(arrayF[10]) )));

      });
    } catch (IOException e) {
      console.printLn("Error about reading from Bookings.txt");
    }

    int expected = 1271;
    int actual = bookings.size();
    assertEquals(expected,actual);
  }

  @Test
  void writeToFile() {
  }

  @Test
  void check_space() {
  }

  @Test
  void testCancelBooking() {
  }

  @Test
  void resizeFreeSeat() {
  }

  @Test
  void represent() {
  }

  @Test
  void book() {
  }

  @Test
  void getAllBookings() {
  }
}