package uz.sitelabs.car.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uz.sitelabs.car.service.AuthService;

@Slf4j
@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }


    @GetMapping
    public String redirect(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication.isAuthenticated()){
//            return "redirect:/api/car/all";
//        } else {
            return "redirect:/login";
//        }
    }


    @GetMapping("/login")
    public String loginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication.isAuthenticated()){
//            return "redirect:/api/car/all";
//        } else {
            return "login";
//        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody @Valid RegistrationDto request){
//        log.info("Request register email: {}", request.getEmail());
//        authService.register(request);
//        // TODO: 17/11/22
//        return null;
//    }
//
//    @GetMapping("/verify-token/{token}")
//    public ResponseEntity<Boolean> confirm(@PathVariable(value = "token") String token){
//        log.info("Request confirm token: {}", token);
//        return ResponseEntity.ok(authService.confirmToken(token));
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
//        log.info("Request reset-password email: {}", resetPasswordDto.getEmail());
//        return ResponseEntity.ok(authService.resetPassword(resetPasswordDto));
//    }
//
//    @GetMapping("/check-password-token")
//    public ResponseEntity<CheckPasswordDto> checkPasswordToken(@RequestParam("token") String token){
//        log.info("Request check-password-token: {}", token);
//        return ResponseEntity.ok(authService.checkPasswordToken(token));
//    }
//
//    @PostMapping("/update-user-password")
//    public ResponseEntity<?> updateUserPassword(@RequestBody UpdatePasswordDto updatePasswordDto){
//        log.info("Request update-user-password email; {}", updatePasswordDto.getEmail());
//        return ResponseEntity.ok(authService.updateUserPassword(updatePasswordDto));
//    }
}
