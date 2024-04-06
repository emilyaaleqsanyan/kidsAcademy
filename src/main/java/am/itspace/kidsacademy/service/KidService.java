package am.itspace.kidsacademy.service;

import am.itspace.kidsacademy.entity.Kid;
import am.itspace.kidsacademy.security.SpringUser;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface KidService {

    Kid save(Kid kid);

    List<Kid> findAll();

    void delete(int id);

    String kidPage(ModelMap modelMap, SpringUser springUser);
}


