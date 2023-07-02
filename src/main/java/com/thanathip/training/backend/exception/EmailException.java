package com.thanathip.training.backend.exception;

public class EmailException extends BaseException{

    public EmailException(String message){
        super(message);
    }

    public static EmailException templateNotFound(){
        return new EmailException("template.not.found");
    }
}
