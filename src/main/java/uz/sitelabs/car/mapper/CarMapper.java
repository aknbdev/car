package uz.sitelabs.car.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uz.sitelabs.car.dto.CarDto;
import uz.sitelabs.car.model.EntCar;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto toDto(EntCar entCar);

    EntCar toEntity(CarDto carDto);

    void update(@MappingTarget EntCar source, CarDto dto);
}
