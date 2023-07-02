package com.thanathip.training.backend.model;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class BaseResponse {

    private String message;

    private List<Map<String, Object>> data;

    private int status;


}
