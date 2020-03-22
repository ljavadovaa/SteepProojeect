package app;

import app.console.ConsoleMain;
import app.controller.BookingController;
import app.controller.FlightController;
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
  FlightController flightController;

  @BeforeEach
  void setUp() {
    console = new ConsoleMain();
    bookingController = new BookingController();
    flightController = new FlightController();
  }

  @Test
  void getID() {
    Booking expected = bookingController.getAll().get(0);
    Booking actual = bookingController.getID(1);
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
    int expected = 1352;
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
                , new Flight(Integer.parseInt(arrayF[1]), arrayF[3], arrayF[5], String.format(arrayF[7] + " " + arrayF[8]),
                Integer.parseInt(arrayF[11]), Integer.parseInt(arrayF[11]) )));

      });
    } catch (IOException e) {
      console.printLn("Error about reading from Bookings.txt");
    }

    int expected = 1352;
    int actual = bookings.size();
    assertEquals(expected,actual);
  }

  @Test
  void check_space() {
    boolean expected = false;
    boolean actual = bookingController.check_space(new File("src/main/java/app/database/Flights.txt"));
    assertEquals(expected,actual);
  }

  @Test
  void represent() {
    Booking booking = new Booking(1272,new Person("Emin","Fil"),
            new Flight(50,"Kiev","Ganja","18/03/2020",30,30));
    String expected = "BOOKING ID: 1272 , PERSON{ Emin Fil } , FLIGHT{ ID: 50 | Kiev to Ganja | 18/03/2020 | maxSeats: 30 }";
    String actual = bookingController.represent(booking);
    assertEquals(expected,actual);
  }


}