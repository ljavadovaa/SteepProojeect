package app.dao;

import app.console.ConsoleMain;
import app.entities.Flight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightDAO implements DAO<Flight> {
  ConsoleMain console = new ConsoleMain();
  List<Flight> flights = new ArrayList<>(read());

  @Override
  public Flight getID(int id) {
    return flights.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<Flight> getAll() {
    return flights;
  }

  public List<Flight> read(){
    List<Flight> flights = new ArrayList<>();
    try {
      File file = new File("src/main/java/app/database/Flights.txt");
      BufferedReader br = new BufferedReader(new FileReader(file));
      flights = br.lines().map(line -> line.split(",")).
          map(arr -> new Flight(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), Integer.parseInt(arr[5]))).collect(Collectors.toList());
    } catch (Exception e) {
      console.printLn("FILE INPUT OUTPUT ERROR");
    }
    return flights;
  }

}
