package tn.esprit.gaspillagezero.services.EventManagement_Servvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.EventManagement.Menus;
import tn.esprit.gaspillagezero.repository.EventManagement_Repository.MenuRepository;

import java.util.List;
@Service
public class MenuServiceImplement implements IMenuService{
    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menus addMenu(Menus menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menus updateMenu(Menus menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void deleteMenu(long menuId) {
        menuRepository.deleteById(menuId);

    }

    @Override
    public List<Menus> retieveAllmenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menus retieveMenu(long menuId) {
        return menuRepository.findById(menuId).get();
    }
}
