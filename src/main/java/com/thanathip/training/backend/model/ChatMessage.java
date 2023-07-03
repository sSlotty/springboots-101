package com.thanathip.training.backend.model;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessage {

    private String from;
    private String message;
    private Date created;

    public ChatMessage() {
        this.created = new Date();
    }
}
