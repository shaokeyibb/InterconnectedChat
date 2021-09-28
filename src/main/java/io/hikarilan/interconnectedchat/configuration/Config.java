package io.hikarilan.interconnectedchat.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Config {

    @Value("${interconnectedChat.enableServerNameWhitelist}")
    private boolean enableServerNameWhitelist;
    @Value("#{'${interconnectedChat.allowedServerMame}'.split(',')}")
    private List<String> allowedServerMame;

}
