package app.dao;

import app.console.ConsoleMain;
import app.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
  ConsoleMain console = new ConsoleMain();
  FlightDAO flightDAO = new FlightDAO();

  @BeforeEach
  void setUp() {


  }

  @Test
  void get(int id) {
    try {
      BufferedReader br = new BufferedReader(new FileReader("src/main/java/app/database/Flights.txt"));
      List<String> collect = br.lines().collect(Collectors.toList());
      int count = 0;
      if (flightDAO.getID(id).getId() == Integer.parseInt(collect.toString().split(",")[0]))
        count++;

      int expected = 1;
      int actual = count;
      assertEquals(expected, actual);
    } catch (Exception e) {
      console.printLn("File not found");
    }


//    List<Flight> flights = new ArrayList<>(read());
//    Flight flight = flights.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
//    int expected = 1;
//    assert flight != null;
//    int actual = flight.toString().length();
//    assertEquals(expected,actual);
  }

  @Test
  void getAll() {
    List<Flight> flights = new ArrayList<>();
    flights.add(new Flight(51,"Kiev","Ganja","18/03/2020",10,25));
    flights.add(new Flight(52,"Kiev","Tokyo","27/03/2020",12,27));
    flights.add(new Flight(53,"Kiev","Dubai","19/03/2020",9,29));
    int expected = 3;
    //int actual = flights.getAll().size();
    //assertEquals(expected,actual);
  }

  @Test
  void read() {
    List<Flight> flights = new ArrayList<>();
    try {
      File file = new File("src/main/java/app/files/Flights.txt");
      BufferedReader br = new BufferedReader(new FileReader(file));
      flights = br.lines().map(line -> line.split(",")).
        map(arr -> new Flight(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), Integer.parseInt(arr[5]))).collect(Collectors.toList());
    } catch (Exception e) {
        console.printLn("FILE INPUT OUTPUT ERROR");
    }

    boolean expected = true;
    boolean actual = !flights.isEmpty();
    assertEquals(expected,actual);
    }
}
