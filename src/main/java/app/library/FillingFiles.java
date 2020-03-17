package app.library;

import app.dao.FlightDAO;
import app.entities.*;
import app.service.BookingService;
import app.service.FlightService;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

public class FillingFiles {
  Random random = new Random();
  public void fillFlights(){
    FlightService flightService = new FlightService();
    List<Airport> airports = Arrays.asList(Airport.values());
    StringBuilder sb = new StringBuilder();

    IntStream.range(0, 50).forEach(i -> {
      int freeSeat = (random.nextInt(10) + 5);
      sb.append(i + 1);
      sb.append(",");
      sb.append("Kiev");
      sb.append(",");
      String city = airports.get(random.nextInt(15)).toString();
      sb.append(city);
      sb.append(",");
      sb.append(MakeTime());
      sb.append(",");
      sb.append(freeSeat);
      sb.append(",");
      sb.append(Airport.valueOf(city).seats);
      sb.append("\n");
    });
    File file = new File("src/main/java/app/database/Flights.txt");
    if ((flightService.check_space(file))){
      flightService.writeToFile(sb.toString());
    }
  }

  public void fillBookings(){
    BookingService bookingService = new BookingService();
    FlightDAO flightDAO = new FlightDAO();
    List<Flight> flights = new ArrayList<>(flightDAO.getAll());
    StringBuilder sb = new StringBuilder();
    int k = 0;
    for (Flight f: flights) {
      for (int i = 1; i <= (f.getAllSeats()-f.getFreeSeats()); i++) {
        k++;
        Booking booking = new Booking(k, MakePerson(), f);
        sb.append(booking.toString());
        sb.append("\n");
      }
    }
    File file = new File("src/main/java/app/database/Bookings.txt");
    if ((bookingService.check_space(file)))
      bookingService.writeToFile(sb.toString());
  }

  public Person MakePerson(){
    List<PersonName> names = Arrays.asList(PersonName.values());
    List<PersonSurname> surnames = Arrays.asList(PersonSurname.values());
    Random random = new Random();
    return new Person(names.get(random.nextInt(names.size())).toString(), surnames.get(random.nextInt(names.size())).toString());
  }

  public String MakeTime(){
    Random random = new Random();
    int randDate = random.nextInt(10);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
    LocalDateTime date = LocalDateTime.now();
    LocalDateTime localDateTime = date.plusDays(randDate).plusHours(randDate).plusMinutes(randDate);
    return localDateTime.format(dateTimeFormatter);
  }

}
