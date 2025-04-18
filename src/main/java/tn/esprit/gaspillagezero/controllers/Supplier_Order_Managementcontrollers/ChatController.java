package tn.esprit.gaspillagezero.controllers.Supplier_Order_Managementcontrollers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service.ChatService;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody chat chat) {
        String response = chatService.sendMessage(chat.userId, chat.userMessage);
        return ResponseEntity.ok(response);
    }

    record chat(
            Long userId,
            String userMessage
    ){}
}