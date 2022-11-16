package uz.sitelabs.car.car;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper mapper;

    public CarService(CarRepository carRepository,
                      CarMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    public void save(CarDto request) {

        EntCar car = new EntCar();
        if (request.getId() != null) {
            car = getEntityById(request.getId());
        }
        mapper.update(car, request);
        carRepository.save(car);
    }

    public CarDto getOne(Long id) {

        return mapper.toDto(getEntityById(id));
    }

    public List<CarDto> getAll() {

        List<CarDto> carList = carRepository
                .findAllCars().stream().map(mapper::toDto).toList();
        if (carList.isEmpty()) return null;
        return carList;
    }

    public void delete(Long id) {

        carRepository.deleteById(id);
    }

    private EntCar getEntityById(Long id) {

        return carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not fount"));
    }
}