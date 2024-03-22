package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Reservation;
import backendrestaurant.com.example.backendrestaurant.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/reservering/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        if (!reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        // Set the current date if the reservation date is not provided in the request
        if (reservation.getReservationDate() == null) {
            reservation.setReservationDate(LocalDate.now());
        }

        // Set the default email only if it is not provided in the request
        if (reservation.getEmailAddress() == null || reservation.getEmailAddress().isEmpty()) {
            // Set the default email (replace with your logic)
            reservation.setEmailAddress("default@example.com");
        }

        // Set the default phone number only if it is not provided in the request
        if (reservation.getPhoneNumber() == null || reservation.getPhoneNumber().isEmpty()) {
            // Set the default phone number (replace with your logic)
            reservation.setPhoneNumber("123456789");
        }

        // Implement logic to create a new reservation
        Reservation createdReservation = reservationService.createReservation(reservation);

        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }




    @PutMapping("/updateReservationByName/{name}")
    public ResponseEntity<Reservation> updateReservationByName(
            @PathVariable String name,
            @RequestBody Reservation updatedReservation
    ) {
        Reservation updated = reservationService.updateReservationByName(name, updatedReservation);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<String> deleteReservationByName(@PathVariable String name) {
        // Implement logic to delete a reservation by name
        boolean deleted = reservationService.deleteReservationByName(name);

        if (deleted) {
            return new ResponseEntity<>("Reservering verwijderd", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reservering niet gevonden", HttpStatus.NOT_FOUND);
        }
    }



}
