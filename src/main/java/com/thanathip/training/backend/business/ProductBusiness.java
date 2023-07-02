package com.thanathip.training.backend.business;

import com.thanathip.training.backend.exception.ProductException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductBusiness {

    public List<Map<String, Object>> getProductByID(String id) throws ProductException, JSONException {

        List<Map<String, Object>> dataList = new ArrayList<>();

// Create a map representing a task
        Map<String, Object> task1 = new HashMap<>();
        task1.put("userId", 1);
        task1.put("id", 1);
        task1.put("title", "delectus aut autem");
        task1.put("completed", false);
        dataList.add(task1);

// Create another map representing a task
        Map<String, Object> task2 = new HashMap<>();
        task2.put("userId", 1);
        task2.put("id", 2);
        task2.put("title", "lorem ipsum dolor");
        task2.put("completed", true);
        dataList.add(task2);


        return dataList;
    }
}
