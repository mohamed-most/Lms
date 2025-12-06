package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.request.AddressRequestDto;
import com.mohamedmostafa.Lms.dtos.response.AddressResponseDto;
import com.mohamedmostafa.Lms.entity.Address;

public class AddressMapper {


    public static Address toEntity(AddressRequestDto addressRequestDto) {
        return Address.builder()
                .country(addressRequestDto.getCountry())
                .city(addressRequestDto.getCity())
                .street(addressRequestDto.getStreet())
                .build();
    }


    public static AddressResponseDto toResponseDto(Address address) {
        return AddressResponseDto.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }
}
