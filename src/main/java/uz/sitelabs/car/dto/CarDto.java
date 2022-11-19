package uz.sitelabs.car.dto;

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
