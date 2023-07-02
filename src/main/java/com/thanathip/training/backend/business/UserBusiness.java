package com.thanathip.training.backend.business;

import com.thanathip.training.backend.entity.User;
import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.exception.FileException;
import com.thanathip.training.backend.exception.UserException;
import com.thanathip.training.backend.mapper.UserMapper;
import com.thanathip.training.backend.model.MLoginRequest;
import com.thanathip.training.backend.model.MRegisterRequest;
import com.thanathip.training.backend.model.MRegisterResponse;
import com.thanathip.training.backend.service.TokenService;
import com.thanathip.training.backend.service.UserService;
import com.thanathip.training.backend.util.SecurityUtil;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserBusiness {

    private final UserService userService;

    private final TokenService tokenService;
    private final UserMapper userMapper;


    public UserBusiness(UserService userService, TokenService tokenService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }


    public List<Map<String, Object>> register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        MRegisterResponse response = userMapper.toRegisterResponse(user);

        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("email", response.getEmail());
        jsonData.put("name", response.getName());
        dataList.add(jsonData);

        return dataList;
    }

    public List<Map<String, Object>> login(MLoginRequest request) throws BaseException {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw UserException.emailNullLogin();
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw UserException.passwordNullLogin();
        }
        //verify
        Optional<User> opt = userService.findByEmail(request.getEmail());

        if (opt.isEmpty()) {
            throw UserException.emailNotFound();
        }

        User user = opt.get();
        if (!userService.mathPassword(request.getPassword(), opt.get().getPassword())) {
            throw UserException.passwordNotMatch();
        }

        //expired in 60 minute
        return getMaps(user);
    }

    public List<Map<String, Object>> refreshToken() throws BaseException {

        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);

        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }
        User user = optUser.get();

        return getMaps(user);
    }

    private List<Map<String, Object>> getMaps(User user) {
        Date expAssesstoken = Date.from(new Date().toInstant().plusSeconds(60 * 60));
        Date expRefreshToken = Date.from(new Date().toInstant().plusSeconds(60 * 60 * 24 * 14));

        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("assessToken", tokenService.tokenize(user, expAssesstoken));
        jsonData.put("refreshToken", tokenService.tokenize(user, expRefreshToken));
        dataList.add(jsonData);

        return dataList;
    }


    public List<Map<String, Object>> uploadProfilePicture(MultipartFile file) throws FileException, JSONException {
        if (file == null) {
            throw FileException.fileNull();
        }

        if (file.getSize() > 1048576 * 5) {
            throw FileException.fileSizeTooLarge();
        }

        String contentType = file.getContentType();

        if (contentType == null) {
            throw FileException.fileExtensionNotSupport();
        }

        List<String> supportType = Arrays.asList("image/png", "image/jpeg", "image/gif");

        if (!supportType.contains(contentType)) {
            throw FileException.fileExtensionNotSupport();
        }

        try {
            byte[] bytes = file.getBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("OK", "OK");
        dataList.add(jsonData);

        return dataList;
    }


}
