package matjez.rentacarapp.controllers;

import matjez.rentacarapp.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String homePage(){
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

}
