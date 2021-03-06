package matjez.rentacarapp.controllers;

import matjez.rentacarapp.models.Car;
import matjez.rentacarapp.models.dtos.CarDto;
import matjez.rentacarapp.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // ta adnotacja określa dostęp puli IP do zasobów apki, jesli w nawiasie nie ma nic - dostęp ma każdy
@RestController
@RequestMapping("/api/v1")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {

        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {

        return carService.getCars();
    }
    /*
     *
     * DTO - DATA TRANSFER OBJECT
     *
     * */
//    @GetMapping("/dto/cars")
//    public List<CarDto> getCarsDto() {
//        return carService.getCarsDto();
//    }

    @GetMapping("/dto/cars")
    public List<CarDto> getCarsDto(@RequestParam(value ="carType", required = false) String carType) {
        if(carType != null){
            return carService.getCarsByCarType(carType);
        }
        return carService.getCarsDto();
    }

    @PostMapping("/dto/cars")
    public Car addCar(@RequestBody CarDto carDto){
        return carService.addCar(carDto);
    }

    @PutMapping("/dto/cars")
    public void updateCar(@RequestBody CarDto carDto){
        carService.updateCar(carDto);
    }

    @DeleteMapping("/dto/cars/{carModel}")
    public void deleteCar(@PathVariable String carModel){
        carService.deleteCar(carModel);
    }

}
