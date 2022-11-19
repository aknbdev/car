package uz.sitelabs.car.dto.dto;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import uz.sitelabs.car.validation.ValidEmail;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResetPasswordDto {
    
    @ValidEmail(message = "Email is invalid!")
    @NotBlank(message = "Email is mandatory")
    private String email;
}
