package am.itspace.kidsacademy.service.impl;

import am.itspace.kidsacademy.entity.Kid;
import am.itspace.kidsacademy.entity.enums.UserType;
import am.itspace.kidsacademy.repository.KidRepository;
import am.itspace.kidsacademy.security.SpringUser;
import am.itspace.kidsacademy.service.KidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KidServiceImpl implements KidService {

    private final KidRepository kidRepository;

    @Override
    public List<Kid> findAll() {
        return kidRepository.findAll();
    }

    @Override
    public Kid save(Kid kid) {
        return kidRepository.save(kid);
    }

    @Override
    public void delete(int id) {
        Optional<Kid> byId = kidRepository.findById(id);
        if(byId.isPresent()){
            Kid kid = byId.get();
            kidRepository.deleteById(kid.getId());
        }
    }

    @Override
    public String kidPage(ModelMap modelMap, SpringUser springUser) {
        List<Kid> kids = kidRepository.findAll();
        List<Kid> userKids = new ArrayList<>();
        if (springUser.getUser().getUserType() == UserType.USER) {
            for (Kid kid : kids) {
                if (kid.getUser().getId() == springUser.getUser().getId()) {
                    userKids.add(kid);
                }
            }
            modelMap.addAttribute("userKids", userKids);
            return "/user/kidsPage";
        }
        modelMap.addAttribute("kids", kids);
        return "/admin/kidsPage";
    }
}




