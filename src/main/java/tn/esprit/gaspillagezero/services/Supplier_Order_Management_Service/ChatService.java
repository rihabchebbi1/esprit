package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Chat;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.ChatRepository;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.OrderRepository;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final OrderRepository orderRepository;
    private static final String API_URL = "https://api.together.xyz/v1/chat/completions";
    private static final String API_KEY = "01301ddd79db215d207cd1e4f3a6c11ef9847f64f10dba8fb5e7aeae94106a38";
    private static final int MAX_HISTORY = 1000;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendMessage(Long userId, String userMessage) {
        try {
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", "meta-llama/Meta-Llama-3.1-8B-Instruct-Turbo-classifier");
            requestBody.put("max_tokens", 200);

            ArrayNode messagesArray = requestBody.putArray("messages");
            List<Chat> chatHistory = getChatHistory(userId, MAX_HISTORY);
            List<Order> orderChat= getAllOrder();
            for (Chat chat : chatHistory) {
                ObjectNode chatNode = objectMapper.createObjectNode();
                chatNode.put("role", chat.getRole());
                chatNode.put("content", chat.getContent());
                messagesArray.add(chatNode);
            }

            for (Order order: orderChat){
                ObjectNode orderNode = objectMapper.createObjectNode();
                orderNode.put("role", "user");
                orderNode.put("content", order.toString());
                messagesArray.add(orderNode);}

            ObjectNode userMessageNode = objectMapper.createObjectNode();
            userMessageNode.put("role", "user");
            userMessageNode.put("content", userMessage);
            messagesArray.add(userMessageNode);
            System.out.println(userMessage);
            String jsonRequest = objectMapper.writeValueAsString(requestBody);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(jsonRequest, MediaType.parse("application/json")))
                    .build();

            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                String responseBody = response.body().string();
                JsonNode jsonResponse = objectMapper.readTree(responseBody);
                if (jsonResponse.has("choices") && jsonResponse.get("choices").size() > 0) {
                    String assistantResponse = jsonResponse.get("choices").get(0).get("message").get("content").asText();

                    saveMessage(new Chat(userId, "user", userMessage));
                    saveMessage(new Chat(userId, "assistant", assistantResponse));

                    return assistantResponse;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Erreur de l'IA";
    }




    private void saveMessage(Chat chat) {
        chatRepository.save(chat);
    }

    public List<Chat> getChatHistory(Long userId, int limit) {
        return chatRepository.findAllByUserIdOrderByTimestampDesc(userId);
    }

    public List<Order> getAllOrder() {
        System.out.println(orderRepository.findAll());    return orderRepository.findAll();}
}
