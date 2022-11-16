package uz.sitelabs.car.car;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto toDto(EntCar entCar);

    EntCar toEntity(CarDto carDto);

    void update(@MappingTarget EntCar source, CarDto dto);
}
