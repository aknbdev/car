package uz.sitelabs.car.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.sitelabs.car.model.EntUser;
import uz.sitelabs.car.model.VerifyToken;
import uz.sitelabs.car.repository.VerifyTokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class VerifyTokenService {

    private final VerifyTokenRepository verifyTokenRepository;

    public String createNewVerifyToken(EntUser user){
        String token = UUID.randomUUID().toString();

        VerifyToken verifyToken = new VerifyToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user);
        verifyTokenRepository.save(verifyToken);
        log.info("Created new token: {}", token);

        return token;
    }

    public void setConfirmedAt(VerifyToken verifyToken){
        verifyToken.setConfirmedAt(LocalDateTime.now());
        verifyTokenRepository.save(verifyToken);
    }

    public VerifyToken getVerifyTokenByToken(String token){
        return verifyTokenRepository.findByToken(token).orElseThrow(
                () -> new IllegalStateException("Token not found!")
        );
    }
}
