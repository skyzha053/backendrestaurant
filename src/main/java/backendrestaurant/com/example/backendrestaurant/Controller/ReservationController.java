package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Service.ReservationService;
import backendrestaurant.com.example.backendrestaurant.dtos.CreateReservationDTO;
import backendrestaurant.com.example.backendrestaurant.dtos.ReservationDTO;
import backendrestaurant.com.example.backendrestaurant.dtos.UpdateReservationDTO;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Endpoint om alle reserveringen op te halen
    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        try {
            List<ReservationDTO> reservations = reservationService.getAllReservations().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

            if (!reservations.isEmpty()) {
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    // Endpoint om een nieuwe reservering te maken
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationDTO createReservationDTO) {
        try {
            ReservationDTO reservationDTO = convertToDTO(reservationService.createReservation(convertToEntity(createReservationDTO)));
            return new ResponseEntity<>(reservationDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    // Endpoint om een reservering bij te werken op basis van de naam van de reservering
    @PutMapping("/updateReservationByName/{name}")
    public ResponseEntity<?> updateReservationByName(
            @PathVariable String name,
            @RequestBody UpdateReservationDTO updateReservationDTO
    ) {
        try {
            ReservationDTO updatedReservationDTO = convertToDTO(reservationService.updateReservationByName(name, convertToEntity(updateReservationDTO)));

            if (updatedReservationDTO != null) {
                return new ResponseEntity<>(updatedReservationDTO, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    // Endpoint om een reservering te verwijderen op basis van de naam van de reservering
    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<?> deleteReservationByName(@PathVariable String name) {
        try {
            boolean deleted = reservationService.deleteReservationByName(name);

            if (deleted) {
                return ResponseEntity.ok("Reservering verwijderd");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    // Helper methode om een Entiteit naar DTO te converteren
    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setName(reservation.getName());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setEmailAddress(reservation.getEmailAddress());
        reservationDTO.setPhoneNumber(reservation.getPhoneNumber());
        return reservationDTO;
    }

    // Helper methode om CreateReservationDTO naar Entiteit te converteren
    private Reservation convertToEntity(CreateReservationDTO createReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setName(createReservationDTO.getName());
        reservation.setReservationDate(createReservationDTO.getReservationDate());
        reservation.setEmailAddress(createReservationDTO.getEmailAddress());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
        return reservation;
    }

    // Helper methode om UpdateReservationDTO naar Entiteit te converteren
    private Reservation convertToEntity(UpdateReservationDTO updateReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(updateReservationDTO.getReservationDate());
        reservation.setEmailAddress(updateReservationDTO.getEmailAddress());
        reservation.setPhoneNumber(updateReservationDTO.getPhoneNumber());
        return reservation;
    }
}
