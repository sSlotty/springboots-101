package com.thanathip.training.backend.exception;

public class ChatException extends BaseException{

        public ChatException(String message) {
            super(message);
        }

        public static ChatException accessDenied(){
            return new ChatException("access.denied.not.found");
        }
}
