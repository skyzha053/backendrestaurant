package backendrestaurant.com.example.backendrestaurant.Service;
import backendrestaurant.com.example.backendrestaurant.Repository.ReservationRepository;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservering niet gevonden met ID: " + reservationId));
    }

    public Reservation createReservation(Reservation reservation) {
        // Voeg hier indien nodig aanvullende validaties toe
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long reservationId, Reservation updatedReservation) {
        Reservation existingReservation = getReservationById(reservationId);

        // Update de velden van de bestaande reservering met de nieuwe waarden
        existingReservation.setGuestName(updatedReservation.getGuestName());
        existingReservation.setReservationDateTime(updatedReservation.getReservationDateTime());
        existingReservation.setNumberOfGuests(updatedReservation.getNumberOfGuests());

        // Voeg hier indien nodig aanvullende validaties toe
        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long reservationId) {
        Reservation existingReservation = getReservationById(reservationId);
        reservationRepository.delete(existingReservation);
    }
}
