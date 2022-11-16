package uz.sitelabs.car.car;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("Car get one request id: {}", id);
        return ResponseEntity.ok(carService.getOne(id));
    }

    @GetMapping("/get-all")
    public ModelAndView getAll() {
        log.info("Car get all request.");
        ModelAndView mav = new ModelAndView("list-of-cars");
        mav.addObject("cars", carService.getAll());
        return mav;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CarDto request) {
        log.info("Car create request: {}", request);
        carService.save(request);
        return "redirect:/api/car/get-all";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute CarDto request) {
        log.info("Car update request: {}", request);
        carService.save(request);
        return "redirect:/api/car/get-all";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        log.info("Car delete request id: {}", id);
        carService.delete(id);
        return "redirect:/api/car/get-all";
    }

    @GetMapping("/new-car")
    public ModelAndView newCarForm() {
        ModelAndView mav = new ModelAndView("new-car-form");
        mav.addObject("car", new CarDto());
        return mav;
    }

    @GetMapping("/update-form")
    public ModelAndView updateForm(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("update-car-form");
        mav.addObject("car", carService.getOne(id));
        return mav;
    }
}
