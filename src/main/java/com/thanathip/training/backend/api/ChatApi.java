package com.thanathip.training.backend.api;

import com.thanathip.training.backend.business.ChatBusiness;
import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.model.ChatMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApi {

    private final ChatBusiness chatBusiness;

    public ChatApi(ChatBusiness chatBusiness) {
        this.chatBusiness = chatBusiness;
    }


    @PostMapping("/message")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatMessageRequest request) throws BaseException {
        chatBusiness.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
