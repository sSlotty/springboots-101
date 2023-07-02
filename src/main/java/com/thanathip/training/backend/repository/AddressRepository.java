package com.thanathip.training.backend.repository;

import com.thanathip.training.backend.entity.Address;
import com.thanathip.training.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);


}
