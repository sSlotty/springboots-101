package com.thanathip.training.backend.repository;

import com.thanathip.training.backend.entity.Social;
import com.thanathip.training.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);


}
