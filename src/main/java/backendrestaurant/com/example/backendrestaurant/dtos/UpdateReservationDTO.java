package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class UpdateReservationDTO {
    private LocalDate reservationDate;
    private String allergies;
    private String comments;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Ongeldig telefoonnummer")
    private String phoneNumber;

    @Email(message = "Ongeldig e-mailadres")
    private String emailAddress;

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
