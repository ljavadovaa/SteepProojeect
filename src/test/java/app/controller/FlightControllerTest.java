package app.controller;

import app.console.ConsoleMain;
import app.dao.FlightDAO;
import app.entities.Flight;
import app.service.FlightService;
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