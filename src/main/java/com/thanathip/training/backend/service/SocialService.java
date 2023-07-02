package com.thanathip.training.backend.service;

import com.thanathip.training.backend.entity.Social;
import com.thanathip.training.backend.entity.User;
import com.thanathip.training.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository socialRepository;


    public SocialService(SocialRepository SocialRepository) {
        this.socialRepository = SocialRepository;
    }

    public Optional<Social> findByUSer(User user) {
        return socialRepository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) {

        //TODO: validate

        //TODO : create
        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);
        return socialRepository.save(entity);
    }
}
