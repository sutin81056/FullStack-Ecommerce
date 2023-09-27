package com.example.mydemo.model.entity.mappers;

import com.example.mydemo.model.entity.User;
import com.example.mydemo.model.entity.dto.SignUpDto;
import com.example.mydemo.model.entity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// 由spring管理
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    // 忽略password
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
