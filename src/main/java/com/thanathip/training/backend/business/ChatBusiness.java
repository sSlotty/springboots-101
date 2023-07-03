package com.thanathip.training.backend.business;

import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.exception.ChatException;
import com.thanathip.training.backend.model.ChatMessage;
import com.thanathip.training.backend.model.ChatMessageRequest;
import com.thanathip.training.backend.util.SecurityUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ChatBusiness {

    private final SimpMessagingTemplate template;

    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageRequest request) throws BaseException {

        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if(!opt.isPresent()){
            throw ChatException.accessDenied();
        }

        //TODO :: validate message

        final String destination = "/topic/chat";

        ChatMessage payload = new ChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());

        template.convertAndSend(destination, payload);
    }
}
