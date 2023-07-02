package com.thanathip.training.backend.mapper;

import com.thanathip.training.backend.entity.User;
import com.thanathip.training.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MRegisterResponse toRegisterResponse(User user);
}
