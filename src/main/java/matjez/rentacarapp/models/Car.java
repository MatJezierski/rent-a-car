package matjez.rentacarapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "car_model", nullable = false, unique = true)
    private String carModel;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "car_prize_per_day")
    private double carPrizePerDay;

    @Column (name = "car_info")
    private String carInfo;

    @Column(name = "availability")
    private Boolean isAvailable;

    @Column(name = "car_image")
    private String carImage;

}
