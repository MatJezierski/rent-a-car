package matjez.rentacarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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


}
