package com.thanathip.training.backend.api;

import com.thanathip.training.backend.business.UserBusiness;
import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.model.BaseResponse;
import com.thanathip.training.backend.model.MLoginRequest;
import com.thanathip.training.backend.model.MRegisterRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserApi {

    public final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        List<Map<String, Object>> data = business.register(request);
        BaseResponse response = new BaseResponse();
        response.setMessage("Register Success");
        response.setData(data);
        response.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody MLoginRequest request) throws BaseException, JSONException {
        List<Map<String, Object>> data = business.login(request);
        BaseResponse response = new BaseResponse();

        response.setMessage("Login Success");
        response.setData(data);
        response.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<BaseResponse> refreshToken() throws BaseException, JSONException {
        List<Map<String, Object>> data = business.refreshToken();

        BaseResponse response = new BaseResponse();

        response.setMessage("Refresh Token Success");
        response.setData(data);
        response.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException, JSONException {

        BaseResponse testResponse = new BaseResponse();

        List<Map<String, Object>> data  = business.uploadProfilePicture(file);

        testResponse.setMessage("Hello Test");
        testResponse.setData(data);
        testResponse.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(testResponse);
    }


}
