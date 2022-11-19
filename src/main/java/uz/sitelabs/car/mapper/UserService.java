package uz.sitelabs.car.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sitelabs.car.dto.dto.RegistrationDto;
import uz.sitelabs.car.model.EntUser;
import uz.sitelabs.car.model.EntRole;
import uz.sitelabs.car.enums.RoleEnum;
import uz.sitelabs.car.service.VerifyTokenService;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VerifyTokenService verifyTokenService;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository,
                       VerifyTokenService verifyTokenService,
                       UserMapper mapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.verifyTokenService = verifyTokenService;
        this.mapper = mapper;
    }

    public EntUser getUserByEmail(String username) {
        return userRepository.findByEmail(username).orElse(null);
    }

    public String signUp(RegistrationDto request) {
        EntUser user = mapper.registrationDtoToUser(request);
        Optional<EntUser> optionalUser = userRepository.findByEmail(request.getEmail());
        String token;

        if (optionalUser.isPresent()) {

            if (bCryptPasswordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())
                    && !optionalUser.get().getVerified()) {
                user.setAuthorities(optionalUser.get().getAuthorities());
                user.setPassword(optionalUser.get().getPassword());
                user.setId(optionalUser.get().getId());
                userRepository.save(user);
                token = verifyTokenService.createNewVerifyToken(user);
                return token;
            } else {
                throw new IllegalStateException("Email already exist");
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singleton(new EntRole(RoleEnum.USER)));
        userRepository.save(user);
        token = verifyTokenService.createNewVerifyToken(user);
        return token;
    }

    public void enableAppUser(EntUser user) {
        user.setVerified(true);
        userRepository.save(user);
    }
}
