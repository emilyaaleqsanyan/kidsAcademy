package am.itspace.kidsacademy.security;

import am.itspace.kidsacademy.entity.User;
import am.itspace.kidsacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byEmail = userService.findByEmail(username);

        if (byEmail == null) {
            throw new UsernameNotFoundException("User with " + username + " does not exists!");
        }

        return new SpringUser(byEmail);
    }
}



