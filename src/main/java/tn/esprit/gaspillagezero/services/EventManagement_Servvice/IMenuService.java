package tn.esprit.gaspillagezero.services.EventManagement_Servvice;

import tn.esprit.gaspillagezero.entites.EventManagement.Menus;

import java.util.List;

public interface IMenuService {
    Menus addMenu(Menus menu);
    Menus updateMenu(Menus menu);
    void deleteMenu(long menuId);
    List<Menus> retieveAllmenus();
    Menus retieveMenu(long menuId);
}
