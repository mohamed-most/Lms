package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.request.AddressRequestDto;
import com.mohamedmostafa.Lms.dtos.response.AddressResponseDto;

public interface AddressService {


    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto);


    public AddressResponseDto getAddress();


}
