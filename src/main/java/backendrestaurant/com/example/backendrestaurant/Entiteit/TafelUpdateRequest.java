package backendrestaurant.com.example.backendrestaurant.Entiteit;


public class TafelUpdateRequest {
    private String tafelNaam;
    private String nieuweNaam;

    // Constructors (you can generate these using your IDE)

    // getters en setters
    public String getTafelNaam() {
        return tafelNaam;
    }

    public void setTafelNaam(String tafelNaam) {
        this.tafelNaam = tafelNaam;
    }

    public String getNieuweNaam() {
        return nieuweNaam;
    }

    public void setNieuweNaam(String nieuweNaam) {
        this.nieuweNaam = nieuweNaam;
    }
}
