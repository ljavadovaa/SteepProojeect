package app.controller;

import app.console.ConsoleMain;
import app.dao.FlightDAO;
import app.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {
  ConsoleMain console;
  FlightController flightController;

  @BeforeEach
  void setUp() {
    console = new ConsoleMain();
    flightController = new FlightController();
  }

  @Test
  void getID() {
    List<Flight> flights = new ArrayList<>();
    flights.add(new Flight(51,"Kiev","Ganja","18/03/2020",10,25));
    flights.add(new Flight(52,"Kiev","Tokyo","27/03/2020",12,27));
    flights.add(new Flight(53,"Kiev","Dubai","19/03/2020",9,29));
    flightController.getAll().addAll(flights);

    Flight expected = flightController.getAll().get(50);
    Flight actual = flightController.getID(51);
    assertEquals(expected,actual);
  }

  @Test
  void getAll() {
    List<Flight> flights = new ArrayList<>();
    flights.add(new Flight(51,"Kiev","Ganja","18/03/2020",10,25));
    flights.add(new Flight(52,"Kiev","Tokyo","27/03/2020",12,27));
    flights.add(new Flight(53,"Kiev","Dubai","19/03/2020",9,29));
    flightController.getAll().addAll(flights);
    int expected = 53;
    int actual = flightController.getAll().size();
    assertEquals(expected,actual);
  }

  @Test
  void read() {
    List<Flight> flights = new ArrayList<>();
    try {
      File file = new File("src/main/java/app/database/Flights.txt");
      BufferedReader br = new BufferedReader(new FileReader(file));
      flights = br.lines().map(line -> line.split(",")).
              map(arr -> new Flight(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), Integer.parseInt(arr[5]))).collect(Collectors.toList());
    } catch (Exception e) {
      console.printLn("FILE INPUT OUTPUT ERROR");
    }

    int expected = 50;
    int actual = flights.size();
    assertEquals(expected,actual);
  }

  @Test
  void printToBoardAll() {
    List<Flight> flights = flightController.getAll().stream().
            filter(flight -> flightController.after24hours(flight.getTime())).collect(Collectors.toList());
    StringBuilder sb = new StringBuilder();
    flights.forEach(flight -> sb.append(flightController.represent(flight)).append("\n"));
    String expected = sb.toString();
    String actual = flightController.printToBoardAll();
    assertEquals(expected,actual);
  }

  @Test
  void printTOBoardOne() {
    String expected = flightController.represent(flightController.getID(50))+"\n";
    String actual = flightController.printTOBoardOne(50);
    assertEquals(expected,actual);
  }

  @Test
  void represent() {
    Flight flight = new Flight(51, "Kiev", "Ganja", "18/03/2020", 10, 25);
    String expected = "    - Flight ID: 51 | From Kiev To Ganja | Date: 18/03/2020 | Free seats: 10 | Max seats: 25";
    String actual = flightController.represent(flight);
    assertEquals(expected,actual);
  }

  @Test
  void after24hours() {
    String time = "21/03/2020 12:24";
    boolean expected = false;
    boolean actual = flightController.after24hours(time);
    assertEquals(expected,actual);
  }

  @Test
  void check_space() {
    boolean expected = false;
    boolean actual = flightController.check_space(new File("src/main/java/app/database/Flights.txt"));
    assertEquals(expected,actual);
  }

  @Test
  void searchByDestination() {
    int expected = 3;
    int actual = flightController.searchByDestination("Chicago".toLowerCase()).size();
    assertEquals(expected,actual);
  }

  @Test
  void searchByTime() {
    int expected = 1;
    int actual = flightController.searchByTime(flightController.searchByDestination("Chicago".toLowerCase()),"21/03/2020").size();
    assertEquals(expected,actual);
  }

  @Test
  void searchByTickets() {
    int expected = 1;
    int actual = flightController.searchByTickets(flightController.
            searchByTime(flightController.searchByDestination("Chicago".toLowerCase()),"21/03/2020"),2).size();
    assertEquals(expected,actual);
  }

  @Test
  void showBoard() {
    List<Flight> flights = flightController.getAll().stream().
            filter(flight -> flightController.after24hours(flight.getTime())).collect(Collectors.toList());
    StringBuilder sb = new StringBuilder();
    flights.forEach(flight -> sb.append(flightController.represent(flight)).append("\n"));
    String expected = sb.toString();
    String actual = flightController.showBoard();
    assertEquals(expected,actual);
  }

}