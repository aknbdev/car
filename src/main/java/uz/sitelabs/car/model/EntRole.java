package uz.sitelabs.car.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.sitelabs.car.enums.RoleEnum;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class EntRole implements GrantedAuthority {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    public EntRole(RoleEnum role) {
        this.role = role;
    }

    public EntRole(){}

    @Override
    public String getAuthority() {
        return role.name();
    }
}
