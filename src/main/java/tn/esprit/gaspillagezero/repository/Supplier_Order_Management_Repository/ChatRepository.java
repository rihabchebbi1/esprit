package tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByUserIdOrderByTimestampDesc(Long userId);
}
