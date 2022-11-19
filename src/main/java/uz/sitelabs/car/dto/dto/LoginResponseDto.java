package uz.sitelabs.car.dto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sitelabs.car.dto.RoleDto;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean verified;
    private Set<RoleDto> authorities;
}
