package com.thanathip.training.backend.exception;

import java.io.Serial;

public class UserException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserException(String message) {
        super("user exception: " + message);
    }

    public static UserException unauthorized() {
        return new UserException("unauthorized");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    public static UserException passwordNull() {
        return new UserException("register.password.null");
    }

    public static UserException nameNull() {
        return new UserException("register.name.null");
    }



    //CREATE
    public static UserException emailNullCreate() {
        return new UserException("create.email.null");
    }

    public static UserException emailDuplicate() {
        return new UserException("register.email.duplicate");
    }

    public static UserException passwordNullCreate() {
        return new UserException("create.password.null");
    }

    public static UserException nameNullCreate() {
        return new UserException("create.name.null");
    }


    public static UserException emailNotFound() {
        return new UserException("login.fail.email");
    }

    public static UserException passwordNotMatch() {
        return new UserException("login.fail.password");
    }

    public static UserException emailNullLogin() {
        return new UserException("login.email.null");
    }

    public static UserException passwordNullLogin() {
        return new UserException("login.password.null");
    }

    public static UserException notFound(){
        return new UserException("user.notfound");
    }


}
