package com.thanathip.training.backend.service;

import com.thanathip.training.backend.entity.Address;
import com.thanathip.training.backend.entity.User;
import com.thanathip.training.backend.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findByUSer(User user) {
        return addressRepository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipCode) {

        //TODO : create
        Address entity = new Address();

        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipCode(zipCode);

        return addressRepository.save(entity);
    }
}
