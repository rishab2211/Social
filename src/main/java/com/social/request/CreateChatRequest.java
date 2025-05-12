package com.social.request;

import java.util.UUID;

public class CreateChatRequest {

    private UUID userId;

    public CreateChatRequest() {
    }

    public CreateChatRequest(UUID user2) {

        this.userId = user2;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
