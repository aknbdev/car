package uz.sitelabs.car.model;

import lombok.Getter;
import lombok.Setter;
import uz.sitelabs.car.model.EntUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class VerifyToken {

        @Id
        @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
        private Long id;

        @Column(name = "token", nullable = false, length = 64)
        private String token;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "expires_at", nullable = false)
        private LocalDateTime expiresAt;

        @Column(name = "confirmed_at")
        private LocalDateTime confirmedAt;

        @ManyToOne
        @JoinColumn(nullable = false, name = ("user_id"))
        private EntUser user;

        public VerifyToken(String token,
                           LocalDateTime createdAt,
                           LocalDateTime expiresAt,
                           EntUser user) {

                this.token = token;
                this.createdAt = createdAt;
                this.expiresAt = expiresAt;
                this.user = user;
        }

        public VerifyToken(){

        }
}
