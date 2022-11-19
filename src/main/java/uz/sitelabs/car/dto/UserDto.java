package uz.sitelabs.car.dto;

import lombok.Getter;
import lombok.Setter;
import uz.sitelabs.car.model.EntRole;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Set<EntRole> authority = new HashSet<>();
    private Boolean verified = Boolean.FALSE;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean deleted = Boolean.FALSE;
}
