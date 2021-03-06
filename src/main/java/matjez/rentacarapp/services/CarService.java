package matjez.rentacarapp.services;

import matjez.rentacarapp.mappers.CarMapper;
import matjez.rentacarapp.models.Car;
import matjez.rentacarapp.models.dtos.CarDto;
import matjez.rentacarapp.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    // @Autowired może być w tym miejscu, ale nie jest wymagana, bo jak widać w linii 12 - wstrzykiwanie przez konstruktor.
    private CarRepository carRepository;
    private CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }
    /*
    *
    * DAO - DATA ACCESS OBJECT
    *
    * */

    // getCars() = wartstwa DAO aplikacji (DATA ACCESS OBJECT)
    public List<Car> getCars(){
        // Controller w Springu korzysta zawsze z Servisu, a więc to w Service musi być cała logika biznesowa aplikacji.
        // Repository służy tylko do odpytywania bazy danych.
        return carRepository.findAll();  //metoda findAll() pochodzi z JpaRepository, a więc interfejsu zaimplementowanego w repo
    }

    /*
    *
    * DTO - DATA TRANSFER OBJECT
    *
    * */
    public List<CarDto> getCarsDto(){
        // Controller w Springu korzysta zawsze z Servisu, a więc to w Service musi być cała logika biznesowa aplikacji.
        // Repository służy tylko do odpytywania bazy danych.
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::map)
                .collect(Collectors.toList());
    }

    public Car addCar(CarDto carDto){
        return carRepository.save(carMapper.reversedMap(carDto));
    }

    public void updateCar(CarDto carDto){
        carRepository
                .findCarByCarModel(carDto.getCarModel())
                .ifPresent(c -> {
                    c.setCarPrizePerDay(carDto.getCarPrizePerDay());
                    c.setCarType(carDto.getCarType());
                    c.setAvailable(carDto.isAvailable());
                    c.setCarImage(carDto.getCarImage());

                    carRepository.save(c);
                });
    }

    public void deleteCar(String carModel){
        carRepository.deleteCarByCarModel(carModel);
    }

    public List<CarDto> getCarsByCarType(String carType){

        return carRepository.findCarsByCarType(carType)
                .stream()
                .map(carMapper::map)
                .collect(Collectors.toList());
    }

}
