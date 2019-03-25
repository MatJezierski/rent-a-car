package matjez.rentacarapp.mappers;

import matjez.rentacarapp.commons.Mapper;
import matjez.rentacarapp.models.Car;
import matjez.rentacarapp.models.dtos.CarDto;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements Mapper<Car, CarDto> {
    @Override
    public CarDto map(Car from) {
        return CarDto            // CarDto.builder().build() - dzięki Lombokowi i Builderowi
                .builder()
                .carModel(from.getCarModel())
                .carType(from.getCarType())
                .carPrizePerDay(from.getCarPrizePerDay())
                .isAvailable(from.isAvailable())
                .carImage(from.getCarImage())
                .build();
    }

    @Override
    public Car reversedMap(CarDto to) {
        return Car
                .builder()
                .carModel(to.getCarModel())
                .carType(to.getCarType())
                .carPrizePerDay(to.getCarPrizePerDay())
                .isAvailable(to.isAvailable())
                .carImage(to.getCarImage())
                .build();
    }
}
