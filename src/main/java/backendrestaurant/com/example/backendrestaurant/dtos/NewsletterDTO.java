package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotBlank;

public class NewsletterDTO {

    @NotBlank(message = "Bestandsnaam mag niet leeg zijn")
    private String fileName;

    private byte[] data;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
