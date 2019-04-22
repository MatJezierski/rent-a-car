package matjez.rentacarapp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private String name;

    private String surname;

    private int age;

    private String address;

}
