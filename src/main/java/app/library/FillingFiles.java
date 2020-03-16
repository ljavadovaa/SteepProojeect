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
    Map<String, Integer> Cities_seats = new HashMap<>();
    FlightService flightService = new FlightService();
    Cities_seats.put("Baku", 30);
    Cities_seats.put("London", 20);
    Cities_seats.put("Paris", 25);
    Cities_seats.put("Moscow", 30);
    Cities_seats.put("Berlin", 45);
    Cities_seats.put("Barcelona", 35);
    Cities_seats.put("Istanbul", 40);
    Cities_seats.put("Madrid", 30);
    Cities_seats.put("Chicago", 50);
    Cities_seats.put("Roma", 35);
    Cities_seats.put("Vienna", 45);
    Cities_seats.put("Porto", 30);
    Cities_seats.put("Helsinki", 25);
    Cities_seats.put("Sydney", 50);
    Cities_seats.put("Beijing", 35);
    StringBuilder sb = new StringBuilder();
    ArrayList<String> cities = new ArrayList<>(Cities_seats.keySet());

    IntStream.range(0, 50).forEach(i -> {
      int freeSeat = (random.nextInt(10) + 5);
      sb.append(i + 1);
      sb.append(",");
      sb.append("Kiev");
      sb.append(",");
      String city = cities.get(random.nextInt(15));
      sb.append(city);
      sb.append(",");
      sb.append(MakeTime());
      sb.append(",");
      sb.append(freeSeat);
      sb.append(",");
      sb.append(Cities_seats.get(city));
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
