package uz.sitelabs.car.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {

    private Long id;

    private String model;

    private String color;

    private String version;
}
