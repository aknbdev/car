package uz.sitelabs.car.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.sitelabs.car.mapper.UserMapper;
import uz.sitelabs.car.mapper.UserService;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserMapper mapper;

    public AppUserDetailsService(UserService userService,
                                 UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return mapper.entUserToUserAuth(userService.getUserByEmail(email));
    }
}
