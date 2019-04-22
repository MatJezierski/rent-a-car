package matjez.rentacarapp.controllers;

import matjez.rentacarapp.extras.XLSCreator;
import matjez.rentacarapp.models.dtos.CarDto;
import matjez.rentacarapp.models.dtos.CustomerDto;
import matjez.rentacarapp.services.CarService;
import matjez.rentacarapp.services.CustomerService;
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

    private CustomerService customerService;

    public HomeController(CarService carService, CustomerService customerService) {
        this.carService = carService;
        this.customerService = customerService;
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
        model.addAttribute("customers", customerService.getCustomersDto());
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

    @GetMapping("/customers")
    public String customersPage(Model model){
        model.addAttribute("customers",customerService.getCustomersDto());
        return "customers";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam(name = "customer") String customerSurname){
        customerService.deleteCustomer(customerSurname);
        return "redirect:/customers";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute CustomerDto customerDto){
        customerService.addCustomer(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("/excel")
    public String createXLSFile() throws NoSuchMethodException,
            IOException, IllegalAccessException, InvocationTargetException {
        XLSCreator<CarDto> creator = new XLSCreator<>(CarDto.class);
        creator.createFile(carService.getCarsDto(),"src/main/resources", "cars");
        return "redirect:/";
    }

}
