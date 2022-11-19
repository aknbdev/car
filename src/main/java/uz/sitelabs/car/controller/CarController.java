package uz.sitelabs.car.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.sitelabs.car.service.CarService;
import uz.sitelabs.car.constant.ApiConstants;
import uz.sitelabs.car.dto.CarDto;

@Slf4j
@Controller
@RequestMapping(value = ApiConstants.API_CAR)
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

    @GetMapping("/all")
    public ModelAndView getAll() {
        log.info("Car get all request.");
        ModelAndView mav = new ModelAndView("listing-v1");
        mav.addObject("cars", carService.getAll());
        mav.addObject("siteUrl", ApiConstants.API_CAR);
        return mav;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CarDto request) {
        log.info("Car create request: {}", request);
        carService.save(request);
        return "redirect:/api/car/all";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute CarDto request) {
        log.info("Car update request: {}", request);
        carService.save(request);
        return "redirect:/api/car/all";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        log.info("Car delete request id: {}", id);
        carService.delete(id);
        return "redirect:/api/car/all";
    }

    @GetMapping("/new-car")
    public ModelAndView newCarForm() {
        ModelAndView mav = new ModelAndView("new-car-form");
        mav.addObject("car", new CarDto());
        mav.addObject("siteUrl", ApiConstants.API_CAR);
        return mav;
    }

    @GetMapping("/update-form")
    public ModelAndView updateForm(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("update-car-form");
        mav.addObject("car", carService.getOne(id));
        mav.addObject("siteUrl", ApiConstants.API_CAR);
        return mav;
    }
}
