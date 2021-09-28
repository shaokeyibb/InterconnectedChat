package io.hikarilan.interconnectedchat.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.hikarilan.interconnectedchat.InterconnectedChatApplication;
import io.hikarilan.interconnectedchat.entities.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class ChatController {

    private final InterconnectedChatApplication instance = InterconnectedChatApplication.getInstance();

    private final AtomicLong counter = new AtomicLong();

    @PostMapping(value = "/postChat", produces = "application/json")
    public ResponseEntity<String> postChat(@RequestBody PostRequest requestBody) {
        if (instance.getConfig().isEnableServerNameWhitelist() && !instance.getConfig().getAllowedServerMame().contains(requestBody.getServer_name()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        try {
            new Gson().fromJson(requestBody.getChat_json(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        instance.getChatList().add(new Chat(counter.getAndAdd(1L), System.currentTimeMillis(), requestBody.getServer_name(), requestBody.getPlayer_name(), new Gson().fromJson(requestBody.getChat_json(), JsonObject.class)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getChatList")
    public List<Chat> getChatList(@RequestParam(defaultValue = "-1", required = false) int last_index) {
        if (last_index < 0)
            return instance.getChatList();
        else if (last_index > instance.getChatList().size())
            throw new BadRequestException();
        else {
            return instance.getChatList().stream().sorted(Comparator.comparingLong(Chat::getChat_id).reversed()).limit(last_index).collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    private static class PostRequest {
        private final String server_name;
        private final String player_name;
        private final String chat_json;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public static class BadRequestException extends RuntimeException {
    }
}
