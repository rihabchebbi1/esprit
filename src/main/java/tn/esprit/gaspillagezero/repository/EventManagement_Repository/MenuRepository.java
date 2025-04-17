package tn.esprit.gaspillagezero.repository.EventManagement_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.EventManagement.Menus;

public interface MenuRepository extends JpaRepository<Menus, Long> {
}
