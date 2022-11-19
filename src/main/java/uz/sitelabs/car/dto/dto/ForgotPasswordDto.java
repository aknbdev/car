package uz.sitelabs.car.dto.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import uz.sitelabs.car.validation.PasswordMatches;

@Getter
@Setter
@PasswordMatches
public class ForgotPasswordDto {
    
    @NotBlank
    private String token;
    
    private String password;

    private String matchingPassword;
}
