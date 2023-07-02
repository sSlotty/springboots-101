package com.thanathip.training.backend.service;

import com.thanathip.training.backend.entity.User;
import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.exception.UserException;
import com.thanathip.training.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(String email, String password, String name) throws BaseException {
        if (email == null) {
            throw UserException.emailNullCreate();
        }

        if (password == null) {
            throw UserException.passwordNullCreate();
        }

        if (name == null) {
            throw UserException.nameNullCreate();
        }

        if (userRepository.existsByEmail(email)) {
            throw UserException.emailDuplicate();
        }


        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);

        return userRepository.save(user);
    }

    public boolean mathPassword(String password, String passwordHash) {
        return passwordEncoder.matches(password, passwordHash);
    }

    public Optional<User> findByEmail(String email) throws BaseException {
        if (email == null) {
            throw UserException.emailNull();
        }

        return userRepository.findByEmail(email);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User updateName(String id , String name) throws BaseException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw UserException.notFound();
        }
        User user = opt.get();
        user.setName(name);
        return userRepository.save(user);
    }

    public User updatePassword(String id , String password) throws BaseException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw UserException.notFound();
        }
        User user = opt.get();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public void deleteById(String id) throws BaseException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw UserException.notFound();
        }
        userRepository.deleteById(id);
    }


    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }
}
