package uz.sitelabs.car.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.sitelabs.car.dto.dto.RegistrationDto;
import uz.sitelabs.car.model.EntUser;
import uz.sitelabs.car.dto.AppUserDetails;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AppUserDetails entUserToUserAuth(EntUser entUser);


    EntUser registrationDtoToUser(RegistrationDto source);
}
