package com.thanathip.training.backend.model;

import lombok.Data;

@Data
public class MLoginRequest {

    private String email;
    private String password;
}
