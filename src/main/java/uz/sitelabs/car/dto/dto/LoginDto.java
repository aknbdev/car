package uz.sitelabs.car.dto.dto;

import lombok.Getter;
import lombok.Setter;
import uz.sitelabs.car.validation.ValidEmail;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
    @ValidEmail(message = "Email not valid")
    @NotBlank(message = "Email is mandatory!")
    private String email;

    @NotBlank(message = "Password is mandatory!")
    private String password;
}
