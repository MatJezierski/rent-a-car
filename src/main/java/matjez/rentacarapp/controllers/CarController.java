package matjez.rentacarapp.controllers;

import matjez.rentacarapp.models.Car;
import matjez.rentacarapp.models.dtos.CarDto;
import matjez.rentacarapp.services.CarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/dto/cars")
    public List<CarDto> getCarsDto() {
        return carService.getCarsDto();
    }


}
