package tn.esprit.gaspillagezero.repository.EventManagement_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.EventManagement.Event;
import tn.esprit.gaspillagezero.entites.Marketplace.Dish;

public interface EventRepository extends JpaRepository<Event, Long> {
}
