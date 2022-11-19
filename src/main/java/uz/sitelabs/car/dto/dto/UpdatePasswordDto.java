package uz.sitelabs.car.dto.dto;

import lombok.Getter;
import lombok.Setter;
import uz.sitelabs.car.validation.PasswordMatches;

@Getter
@Setter
@PasswordMatches
public class UpdatePasswordDto {
    
    private String email;
    private String oldPassword;
    private String password;
    private String matchingPassword;
}
