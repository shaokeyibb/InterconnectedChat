package io.hikarilan.interconnectedchat;

import io.hikarilan.interconnectedchat.configuration.Config;
import io.hikarilan.interconnectedchat.entities.Chat;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class InterconnectedChatApplication {

    @Getter
    private static InterconnectedChatApplication instance;

    @Getter
    private final List<Chat> chatList = new ArrayList<>();

    @Getter
    private Config config;

    public InterconnectedChatApplication(@Autowired Config config) {
        this.config = config;
    }

    public static void main(String[] args) {
        SpringApplication.run(InterconnectedChatApplication.class, args);
    }
}
