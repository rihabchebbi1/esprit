package tn.esprit.gaspillagezero.controllers.Supplier_Order_Managementcontrollers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Chat;
import tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service.ChatService;

import java.util.List;

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
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Chat>> sendMessage(@PathVariable Long userId)
    {
        return ResponseEntity.ok(chatService.getChatHistory(userId, 100));}


    record chat(
            Long userId,
            String userMessage
    ){}
}