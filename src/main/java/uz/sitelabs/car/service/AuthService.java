package uz.sitelabs.car.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.sitelabs.car.dto.dto.CheckPasswordDto;
import uz.sitelabs.car.dto.dto.RegistrationDto;
import uz.sitelabs.car.dto.dto.ResetPasswordDto;
import uz.sitelabs.car.dto.dto.UpdatePasswordDto;
import uz.sitelabs.car.mapper.UserService;
import uz.sitelabs.car.model.EntUser;
import uz.sitelabs.car.model.VerifyToken;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AuthService {
    private final UserService userService;
    private final MailService mailService;
    private final VerifyTokenService verifyTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService appUserService,
                       MailService mailService,
                       VerifyTokenService verifyTokenService,
                       AuthenticationManager authenticationManager) {

        this.verifyTokenService = verifyTokenService;
        this.userService = appUserService;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
    }



    public void register(RegistrationDto request) {

        String token = userService.signUp(request);

        log.info("token created :{}", token);
        mailService.sendVerifyMail(request.getEmail(), token);
    }

    @Transactional
    public Boolean confirmToken(String token) {

        VerifyToken verifyToken = verifyTokenService.getVerifyTokenByToken(token);

        if (verifyToken == null) {
            throw new IllegalStateException("Invalid token.");
        }

        if (verifyToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        if (verifyToken.getExpiresAt().isBefore(LocalDateTime.now())) {

            throw new IllegalStateException("Confirmation token is expired!");
        }

        verifyTokenService.setConfirmedAt(verifyToken);
        userService.enableAppUser(verifyToken.getUser());
        log.info("Confirmed user email: {}", verifyToken.getUser().getEmail());
        return true;
    }

    public String resetPassword(ResetPasswordDto resetPasswordDto) {
        EntUser user = userService.getUserByEmail(resetPasswordDto.getEmail());

        String token = verifyTokenService.createNewVerifyToken(user);

        log.info("Token created: {}", token);

        mailService.sendResetPassword(user.getEmail(), token);

        return token;
    }

    public CheckPasswordDto checkPasswordToken(String token) {

        VerifyToken verifyToken = verifyTokenService.getVerifyTokenByToken(token);

        if (verifyToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is expired!");
        }

        return new CheckPasswordDto(
                verifyToken.getUser().getEmail(),
                verifyToken.getUser().getPassword());
    }

    public Object updateUserPassword(UpdatePasswordDto updatePasswordDto) {

        userService.getUserByEmail(updatePasswordDto.getEmail());


        return null;
    }
}