package com.mohamedmostafa.Lms.dtos.response;

import com.mohamedmostafa.Lms.enums.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponseDto {


    String username;

    String email;

    Role role;

    AddressResponseDto address;


}
