package web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

@Controller
@ComponentScan("web")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false, defaultValue = "") String count, Model model) {
        int result;
        try {
            result = Integer.parseUnsignedInt(count);
        } catch (NumberFormatException e) {
            result = 0;
        }
        model.addAttribute("cars", carService.getCars(result));

        return "car";
    }
}
