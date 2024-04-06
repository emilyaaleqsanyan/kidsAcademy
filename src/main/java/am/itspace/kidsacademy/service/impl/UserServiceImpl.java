package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.User;
import am.itspace.kidsacademy.entity.enums.UserType;
import am.itspace.kidsacademy.repository.UserRepository;
import am.itspace.kidsacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public String userRegister(User user) {
        User byEmail = findByEmail(user.getEmail());
        if (byEmail == null) {
            user.setUserType(UserType.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/user/register?msg=User Registered";
        } else {
            return "redirect:/user/register?msg=Email already in use";
        }
    }
}




