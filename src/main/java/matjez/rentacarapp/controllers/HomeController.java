package matjez.rentacarapp.controllers;

import matjez.rentacarapp.extras.XLSCreator;
import matjez.rentacarapp.models.dtos.CarDto;
import matjez.rentacarapp.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
public class HomeController {

    private CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    /*
    * Ta klasa jest poza pakietem "controllers", jako że to nie będzie controller RESTowy.
    * Poza tym "view" ma być w założeniu w Angularze, nie tak jak teraz w zwykłym HTMLu,
    * ,więc cała ta paczka "view" będzie po skończeniu aplikacji "deprecated".
    *
    */

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("cars", carService.getCarsDto());
        return "index";
    }

    @GetMapping("/cars")
    public String carsPage(Model model){
        model.addAttribute("cars",carService.getCarsDto());
        return "cars";
    }

    @GetMapping("/delete")
    public String deleteCar(@RequestParam(name = "car") String carModel){
        carService.deleteCar(carModel);
        return "redirect:/cars";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute CarDto car){
        carService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/excel")
    public String createXLSFile() throws NoSuchMethodException,
            IOException, IllegalAccessException, InvocationTargetException {
        XLSCreator<CarDto> creator = new XLSCreator<>(CarDto.class);
        creator.createFile(carService.getCarsDto(),"src/main/resources", "cars");
        return "redirect:/";
    }

}
