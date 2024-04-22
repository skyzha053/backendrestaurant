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

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (!reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody CreateReservationDTO createReservationDTO) {
        ReservationDTO reservationDTO = convertToDTO(reservationService.createReservation(convertToEntity(createReservationDTO)));
        return new ResponseEntity<>(reservationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateReservationByName/{name}")
    public ResponseEntity<ReservationDTO> updateReservationByName(
            @PathVariable String name,
            @RequestBody UpdateReservationDTO updateReservationDTO
    ) {
        ReservationDTO updatedReservationDTO = convertToDTO(reservationService.updateReservationByName(name, convertToEntity(updateReservationDTO)));

        if (updatedReservationDTO != null) {
            return new ResponseEntity<>(updatedReservationDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<String> deleteReservationByName(@PathVariable String name) {
        boolean deleted = reservationService.deleteReservationByName(name);

        if (deleted) {
            return new ResponseEntity<>("Reservering verwijderd", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reservering niet gevonden", HttpStatus.NOT_FOUND);
        }
    }

    // Helper method to convert entity to DTO
    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setName(reservation.getName());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setEmailAddress(reservation.getEmailAddress());
        reservationDTO.setPhoneNumber(reservation.getPhoneNumber());
        return reservationDTO;
    }

    // Helper method to convert DTO to entity
    private Reservation convertToEntity(CreateReservationDTO createReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setName(createReservationDTO.getName());
        reservation.setReservationDate(createReservationDTO.getReservationDate());
        reservation.setEmailAddress(createReservationDTO.getEmailAddress());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
        return reservation;
    }

    // Helper method to convert DTO to entity
    private Reservation convertToEntity(UpdateReservationDTO updateReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(updateReservationDTO.getReservationDate());
        reservation.setEmailAddress(updateReservationDTO.getEmailAddress());
        reservation.setPhoneNumber(updateReservationDTO.getPhoneNumber());
        return reservation;
    }
}
