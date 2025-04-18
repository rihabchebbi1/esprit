package tn.esprit.gaspillagezero.entites.Supplier_Order_Management;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String role;
    @Column(length = 10000)
    private String content;
    private Timestamp timestamp;

    public Chat(Long userId, String role, String content) {
        this.userId = userId;
        this.role = role;
        this.content = content;
    }
}
