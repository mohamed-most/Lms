package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.response.UserResponseDto;
import com.mohamedmostafa.Lms.entity.User;

public class UserMapper {

    public static UserResponseDto toResponseDto(User user) {

        return UserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .address(AddressMapper.toResponseDto(user.getAddress())) // assuming AddressMapper exists
                .build();
    }
}
