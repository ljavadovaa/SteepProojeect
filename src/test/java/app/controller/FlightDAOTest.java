package app.controller;

import app.console.ConsoleMain;
import app.dao.FlightDAO;
import app.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightDAOTest {
  ConsoleMain console;
  FlightDAO flightDAO;

  @BeforeEach
  void setUp() {
    console = new ConsoleMain();
    flightDAO = new FlightDAO();
  }

  @Test
  void getID() {
    List<Flight> flights = new ArrayList<>();
    flights.add(new Flight(51,"Kiev","Ganja","18/03/2020",10,25));
    flights.add(new Flight(52,"Kiev","Tokyo","27/03/2020",12,27));
    flights.add(new Flight(53,"Kiev","Dubai","19/03/2020",9,29));
    flightDAO.getAll().addAll(flights);

    Flight expected = flightDAO.getAll().get(50);
    Flight actual = flightDAO.getID(51);
    assertEquals(expected,actual);
  }

  @Test
  void getAll() {
    List<Flight> flights = new ArrayList<>();
    flights.add(new Flight(51,"Kiev","Ganja","18/03/2020",10,25));
    flights.add(new Flight(52,"Kiev","Tokyo","27/03/2020",12,27));
    flights.add(new Flight(53,"Kiev","Dubai","19/03/2020",9,29));
    flightDAO.getAll().addAll(flights);
    int expected = 53;
    int actual = flightDAO.getAll().size();
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
}
