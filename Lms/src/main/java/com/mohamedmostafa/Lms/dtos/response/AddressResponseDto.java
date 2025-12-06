package com.mohamedmostafa.Lms.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressResponseDto {

    private String street;

    private String city;

    private String country;

}
