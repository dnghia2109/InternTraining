package com.techlead.javaspring.mapper;

import com.techlead.javaspring.dto.UserDto;
import com.techlead.javaspring.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
        return userDto;
    }
}
