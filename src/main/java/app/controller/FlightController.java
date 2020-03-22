package app.controller;

import app.console.ConsoleMain;
import app.entities.Flight;
import app.library.EnterNumber;
import app.service.BookingService;
import app.service.FlightService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlightController {
  ConsoleMain console = new ConsoleMain();
  FlightService flightService = new FlightService();
  EnterNumber enterNumber = new EnterNumber();
  BookingService bookingService = new BookingService();

  public Flight getID(int id) { return flightService.getID(id); }

  public List<Flight> getAll() { return flightService.getAll(); }

  public List<Flight> read() { return flightService.read(); }

  public String printToBoardAll() { return flightService.printToBoardAll(); }

  public String printTOBoardOne(int id) { return flightService.printTOBoardOne(id); }

  public String represent(Flight flight) { return flightService.represent(flight); }

  //public boolean after24hours(String a) { return flightService.after24hours(a); }

  public void writeToFile(String s) { flightService.writeToFile(s); }

  public boolean check_space(File file) { return flightService.check_space(file); }

  public List<Flight> searchByDestination(String dest) { return flightService.searchByDestination(dest); }

  public List<Flight> searchByTime(List<Flight> flights, String time) { return flightService.searchByTime(flights, time); }

  public List<Flight> searchByTickets(List<Flight> flights, int tickets) { return flightService.searchByTickets(flights, tickets); }

  public String showBoard() { return printToBoardAll(); }

  public String showFlight() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n      === Please, Enter flight ID! ===\n");
    int id = enterNumber.enter_number();
    sb.append("\n").append(printTOBoardOne(id));
    return sb.toString();
  }

  public void searching(){
    console.printLn("\n      === Enter Destination Where you wanna go! or Enter '0' (zero) to back 'MAIN MENU'! ===");
    String destination = console.readLn().trim();
    if(backToMainMenu(destination)) { return; }
    console.printLn("                ===== Results for your destination =====\n");
    List<Flight> flightsByDestination = new ArrayList<>(searchByDestination(destination.trim()));
    searchByDestination(destination).forEach(flight -> console.printLn(represent(flight)));

    if(flightsByDestination.size() != 0) {
      console.printLn("\n      === Enter Date When you wanna go! Ex: (11/03/2020 YYYY/MM/DD) or Enter '0' (zero) to back 'MAIN MENU'! ===");
      String time = console.readLn().trim();
      if(backToMainMenu(time)) { return; }
      console.printLn("                ===== Results for your destination and date =====\n");
      List<Flight> flightsByTime = new ArrayList<>(searchByTime(flightsByDestination, time.trim()));
      searchByTime(flightsByDestination,time).forEach(flight -> console.printLn(represent(flight)));

      if(flightsByTime.size() != 0){
        console.printLn("\n      === Enter Number of Tickets! ===  or Enter '0' (zero) to back 'MAIN MENU'! ===");
        int tickets = enterNumber.enter_number();
        if(backToMainMenu(String.valueOf(tickets))) { return; }
        console.printLn("\n                ===== Results for your destination , time and ticket number =====\n");
        List<Flight> flightsByTicket = new ArrayList<>(searchByTickets(flightsByTime, tickets));
        searchByTickets(flightsByTime,tickets).forEach(flight -> console.printLn(represent(flight)));

        Flight filteredFlight = null;
        if (flightsByTicket.size() != 0) {
          if(flightsByTicket.size() > 1) {
            console.printLn("\n      === Please Select Flight ID: ===");
            int flightId = enterNumber.enter_number();
            for (Flight f : flightsByTicket) {
              if(f.getId() == flightId)
                filteredFlight = f;
            }
          }
          else
            filteredFlight = flightsByTicket.get(0);

          console.printLn("\n        ===== Start to book =====\n");
          bookingService.book(tickets, filteredFlight);
        }
        else console.printLn("               ---  )-: Flights Not Found :-(  ---\n");
      }
      else console.printLn("               ---  )-: Flights Not Found :-(  ---\n");
    }
    else console.printLn("               ---  )-: Flights Not Found :-(  ---\n");

  }

  public boolean backToMainMenu(String a) {
    return a.equals("0");
  }

}
