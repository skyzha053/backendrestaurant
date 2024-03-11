package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Reservation;
import backendrestaurant.com.example.backendrestaurant.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservationByName(String name, Reservation updatedReservation) {
        Optional<Reservation> existingReservation = reservationRepository.findByName(name);

        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            // Update fields as needed
            reservation.setNumberOfPersons(updatedReservation.getNumberOfPersons());
            reservation.setReservationDate(updatedReservation.getReservationDate());
            reservation.setAllergies(updatedReservation.getAllergies());
            reservation.setComments(updatedReservation.getComments());

            return reservationRepository.save(reservation);
        } else {
            return null; // Handle not found scenario
        }
    }

    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        } else {
            return false; // Handle not found scenario
        }
    }

    public boolean deleteReservationByName(String name) {
        Optional<Reservation> existingReservation = reservationRepository.findByName(name);

        if (existingReservation.isPresent()) {
            reservationRepository.delete(existingReservation.get());
            return true;
        } else {
            return false;
        }
    }
}
