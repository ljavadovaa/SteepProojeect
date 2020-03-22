package app;

import app.console.ConsoleMain;
import app.entities.Flight;
import app.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightServiceTest {
  ConsoleMain console;
  FlightService flightService;

  @BeforeEach
  void setUp() {
    console = new ConsoleMain();
    flightService = new FlightService();
  }

  @Test
  void printTOBoardOne() {
    String expected = flightService.represent(flightService.getID(50))+"\n";
    String actual = flightService.printTOBoardOne(50);
    assertEquals(expected,actual);
  }

  @Test
  void represent() {
    Flight flight = new Flight(51, "Kiev", "Ganja", "18/03/2020", 10, 25);
    String expected = "    - Flight ID: 51 | From Kiev To Ganja | Date: 18/03/2020 | Free seats: 10 | Max seats: 25";
    String actual = flightService.represent(flight);
    assertEquals(expected,actual);
  }

  @Test
  void check_space() {
    boolean expected = false;
    boolean actual = flightService.check_space(new File("src/main/java/app/database/Flights.txt"));
    assertEquals(expected,actual);
  }

  @Test
  void searchByDestination() {
    int expected = 5;
    int actual = flightService.searchByDestination("Chicago".toLowerCase()).size();
    assertEquals(expected,actual);
  }

  @Test
  void searchByTime() {
    int expected = 1;
    int actual = flightService.searchByTime(flightService.searchByDestination("Chicago".toLowerCase()),"24/03/2020").size();
    assertEquals(expected,actual);
  }

  @Test
  void searchByTickets() {
    int expected = 1;
    int actual = flightService.searchByTickets(flightService.
            searchByTime(flightService.searchByDestination("Chicago".toLowerCase()),"24/03/2020"),10).size();
    assertEquals(expected,actual);
  }
}
