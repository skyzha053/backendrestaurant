package backendrestaurant.com.example.backendrestaurant.Entiteit;


public class TafelUpdateRequest {
    private String tafelNaam;
    private String nieuweNaam;




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
