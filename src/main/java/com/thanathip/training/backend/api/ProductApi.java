package com.thanathip.training.backend.api;

import com.thanathip.training.backend.business.ProductBusiness;
import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.model.BaseResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductApi {

    public final ProductBusiness business;

    public ProductApi(ProductBusiness business) {
        this.business = business;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable("id") String id) throws BaseException, JSONException {

        BaseResponse response = new BaseResponse();

        List<Map<String, Object>> product = business.getProductByID(id);

        response.setMessage("Hello Test");
        response.setData(product);
        response.setStatus(200);


        return ResponseEntity.ok(response);
    }


}
