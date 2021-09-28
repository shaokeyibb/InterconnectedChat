package io.hikarilan.interconnectedchat.entities;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chat {

    private final long chat_id;
    private final long time;
    private final String server_name;
    private final String player_name;
    private final JsonObject chat_json;

}
