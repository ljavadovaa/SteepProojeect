package app.dao;

import app.console.ConsoleMain;
import app.entities.Booking;
import app.entities.Flight;
import app.entities.Person;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements DAO<Booking> {
  ConsoleMain console = new ConsoleMain();
    @Override
    public Booking getID(int id) {
      List<Booking> bookings = new ArrayList<>(read());
      return bookings.stream().filter(b -> b.getBookingId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Booking> getAll() {
        return read();
    }

    public List<Booking> read(){
      List<Booking> bookings = new ArrayList<>();
      try {
        File file = new File("src/main/java/app/database/Bookings.txt");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        bf.lines().forEach(line -> {
          String[] arrayAll = line.split(" , ");
          String[] arrayID = arrayAll[0].split(" ");
          String[] arrayP = arrayAll[1].split(" ");
          String[] arrayF = arrayAll[2].split(" ");

          bookings.add(new Booking(Integer.parseInt(arrayID[2]), new Person(arrayP[1], arrayP[2])
                  , new Flight(Integer.parseInt(arrayF[1]), arrayF[3], arrayF[5],String.format(arrayF[7] + " "+arrayF[8]),
                  Integer.parseInt(arrayF[11]), Integer.parseInt(arrayF[11]) )));

        });
      } catch (IOException e) {
        console.printLn("Error about reading from Bookings.txt");
      }
      return bookings;
    }

}


