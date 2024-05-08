package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserLoginRequestDTO {
    @NotBlank(message = "Gebruikersnaam mag niet leeg zijn")
    private String userName;

    @NotBlank(message = "Wachtwoord mag niet leeg zijn")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
