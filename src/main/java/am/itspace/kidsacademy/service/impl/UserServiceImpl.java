package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.User;
import am.itspace.kidsacademy.entity.enums.UserType;
import am.itspace.kidsacademy.exception.ProblemFoundException;
import am.itspace.kidsacademy.repository.UserRepository;
import am.itspace.kidsacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        User save = userRepository.save(user);
        log.info("User saved: " + user.getEmail());
        return save;
    }

    @Override
    public List<User> findAll() {
        List<User> all = userRepository.findAll();
        log.info("Found " + all.size() + " " + "users");
        return all;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ProblemFoundException());
    }

    @Override
    public String userRegister(User user) {
        User byEmail = findByEmail(user.getEmail());
        if (byEmail == null) {
            user.setUserType(UserType.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("User registered: " + user.getEmail());
            return "redirect:/user/register?msg=User Registered";
        } else {
            log.info("User already registered: " + user.getEmail());
            return "redirect:/user/register?msg=Email already in use";
        }
    }
}




