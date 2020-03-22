package app.service;

import app.console.ConsoleMain;
import app.dao.FlightDAO;
import app.entities.Flight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
  ConsoleMain console = new ConsoleMain();
  FlightDAO flightDAO = new FlightDAO();

  public Flight getID(int id) {
    return flightDAO.getID(id);
  }

  public List<Flight> getAll() {
    return flightDAO.getAll();
  }

  public List<Flight> read() {
    return flightDAO.read();
  }

  public String printTOBoardOne(int id) {
    return (getID(id) != null ? represent(getID(id)) : "Flight not found!") + "\n";
  }

  public String represent(Flight flight) {
    return String.format("    - Flight ID: %d | From %s To %s | Date: %s | Free seats: %d | Max seats: %d",
            flight.getId(), flight.getFrom(), flight.getTo(), flight.getTime(), flight.getFreeSeats(), flight.getAllSeats());
  }

  public String printToBoardAll() {
    ArrayList<Flight> flights = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDate nextDay = LocalDate.now().plusDays(1);

    for (Flight f : flightDAO.getAll()) {
      LocalDate flightDate = LocalDate.parse(f.getTime(), formatter);
      if (nextDay.getYear() == flightDate.getYear() && nextDay.getMonth() == flightDate.getMonth() && nextDay.getDayOfMonth() >= flightDate.getDayOfMonth())
        flights.add(f);
    }
    StringBuilder sb = new StringBuilder();
    flights.forEach(f-> sb.append(represent(f)).append("\n"));
    return sb.toString();
  }

  public void writeToFile(String s) {
    File file = new File("src/main/java/app/database/Flights.txt");
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(s);
      bw.close();
    } catch (IOException ex) {
      console.printLn("IO EXCEPTION FOUND!");
    }
  }

  public boolean check_space(File file) {
    return (file.length() == 0);
  }

  public List<Flight> searchByDestination(String dest) {
    List<Flight> flights = new ArrayList<>(getAll());
    return flights.stream().filter(f -> f.getTo().toLowerCase().equals(dest.toLowerCase())).collect(Collectors.toList());
  }

  public List<Flight> searchByTime(List<Flight> flights, String time) {
    List<Flight> flightsByTime = new ArrayList<>(flights);
    List<Flight> flightFilteredTime = new ArrayList<>();
    for (Flight flight: flightsByTime) {
      String flightDate = flight.getTime().split(" ")[0];
      if (time.equals(flightDate)) flightFilteredTime.add(flight);
    }
    return flightFilteredTime;
  }

  public List<Flight> searchByTickets(List<Flight> flights, int tickets) {
    List<Flight> flightsByTickets = new ArrayList<>(flights);
    List<Flight> flightFilteredTickets = new ArrayList<>();
    for (Flight flight : flightsByTickets) {
      int flightTickets = flight.getFreeSeats();
      if (flightTickets >= tickets) flightFilteredTickets.add(flight);
    }
    return flightFilteredTickets;
  }
}
