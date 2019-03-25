package matjez.rentacarapp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    // kopiujemy tutaj wszystko co jest w klasie Car (bez adnotacji), oprócz pola Id
    // DAO to obiekty z bazy, a DTO ma lecieć na frontend, do klienta

    private String carModel;

    private String carType;

    private double carPrizePerDay;

    private boolean isAvailable;

    private String carImage;

}
