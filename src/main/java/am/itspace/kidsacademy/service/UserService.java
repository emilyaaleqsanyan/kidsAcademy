package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.User;
import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findByEmail(String email);

}


